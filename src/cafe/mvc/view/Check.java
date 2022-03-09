package cafe.mvc.view;

import cafe.mvc.controller.OrdersController;
import cafe.mvc.controller.ProductController;

public class Check {

	public static void main(String[] args) {
		OrdersController.dailySalesStatistic("220309");
		
		OrdersController.productSalesStatistic();
		
		ProductController.selectByGroup("D");

	}

}
