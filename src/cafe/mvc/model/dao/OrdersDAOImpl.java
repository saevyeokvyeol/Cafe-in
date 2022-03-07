package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Statistics;
import cafe.mvc.util.DbUtil;

public class OrdersDAOImpl implements OrdersDAO {

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
	
		return 0;
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
		Scanner sc = new Scanner(System.in);
		
		try {
			
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(sql);
			System.out.println("변경할 상태코드는 ? \n 1.접수대기 | 2.주문 접수 |  3.상품 준비중 | 4. 상품 준비 완료 | 5. 픽업 완료 | 6. 주문 취소");
			int a = sc.nextInt();
			System.out.println("변경할 주문번호는 ?");
			int b = sc.nextInt();
			ps.setInt(1, a);
			ps.setInt(2, b);
			
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
	 * 현재 진행 중인 주문 검색: 픽업 완료, 주문 취소 상태가 아닌 모든 주문 검색
	 * : 메소드명 고민중입니다... 다들 아이디어 부탁드려요!
	 * */
	@Override
	public List<Orders> selectOnoingOrder() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> orderList = null;
		String sql = "";
		
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			ps= con.prepareStatement(sql);
			
			orderList = (List<Orders>) ps.executeQuery();
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		
		return orderList;
	}

	/**
	 * 회원의 지난 주문 내역 조회
	 * : 로그인한 회원의 정보를 통해 회원의 지난 주문 내역 검색
	 *   주문 상세 메소드를 따로 만들어 상세 내역도 함께 보여주기
	 * */
	@Override
	public List<Orders> selectByUserTel(String UserTel) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = null;
		String sql = "select u.user_tel,u.user_name, ol.qty, p.prod_name, p.prod_price,ol.price_qty from users u join orders o on u.user_tel = o.user_tel join order_line ol using(order_num)join product p on ol.prod_code = p.prod_code where u.user_tel=?;";
		Orders orders= null;

		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, UserTel);
			rs = ps.executeQuery();
			//List = new ArrayList<Orders>();
			//전화번호,이름,주문수량,상품명,판매가격,가격*주문수량
			while(rs.next()) {
				orders = new Orders( rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getInt(6) );
				list.add(orders);
			}
		
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		
		return list;
	}


	/**
	 * 일간 매출 통계
	 * : 통계를 어떻게 끌어올까요...(통계용 DTO를 새로 만들어서 가져오기...?)
	 * */
	@Override
	public Statistics dailySalesStatistic() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
