package cafe.mvc.controller;

import java.util.List;

import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.service.OrdersService;
import cafe.mvc.model.service.OrdersServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.SuccessView;

public class OrdersController {
	//�ֹ��ϱ�, �ֹ���������
	
		private static OrdersService ordersService = new OrdersServiceImpl();
		/**
		 * �ֹ������ڵ庯��
		 * */
		public static void orderStateUpdate(Orders orders) {
			try {
			ordersService.orderStateUpdate(orders);
			SuccessView.printMessage("�ֹ������ڵ带 �����Ͽ����ϴ�.");
			}catch(Exception e) {
				FailView.errorMessage(e.getMessage());
			}
		}
		
		
		/**
		 * ������������ �ֹ��˻�
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
		 * ȸ���� ���� �ֹ� ���� ��ȸ
		 * */
		
		
	

}
