package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import cafe.mvc.model.dto.OrderLineDTO;
import cafe.mvc.model.dto.OrdersDTO;
import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.model.dto.StatisticsDTO;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.util.DbUtil;

public class OrdersDAOImpl implements OrdersDAO {
	// DbUtil에서 proFile 가져오기
	private Properties proFile = DbUtil.getProFile();
	private ProductDAO productDao = new ProductDAOImpl();

	/**
	 * 주문하기
	 * 1. session에 저장된 장바구니 map을 가져와 구매할 물건과 수량 파악
	 * 2. orders 테이블에 insert
	 * 3. 회원이며 적립금을 사용한 경우 적립금 차감(user 테이블 update)
	 * 4. order_line 테이블에 insert
	 * 5. 디저트 주문이 들어온 경우 stock 감소(update)
	 * 
	 * 코드가 너무 길어지면 메소드를 적당히 나누기
	 * 
	 * @ 자동 커밋 해제할 것!!
	 * 
	 * 만약 재고가 모자라서 못 사면 몇 개 남았다고 알려주기
	 * */
	@Override
	public int orderInsert(OrdersDTO orders) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.orderInsert");
		// insert into orders values(orders_seq.nextval, ?, 0, ?, ?, ?, sysdate, ?);
		// 1. 전화번호, 2. 결제 방법, 3. 적립금 사용 액수, 4. 총 결제 금액, 5. 테이크아웃 여부
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); // 자동 커밋 해제
			
			String userTel = orders.getUserTel();
			String payMethod = orders.getPayMethod();
			int payPoint = orders.getPayPoint();
			int totalPrice = this.getToTalPrice(con, orders);
			
			ps = con.prepareStatement(sql);
			
			if(!userTel.equals("guest")) {
				ps.setString(1, userTel);
			} else {
				ps.setString(1, null);
			}
			
			if(payMethod.equals("카드")) {
				ps.setString(2, "card");
			} else if(payMethod.equals("현금")) {
				ps.setString(2, "cash");
			} else {
				throw new SQLException("결제 방법이 잘못되어 주문이 완료되지 않았습니다.");
			}
			ps.setInt(3, payPoint);
			ps.setInt(4, totalPrice);
			ps.setInt(5, orders.getTakeOut());
			
			result = ps.executeUpdate();
			
			
			
			if(result == 0) { // 만약 오류로 인해 등록되지 않았다면
				con.rollback();
				throw new SQLException("주문이 완료되지 않았습니다.");
			}
			
			// 적립금 사용 시 적립금 차감
			if(!userTel.equals("guest")) {
				if(payPoint > 0) {
					int dePointResult = dereaseUserPoint(con, payPoint, userTel);
					if(dePointResult != 1) {
						throw new SQLException("적립금 사용 오류가 발생하여 주문이 완료되지 않았습니다.");
					}
				} else if(payPoint < 0) {
					throw new SQLException("적립금 사용 액수가 잘못되어 주문이 완료되지 않았습니다.");
				}
			} else {
				if(payPoint != 0) {
					throw new SQLException("비회원은 적립금을 사용할 수 없습니다.");
				}
			}
			
			// 적립금 추가
			if(!userTel.equals("guest")) {
				int inPointResult = increaseUserPoint(con, totalPrice, userTel);
				if(inPointResult != 1) {
					throw new SQLException("적립금 적립 오류가 발생하여 주문이 완료되지 않았습니다.");
				}
				
			}
			
			 // orderLineInsert를 통해 주문 상세 insert 
			int lineResult[] = orderLineInsert(con, orders);
			for(int i : lineResult) {
				if(i != 1) { // 결과 array에서 1행이 등록된 정상적인 결과가 아니라면
					con.rollback();
					throw new SQLException("주문이 완료되지 않았습니다.");
				}
			}
			
			for(OrderLineDTO orderLineDTO : orders.getOrderLineList()) {
				if(orderLineDTO.getProdCode().substring(0, 1).equals("D")) {
					int stockResult = decreaseStockUpdate(con, orderLineDTO);
					if(stockResult == 0) {
						throw new SQLException("주문이 완료되지 않았습니다.");
					}
				}
			}
			
			con.commit(); // 모든 것이 정상적으로 완료되면 커밋
		} finally {
			con.commit(); // 중간에 끊겼다면 rollback한 상태로 커밋
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	/**
	 * 적립금 사용한 경우 적립금 차감
	 * */
	public int dereaseUserPoint(Connection con, int payPoint, String userTel) throws SQLException{
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.dereaseUserPoint");
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, payPoint);
			ps.setString(2, userTel);
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps);
		}
		
		return result;
	}
	
	/**
	 * 총 주문 금액의 10% 적립
	 * @throws SQLException 
	 * */
	public int increaseUserPoint(Connection con, int totalPrice, String userTel) throws SQLException {
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.increaseUserPoint");
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, (totalPrice / 10));
			ps.setString(2, userTel);
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.close(null, ps);
		}
		
		return result;
	}
	
	/**
	 * 주문 상세 등록하기
	 * */
	public int[] orderLineInsert(Connection con, OrdersDTO ordersDTO) throws SQLException{
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.orderLineInsert");
		// insert into order_line values(orderline_seq.nextval, ?, ?, ?, ?)
		// 오더 넘버, 상품코드, 갯수, 가격*갯수
		int[] result = null;
		
		try {
			ps = con.prepareStatement(sql);
			for(OrderLineDTO orderLineDTO : ordersDTO.getOrderLineList()) {
				ProductDTO productDTO = productDao.productSelectByProdCode(orderLineDTO.getProdCode()); // 코드로 상품 정보 끌어오기 
				ps.setString(1, productDTO.getProdCode());
				ps.setInt(2, orderLineDTO.getQty());
				ps.setInt(3, (productDTO.getProdPrice() * orderLineDTO.getQty()));
				ps.addBatch(); // 배치에 추가
				ps.clearParameters(); // 파라미터 지우기
			}
			
			result = ps.executeBatch(); // 일괄처리
		} finally {
			DbUtil.close(null, ps);
		}
		return result;
	}
	
	/**
	 * 디저트일 경우 재고량 감소
	 * */
	public int decreaseStockUpdate(Connection con, OrderLineDTO orderLineDTO) throws SQLException {
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.decrementStockUpdate");
		int result = 0;
		
		try {
			ProductDTO product = productDao.productSelectByProdCode(orderLineDTO.getProdCode());
			
			String prodCode = product.getProdCode();
			int prodStock = product.getStock().getProdStock();
			
			if(prodCode.substring(0, 1).equals("D") && prodStock < orderLineDTO.getQty()) {
				throw new SQLException("재고량이 부족해 결제를 실패했습니다.\n" + prodCode + " 상품의 남은 수량은 " + prodStock +"개 입니다.");
			}
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderLineDTO.getQty());
			ps.setString(2, orderLineDTO.getProdCode());
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps);
		}
		
		return result;
	}
	
	/**
	 * 상품 총 구매 금액 구하기
	 * */
	public int getToTalPrice(Connection con, OrdersDTO orders) throws SQLException{
		List<OrderLineDTO> orderLineList = orders.getOrderLineList();
		int total = 0;
		for(OrderLineDTO orderline : orderLineList) {
			ProductDTO productDTO = productDao.productSelectByProdCode(orderline.getProdCode());
			if(productDTO == null) {
				throw new SQLException("상품 번호에 오류가 발생해 결제를 실패했습니다.");
			}
			
			total = (orderline.getQty() * productDTO.getProdPrice());
		}
		
		return total;
	}

	/**
	 * 주문 상태 코드 변경
	 * orders의 주문 상태 코드 update
	 * : 주문 번호와 상태 코드를 받아서 orders를 생성해 인수로 받음
	 * */
	@Override
	public int orderStateUpdate(OrdersDTO ordersDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.orderStateUpdate");
		int result=0;
	
		try {
			con = DbUtil.getConnection();
			
			ps=con.prepareStatement(sql);
			int stateCode = ordersDTO.getStateCode();
			int orderNum = ordersDTO.getOrderNum();
			ps.setInt(1, stateCode);
			ps.setInt(2, orderNum);
			
			result = ps.executeUpdate();
			if(result==0) {
				con.rollback();
				throw new SQLException("상태 코드 변경에 실패했습니다.");
			}
			
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * 회원의 지난 주문 내역 조회
	 * : 로그인한 회원의 정보를 통해 회원의 지난 주문 내역 검색
	 *   주문 상세 메소드를 따로 만들어 상세 내역도 함께 보여주기
	 * */
	@Override
	public List<OrdersDTO> selectByUserTel(String UserTel)  throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//Orders orders = null;
		List<OrdersDTO> orderList = new ArrayList<>();
		String sql = proFile.getProperty("order.selectByUserTel");
		//int주문상태코드, String이름, String상품명,int 수량, int판매가격, int가격*주문수량 
		try {
			con = DbUtil.getConnection();
			ps= con.prepareStatement(sql);
			ps.setString(1, UserTel);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OrdersDTO orders = new OrdersDTO( rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8) );

				List<OrderLineDTO> orderLineList = orderLineselectByOrderNum(con, orders.getOrderNum());
				for(OrderLineDTO orderLine : orderLineList) {
					ProductDTO product = productDao.productSelectByProdCode(orderLine.getProdCode());
					orderLine.setProduct(product);
				}
				
				orders.setOrderLineList(orderLineList);
				orderList.add(orders);
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		
		return orderList;
	}
	
	/**
	 * 주문상세
	 * */
	public List<OrderLineDTO> orderLineselectByOrderNum(Connection con, int orderNum)throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderLineDTO> list = new ArrayList<OrderLineDTO>();
		
		String sql = proFile.getProperty("order.selectByUserTelOrderLine");
		

		try {
			ps= con.prepareStatement(sql);
			ps.setInt(1, orderNum);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OrderLineDTO orderLineDTO = new OrderLineDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				list.add(orderLineDTO);
			}
		} finally {
			DbUtil.close(null, ps, rs);
		}
		
		return list;
	}

	/**
	 * 현재 진행 중인 주문 검색: 픽업 완료, 주문 취소 상태가 아닌 모든 주문 검색
	 * */
	@Override
	public List<OrdersDTO> selectOnoingOrder() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrdersDTO> list = new ArrayList<>();
		
		String sql = "select*from orders where state_code not in(4,5)";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {

				OrdersDTO orders = new OrdersDTO( rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8));

				List<OrderLineDTO> orderLineList = orderLineselectByOrderNum(con, orders.getOrderNum());
				for(OrderLineDTO orderLine : orderLineList) {
					ProductDTO product = productDao.productSelectByProdCode(orderLine.getProdCode());
					orderLine.setProduct(product);
				}
				
				orders.setOrderLineList(orderLineList);
				list.add(orders);

			}
			
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	/**
	 * 일간 매출 통계
	 * : 통계를 어떻게 끌어올까요...(통계용 DTO를 새로 만들어서 가져오기...?)
	 * */
	@Override

	public Map<String, Integer> dailySalesStatistic(String date) throws SQLException {
		// con, ps, rs, 리턴값 생성, sql문 긁어오기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Map<String, Integer> statisticMap = new HashMap<String, Integer>();
		String sql = proFile.getProperty("statistic.dailyStatistic");
		// select order_num, prod_code, qty, price_qty from orders join order_line using(order_num) where to_char(order_date, 'yymmdd') = ? order by order_num
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			
			rs = ps.executeQuery();
			
			int orderTimes = 0;
			int salesPrice = 0;
			int dessertSalesQty = 0; // 디저트
			int drinkSalesQty = 0; // 음료
			int orderNum = 0;
			while(rs.next()) {
				
				if(orderNum != rs.getInt(1)) { // 주문 번호가 다르면 총 주문 횟수++
					orderTimes++;
					orderNum = rs.getInt(1);
				}
				
				salesPrice += rs.getInt(4);
				if(rs.getString(2).substring(0, 1).equals("D")) {
					dessertSalesQty += rs.getInt(3);
				} else {
					drinkSalesQty += rs.getInt(3);
				}
			}

			statisticMap.put("date", Integer.parseInt(date));
			statisticMap.put("orderTimes", orderTimes);
			statisticMap.put("salesPrice", salesPrice);
			statisticMap.put("dessertSalesQty", dessertSalesQty);
			statisticMap.put("drinkSalesQty", drinkSalesQty);
			
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return statisticMap;
	}
	
	/**
	 * 메뉴별 판매 통계: 현재까지 팔린 메뉴 
	 * */
	@Override
	public List<StatisticsDTO> productSalesStatistic() throws SQLException, NotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StatisticsDTO> list = new ArrayList<StatisticsDTO>();
		String sql = proFile.getProperty("order.productSalesStatistic");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StatisticsDTO statistics = new StatisticsDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), (rs.getInt(3)*rs.getInt(4)));
				list.add(statistics);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

}
