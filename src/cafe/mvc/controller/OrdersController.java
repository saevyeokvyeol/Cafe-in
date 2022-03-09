package cafe.mvc.controller;

import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Statistics;
import java.util.List;
import java.util.Map;

import cafe.mvc.model.service.OrdersService;
import cafe.mvc.model.service.OrdersServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.SuccessView;

public class OrdersController {
	static OrdersService ordersService = new OrdersServiceImpl();

	/**
	 * �ֹ��ϱ�
	 * */
	public static void orderInsert(Orders orders) {
		try {
			ordersService.orderInsert(orders);
			SuccessView.printMessage("�ֹ��� �Ϸ�Ǿ����ϴ�. ��ø� ��ٷ��ּ���.");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * �ϰ� �Ǹ� ��� �˻�
	 * */
	public static void dailySalesStatistic(String date) {
		try {
			Map<String, Integer> map = ordersService.dailySalesStatistic(date);
			SuccessView.printDailyStatistics(map);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ��ǰ�� �Ǹ� ��� �˻�
	 * */
	public static void productSalesStatistic() {
		try {
			List<Statistics> list = ordersService.productSalesStatistic();
			SuccessView.printProdStatistics(list);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * �ֹ������ڵ庯��
	 */
	public static void orderStateUpdate(Orders orders) {
		try {
			ordersService.orderStateUpdate(orders);
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
			List<Orders> orderList = ordersService.selectOngoingOrder();
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
		List<Orders> list =ordersService.selectByUserTel(userTel);
		SuccessView.printSelectByUserTel(list,userTel);
		}catch(Exception e){
			FailView.errorMessage(e.getMessage());
		}
	}

	
}
