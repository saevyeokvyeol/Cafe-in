package cafe.mvc.controller;

import cafe.mvc.model.dto.OrdersDTO;
import cafe.mvc.model.dto.StatisticsDTO;
import java.util.List;
import cafe.mvc.model.service.OrdersService;
import cafe.mvc.model.service.OrdersServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.SuccessView;

public class OrdersController {
	static OrdersService ordersService = new OrdersServiceImpl();

	/**
	 * 주문하기
	 * */
	public static void orderInsert(OrdersDTO ordersDTO) {
		try {
			ordersService.orderInsert(ordersDTO);
			SuccessView.printMessage("주문이 완료되었습니다. 잠시만 기다려주세요.");
		} catch (Exception e) {
			e.printStackTrace();
//			FailView.errorMessage(e.getMessage());
		}
	}

	public static void dailySalesStatistic(String date) {
		try {
			StatisticsDTO statistic = ordersService.dailySalesStatistic(date);
			SuccessView.printStatistics(statistic);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	// 주문하기, 주문내역보기

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
			SuccessView.printSelectByUserTel(list,userTel);
			}catch(Exception e){
				FailView.errorMessage(e.getMessage());
			}
		}

	
}
