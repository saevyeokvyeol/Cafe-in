package cafe.mvc.view;

import java.util.List;
import java.util.Map;

import cafe.mvc.model.dto.OrderLine;
import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Statistics;

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
		for(Product prod : cart.keySet()) {
			String prodCode = prod.getProdCode();
			String prodName = prod.getProdName();
			int prodPrice = prod.getProdPrice();
			int qty = cart.get(prod);
			totalPrice += (prodPrice * qty);
			
			System.out.println(prodCode + " | " + prodName + " | " + prodPrice + " | " + qty + " | " + (prodPrice * qty));
		}
		
		System.out.println("�� ���� : " + totalPrice);
	}
	/**
	 * ȸ���� ���� �ֹ� ���� ��ȸ
	 * */
	public static void printSelectByUserTel(List<Orders> list, String userTel) {
		System.out.println("******"+ userTel + " ȸ������ ���� �ֹ� ����");
		for(Orders orders : list) {
			//��ȭ��ȣ,�̸�,�ֹ�����,��ǰ��,�ǸŰ���,����*�ֹ�����
//			String userName = orders.getUserName();
			
			for(OrderLine orderLine : orders.getOrdelLineList()) {
				int qty = orderLine.getQty();
//				String prodName = orderLine.getProdName();
//				int prodPrice = orderLine.getProdPrice();
				int priceQty = orderLine.getPriceQty();
				
//				System.out.println("��ȣ : "+ userTel + " | " + userName + "��  | ���� : " + qty + "  | " + prodName + " | " + prodPrice + "�� |  �� �����ݾ� : " + priceQty + "��");
				
			}
//			
			
			
		}
		
		
	}

	/**
	 * ���� ���� ���� �ֹ� �˻�: �Ⱦ� �Ϸ�, �ֹ� ��� ���°� �ƴ� ��� �ֹ� �˻�
	 * */
	public static void selectOngoingOrder(List<Orders> list) {
		//int�ֹ������ڵ�, String�̸�, String��ǰ��,int���� int�ǸŰ���, int����*�ֹ�����
		System.out.println("0 : ������� | 1 : �ֹ����� | 2 : ��ǰ�غ��� | 3 : ��ǰ�غ�Ϸ�");
		for(Orders orders : list) {
			
			//String userTel = orders.getUserTel();
			int stateCode = orders.getStateCode();
//			String userName = orders.getUserName();
//			
//			String prodName = orders.getProdName();
//			int qty = orders.getQty();
//			int prodPrice = orders.getProdPrice();
//			int priceQty = orders.getPriceQty();
//			
//			System.out.println("�ֹ������ڵ� :" + stateCode + userName + "�� | ���� : " + qty + " | " + prodName + " | " + prodPrice + "�� | �� �����ݾ� : " +priceQty + "��");
		}
		
	}
	
	public static void printSelectProduct(Product product) {
		System.out.println(product.getProdCode() + " | " + product.getProdName());
	}
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	

}
