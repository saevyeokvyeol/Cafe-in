package cafe.mvc.view;

import java.util.List;

import cafe.mvc.model.dto.Orders;


public class SuccessView {

	public static void messagePrint(String message) {
		System.out.println(message);
	}

	public static void printViewSelectOrder(/*String field,*/List<Orders> orderList) {
		System.out.println("-----------현재 "+ /*field +*/"상태입니다-------------");
		
		
		System.out.println();
		
		
	}

	public static void printViewSelectByOrder(String userTel, List<Orders> list) {
		System.out.println("-------"+userTel+"님의 지난 주문내역은... ");
		
		for(Orders orders : list) {
			System.out.println(orders);
		}
		
		System.out.println();
		
	}

}
