package cafe.mvc.view;

import java.util.Map;

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

	public static void printMessage(String message) {
		System.out.println(message);
	}

}
