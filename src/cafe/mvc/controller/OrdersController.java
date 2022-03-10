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
	 * �ֹ��ϱ�
	 * */
	public static void orderInsert(OrdersDTO orders) {
		try {
			ordersService.orderInsert(orders);
			SuccessView.printMessage("�ֹ��� �Ϸ�Ǿ����ϴ�. ��ø� ��ٷ��ּ���.");
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
	 * �ϰ� �Ǹ� ��� �˻�
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
	 * ��ǰ�� �Ǹ� ��� �˻�
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
		
		String userName = new UsersServiceImpl().selectByUserTel(userTel).getUserName();
		SuccessView.printSelectByUserTel(list, userName);
		}catch(Exception e){
			FailView.errorMessage(e.getMessage());
		}
	}
}
