package cafe.mvc.view;

import java.util.List;
import java.util.Map;

import cafe.mvc.model.dto.OrderLine;
import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Statistics;
import cafe.mvc.model.dto.Users;

public class SuccessView {

	public static void printStatistics(Statistics statistic) {
		System.out.println("***** " + statistic.getDate() + " �ϰ� ���� *****");
		System.out.println("�� �ֹ� Ƚ�� : " + statistic.getDailyOrderTimes() + "��");
		System.out.println("�Ǹ� ��ǰ �� : " + statistic.getDailySalesQty() + "��");
		System.out.print("�ϰ� �� ���� : ");
		System.out.printf("%,d", statistic.getDailySalesPrice());
		System.out.println("��");
	}

	public static void printCart(String userTel, Map<Product, Integer> cart) {
		System.out.println("***** " + userTel + " ���� ��ٱ��� *****");
		int totalPrice = 0;
		for (Product prod : cart.keySet()) {
			String prodCode = prod.getProdCode();
			String prodName = prod.getProdName();
			int prodPrice = prod.getProdPrice();
			int qty = cart.get(prod);
			totalPrice += (prodPrice * qty);

			System.out
					.println(prodCode + " | " + prodName + " | " + prodPrice + " | " + qty + " | " + (prodPrice * qty));
		}
		
		System.out.println("�� ���� : " + totalPrice);
	}

	/**
	 * ȸ���� ���� �ֹ� ���� ��ȸ
	 */
	public static void printSelectByUserTel(List<Orders> list, String userTel) {
		System.out.println("*********" + userTel + " ȸ������ ���� �ֹ� ����");
		System.out.println("\t----*** 0 : eat in  1 : take out ***----");
		System.out.println(
				"�����������������������������������������������������������|");

		for (Orders orders : list) {
			// int orderNum, String userTel, int stateCode, String payMethod, int payPoint,
			// int totalPrice, String orderDate, int takeOut
			int orderNum = orders.getOrderNum();
			// userTel = orders.getUserTel();
			String payMethod = orders.getPayMethod();
			int totalPrice = orders.getTotalPrice();
			String orderDate = orders.getOrderDate();
			int takeOut = orders.getTakeOut();

			System.out.println("�ֹ���ȣ : " + orderNum + "�� | take out ���� : " + takeOut + " ������� : " + payMethod
					+ " | �ֹ� ���� : " + orderDate + " | �� �����ݾ� : " + totalPrice + "��");
//			System.out.println(
//					"�����������������������������������������������������������|");
//			
			for(OrderLine orderline : orders.getOrderLineList()) {
				System.out.println("\t*********�ֹ� ��*********");
				
				int orderLineCode = orderline.getOrderLineCode();
				String prodCode = orderline.getProdCode();
				int qty = orderline.getQty();
				int priceQty = orderline.getPriceQty();
				System.out.println("�ֹ����ڵ� : "+orderLineCode+ " | ��ǰ�ڵ� : "+prodCode+" | ���� : "+qty+" | ");
				System.out.println(
						"�����������������������������������������������������������|");
	
			}	
		}
		
//		Orders orders=new Orders();
//		for(OrderLine orderLine : orders.getOrdelLineList()) {
//			int a = orderLine.getOrderLineCode();
//					System.out.println(a);
//		}
	}

	/**
	 * ���� ���� ���� �ֹ� �˻�: �Ⱦ� �Ϸ�, �ֹ� ��� ���°� �ƴ� ��� �ֹ� �˻�
	 */
	public static void selectOngoingOrder(List<Orders> list) {
		// int�ֹ������ڵ�, String�̸�, String��ǰ��,int���� int�ǸŰ���, int����*�ֹ�����
		System.out.println("�������������������������������|");
		System.out.println("0 : ������� | 1 : �ֹ����� | 2 : ��ǰ�غ��� | 3 : ��ǰ�غ�Ϸ�");
		System.out.println("�������������������������������|");
		for (Orders orders : list) {

			int orderNum = orders.getOrderNum();
			String userTel = orders.getUserTel();
			String payMethod = orders.getPayMethod();
			int totalPrice = orders.getTotalPrice();
			String orderDate = orders.getOrderDate();
			int takeOut = orders.getTakeOut();
			int stateCode = orders.getStateCode();
			System.out.println(
					"---------------------------------------------------------------------------------------------------------|");
			if(userTel == null) {
				
			} else {
				System.out.println("���� �ֹ� ���� : " + stateCode + " | " + userTel + " ȸ���� | ������� : "+payMethod+" | �ֹ� ���� : "+ orderDate +" | �� ���� �ݾ� :"+totalPrice+"��");
			}
		}
		System.out.println(
				"---------------------------------------------------------------------------------------------------------|");
			


	}

	public static void printSelectProduct(Product product) {
		System.out.println(product.getProdCode() + " | " + product.getProdName());
	}
	

	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printByCategory(List<Product> productList) {
		for(Product p : productList) {
			System.out.println(p.getProdCode() + " | " + 
							   p.getProdGroup()+ " | " + 
							   p.getProdName() + " | " + 
							   p.getProdPrice()+ " | " + 
							   p.getProdDetail());
		}
	}
	
	public static void printUsersInfo(Users users) {
		System.out.println(users.getUserName() + " | " + users.getUserTel() + " | " + users.getUserPoint() + " | " + users.getRegDate());
	}
	
}
