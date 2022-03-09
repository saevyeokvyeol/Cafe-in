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
	 * �ֹ��ϱ�
	 * */
	public static void orderInsert(OrdersDTO ordersDTO) {
		try {
			ordersService.orderInsert(ordersDTO);
			SuccessView.printMessage("�ֹ��� �Ϸ�Ǿ����ϴ�. ��ø� ��ٷ��ּ���.");
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
	// �ֹ��ϱ�, �ֹ���������

	/**
	 * �ֹ������ڵ庯��
	 */
	public static void orderStateUpdate(OrdersDTO ordersDTO) {
		try {
			ordersService.orderStateUpdate(ordersDTO);
			SuccessView.printMessage("�ֹ������ڵ带 �����Ͽ����ϴ�.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ������������ �ֹ��˻�
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
		 * ȸ���� ���� �ֹ� ���� ��ȸ
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
