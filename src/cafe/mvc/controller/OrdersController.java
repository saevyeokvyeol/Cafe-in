package cafe.mvc.controller;

import java.util.List;
import java.util.Map;

import cafe.mvc.model.dto.OrdersDTO;
import cafe.mvc.model.dto.StatisticsDTO;
import cafe.mvc.model.dto.UsersDTO;
import cafe.mvc.model.service.OrdersService;
import cafe.mvc.model.service.OrdersServiceImpl;
import cafe.mvc.model.service.UsersService;
import cafe.mvc.model.service.UsersServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.MenuView;
import cafe.mvc.view.SuccessView;

public class OrdersController {
	static OrdersService ordersService = new OrdersServiceImpl();

	/**
	 * 주문하기
	 * */
	public static void orderInsert(OrdersDTO orders) {
		try {
			ordersService.orderInsert(orders);
			SuccessView.printMessage("주문이 완료되었습니다. 잠시만 기다려주세요.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		} finally {
			String userTel = orders.getUserTel();
			if(userTel.equals("guest")) {
				MenuView.mainMenu();
			} else {
				MenuView.userMenu(userTel);
			}
		}
	}

	/**
	 * 일간 판매 통계 검색
	 * */
	public static void dailySalesStatistic(String date) {
		try {
			Map<String, Integer> map = ordersService.dailySalesStatistic(date);
			SuccessView.printDailyStatistics(map);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 제품별 판매 통계 검색
	 * */
	public static void productSalesStatistic() {
		try {
			List<StatisticsDTO> list = ordersService.productSalesStatistic();
			SuccessView.printProdStatistics(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 주문상태코드변경
	 */
	public static void orderStateUpdate(OrdersDTO ordersDTO) {
		try {
			ordersService.orderStateUpdate(ordersDTO);
			SuccessView.printMessage("주문상태코드를 변경하였습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 현재진행중인 주문검색
	 */
	public static void selectOngoingOrder() {
		try {
			List<OrdersDTO> orderList = ordersService.selectOngoingOrder();
			SuccessView.selectOngoingOrder(orderList);

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 회원의 지난 주문 내역 조회
	 * */
	public static void selectByUserTel(String userTel) {
		try {
		List<OrdersDTO> list =ordersService.selectByUserTel(userTel);
		
		String userName = new UsersServiceImpl().selectByUserTel(userTel).getUserName();
		SuccessView.printSelectByUserTel(list, userName);
		}catch(Exception e){
			FailView.errorMessage(e.getMessage());
		}
	}
}
