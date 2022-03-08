package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cafe.mvc.model.dto.OrderLine;
import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Statistics;
import cafe.mvc.util.DbUtil;

public class OrdersDAOImpl implements OrdersDAO {
	// DbUtil에서 proFile 가져오기
	private Properties proFile = DbUtil.getProFile();
	ProductDAO productDao = new ProductDAOImpl();

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
	 * */
	
	@Override
	public int orderInsert(Orders orders) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.orderInsert");
		// insert into orders values(orders_seq.nextval, ?, 0, ?, ?, ?, sysdate, ?);
		// 전화번호, 결제 방법, 적립금 사용 액수, 총 결제 금액, 테이크아웃 여부
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); // 자동 커밋 해제
			
			ps = con.prepareStatement(sql);
			ps.setString(1, orders.getUserTel());
			ps.setString(2, orders.getPayMethod());
			ps.setInt(3, orders.getPayPoint());
			ps.setInt(4, this.getToTalPrice(orders));
			ps.setInt(5, orders.getTakeOut());
			
			result = ps.executeUpdate();
			
			if(result == 0) { // 만약 오류로 인해 등록되지 않았다면
				con.rollback();
				throw new SQLException("주문이 완료되지 않았습니다.");
			} else {
				int re[] = orderLineInsert(con, orders); // orderLineInsert를 통해 주문 상세 insert
				for(int i : re) {
					System.out.print(i + " | ");
					if(i != 1) { // 결과 array에서 1행이 등록된 정상적인 결과가 아니라면
						con.rollback();
						throw new SQLException("주문이 완료되지 않았습니다.");
					}
				}
			}
			
			decrementStockUpdate(con, orders.getOrdelLineList());
			
			con.commit(); // 모든 것이 정상적으로 완료되면 커밋
		} finally {
			con.commit(); // 중간에 끊겼다면 rollback한 상태로 커밋
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	/**
	 * 주문 상세 등록하기
	 * */
	public int[] orderLineInsert(Connection con, Orders orders) throws SQLException{
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.orderLineInsert");
		// insert into order_line values(orderline_seq.nextval, ?, ?, ?, ?)
		// 오더 넘버, 상품코드, 갯수, 가격*갯수
		int[] result = null;
		
		try {
			ps = con.prepareStatement(sql);
			for(OrderLine orderLine : orders.getOrdelLineList()) {
				Product product = productDao.selectByProdCode(orderLine.getProdCode()); // 코드로 상품 정보 끌어오기 
				ps.setInt(1, orders.getOrderNum());
				ps.setString(2, product.getProdCode());
				ps.setInt(3, orderLine.getQty());
				ps.setInt(4, (product.getProdPrice() * orderLine.getQty()));
				
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
	public int[] decrementStockUpdate(Connection con, List<OrderLine> orderLineList) throws SQLException {
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.decremStockUpdate");
		int[] result = null;
		
		try {
			ps = con.prepareStatement(sql);
			for(OrderLine orderLine : orderLineList) {
				Product product = productDao.selectByProdCode(orderLine.getProdCode()); // 코드로 상품 정보 끌어오기 
				ps.setInt(1, orderLine.getQty());
				ps.setString(2, orderLine.getProdCode());
				
				ps.addBatch(); // 배치에 추가
				ps.clearParameters(); // 파라미터 지우기
			}
			
			result = ps.executeBatch();
		} finally {
			// TODO: handle finally clause
		}
		
		return result;
	}
	
	/**
	 * 상품 총 구매 금액 구하기
	 * */
	public int getToTalPrice(Orders orders) throws SQLException{
		List<OrderLine> orderLineList = orders.getOrdelLineList();
		int total = 0;
		
		for(OrderLine orderline : orderLineList) {
			Product product = productDao.selectByProdCode(orderline.getProdCode());
			if(product == null) {
				throw new SQLException("상품 번호에 오류가 발생해 결제를 실패했습니다.");
			} else if(product.getStock().getProdStock() < orderline.getQty()) {
				throw new SQLException("재고량이 부족해 결제를 실패했습니다.");
			}
			
			total += (orderline.getQty() * product.getProdPrice());
		}
		
		return total;
	}

	/**
	 * 주문 상태 코드 변경
	 * orders의 주문 상태 코드 update
	 * : 주문 번호와 상태 코드를 받아서 orders를 생성해 인수로 받음
	 * */
	@Override
	public int orderStateUpdate(Orders orders) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update orders set state_code =? where order_num=?";
		int result=0;
	
		
		try {
			
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(sql);
			System.out.println("변경할 상태코드는 ? \n 1.접수대기 | 2.주문 접수 |  3.상품 준비중 | 4. 상품 준비 완료 | 5. 픽업 완료 | 6. 주문 취소");
			int stateCode = orders.getStateCode();
			System.out.println("변경할 주문번호는 ?");
			int orderNum = orders.getOrderNum();
			ps.setInt(1, stateCode);
			ps.setInt(2, orderNum);
			
			result = ps.executeUpdate();
			if(result==0) {
				con.rollback();
				throw new SQLException("주문상태코드변경 실패..");
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
	public List<Orders> selectByUserTel(String UserTel)  throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//Orders orders = null;
		List<Orders> orderList = new ArrayList<>();
		String sql = "select*from orders join order_line using(order_num) where user_tel =?";
		//int주문상태코드, String이름, String상품명,int 수량, int판매가격, int가격*주문수량 
		try {
			con = DbUtil.getConnection();
			ps= con.prepareStatement(sql);
			ps.setString(1, UserTel);
			rs= ps.executeQuery();
			//selectByUserTelOrderLine(con,orders.getOrderNum());
			
			while(rs.next()) {
				// int orderNum, String userTel, int stateCode, String payMethod, int payPoint, int totalPrice, String orderDate, int takeOut
				Orders orders = new Orders( rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8) );
				//orderList.add(orders);
				/////////////////
				List<OrderLine> orderLineList = selectByUserTelOrderLine(con,orders.getOrderNum());
				orders.setOrdelLineList(orderLineList);
				
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
	public List<OrderLine> selectByUserTelOrderLine(Connection con, int orderNum)throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderLine> list = new ArrayList<OrderLine>();
		
		String sql = "select*from order_line where order_num=?";
		

		try {
			con=DbUtil.getConnection();
			ps= con.prepareStatement(sql);
			ps.setInt(1, orderNum);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				OrderLine orderLine = new OrderLine(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				list.add(orderLine);
			}
		} finally {
			DbUtil.close(null, ps, rs);
		}
		
		return list;
	}

	

	/**
	 * 현재 진행 중인 주문 검색: 픽업 완료, 주문 취소 상태가 아닌 모든 주문 검색
	 * : 메소드명 고민중입니다... 다들 아이디어 부탁드려요!
	 * */
	@Override
	public List<Orders> selectOnoingOrder() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = new ArrayList<>();
		
		String sql = "select*from orders where state_code not in(4,5)";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Orders orders = new Orders( rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8) );
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
	public Statistics dailySalesStatistic(String date) throws SQLException {
		// con, ps, rs, 리턴값 생성, sql문 긁어오기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Statistics statistics = null;
		String sql = proFile.getProperty("statistic.dailyStatistic");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			
			rs = ps.executeQuery();
			
			int dailyOrderTimes = 0;
			int dailySalesPrice = 0;
			int dailySalesQty = 0;
			int orderNum = 0;
			while(rs.next()) {
				
				if(orderNum != rs.getInt(1)) { // 주문 번호가 다르면 총 주문 횟수++
					dailyOrderTimes++;
					orderNum = rs.getInt(1);
				}
				
				dailySalesPrice += rs.getInt(5);
				dailySalesQty += rs.getInt(4);
			}
			
			statistics = new Statistics(date, dailyOrderTimes, dailySalesPrice, dailySalesQty); 
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		
		return statistics;
	}

}
