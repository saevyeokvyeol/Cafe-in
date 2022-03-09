package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dao.OrdersDAO;
import cafe.mvc.model.dao.OrdersDAOImpl;
import cafe.mvc.model.dto.OrdersDTO;
import cafe.mvc.model.dto.StatisticsDTO;

public class OrdersServiceImpl implements OrdersService {

	OrdersDAO ordersDao = new OrdersDAOImpl();	
	
	/**
	 * 주문하기
	 * 1. session에 저장된 장바구니 map을 가져와 구매할 물건과 수량 파악
	 * 2. orders 테이블에 insert
	 * 3. 회원이며 적립금을 사용한 경우 적립금 차감(user 테이블 update)
	 * 4. order_line 테이블에 insert
	 * 5. 디저트 주문이 들어온 경우 stock 감소(update)
	 * */
	@Override
	public void orderInsert(OrdersDTO ordersDTO) throws SQLException, AddException {
		int result = ordersDao.orderInsert(ordersDTO);
		if(result == 0) {
			throw new AddException("주문이 완료되지 않았습니다.");
		}
	}

	/**
	 * 주문 상태 코드 변경
	 * orders의 주문 상태 코드 update
	 * : 주문 번호와 상태 코드를 받아서 orders를 생성해 인수로 받음
	 * */
	@Override
	public void orderStateUpdate(OrdersDTO ordersDTO) throws SQLException, ModifyException, NotFoundException {

		int result = ordersDao.orderStateUpdate(ordersDTO);
		if(result==0)throw new SQLException("변경을 실패하였습니니다.");

	}

	/**
	 * 현재 진행 중인 주문 검색: 픽업 완료, 주문 취소 상태가 아닌 모든 주문 검색
	 * */
	@Override
	public List<OrdersDTO> selectOngoingOrder() throws SQLException, NotFoundException {
		List<OrdersDTO> list=ordersDao.selectOnoingOrder();
		if(list.isEmpty()) throw new SQLException("현재 진행중인 주문이 없습니다..");
		return list;
	}

	/**
	 * 회원의 지난 주문 내역 조회
	 * : 로그인한 회원의 정보를 통해 회원의 지난 주문 내역 검색
	 * */
	@Override
	public List<OrdersDTO> selectByUserTel(String UserTel) throws SQLException, NotFoundException {
		List<OrdersDTO> list=ordersDao.selectByUserTel(UserTel);
		
		if( list.isEmpty() ) throw new SQLException("지난 주문 내역이 없습니다..");
		return list;
	}

	/**
	 * 일간 매출 통계 조회
	 * */
	@Override

	public Map<String, Integer> dailySalesStatistic(String date) throws SQLException, NotFoundException {
		Map<String, Integer> map = ordersDao.dailySalesStatistic(date);
		if (map == null) {
			throw new NotFoundException("일간 매출을 검색할 수 없습니다.");
		}
		return map;
	}
	
	/**
	 * 메뉴별 판매 통계: 현재까지 팔린 메뉴 
	 * */
	@Override
	public List<StatisticsDTO> productSalesStatistic() throws SQLException, NotFoundException {
		List<StatisticsDTO> list = ordersDao.productSalesStatistic();
		if (list == null) {
			throw new NotFoundException("판매 통계를 검색할 수 없습니다.");
		}
		return list;
	}

}
