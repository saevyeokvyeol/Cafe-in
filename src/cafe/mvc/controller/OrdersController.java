package cafe.mvc.controller;

import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Statistics;
import cafe.mvc.model.service.OrdersService;
import cafe.mvc.model.service.OrdersServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.SuccessView;

public class OrdersController {
	static OrdersService ordersService = new OrdersServiceImpl();


	public static void orderInsert(Orders orders) {
		try {
			ordersService.orderInsert(orders);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	

	public static void dailySalesStatistic(String date){
		try {
			Statistics statistic = ordersService.dailySalesStatistic(date);
			SuccessView.printStatistics(statistic);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
}
