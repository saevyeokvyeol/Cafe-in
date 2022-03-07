package cafe.mvc.controller;

import java.util.List;

import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.service.OrdersService;
import cafe.mvc.model.service.OrdersServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.SuccessView;

public class OrdersController {
	//주문하기, 주문내역보기
	
		private static OrdersService ordersService = new OrdersServiceImpl();
		/**
		 * 주문상태코드변경
		 * */
		public static void orderStateUpdate(Orders orders) {
			try {
			ordersService.orderStateUpdate(orders);
			SuccessView.printMessage("주문상태코드를 변경하였습니다.");
			}catch(Exception e) {
				FailView.errorMessage(e.getMessage());
			}
		}
		
		
		/**
		 * 현재진행중인 주문검색
		 * */
		public static void selectOngoingOrder() {
			try {
			List<Orders> orderList=ordersService.selectOngoingOrder();
			//SuccessView.(orderList);
			
			}catch(Exception e) {
				FailView.errorMessage(e.getMessage());
			}
		}
		
		/**
		 * 회원의 지난 주문 내역 조회
		 * */
		
		
	

}
