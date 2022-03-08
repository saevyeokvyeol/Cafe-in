package cafe.mvc.view;

import java.util.Map;

import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Statistics;

public class SuccessView {
	
	public static void printStatistics(Statistics statistic) {
		System.out.println("***** " + statistic.getDate() + " 일간 매출 *****");
		System.out.println("총 주문 횟수 : " + statistic.getDailyOrderTimes() + "건");
		System.out.println("판매 상품 수 : " + statistic.getDailySalesQty() + "개");
		System.out.print("일간 총 매출 : ");
		System.out.printf("%,d", statistic.getDailySalesPrice());
		System.out.println("원");
	}
	
	public static void printCart(String userTel, Map<Product, Integer> cart) {
		System.out.println("***** " + userTel + " 님의 장바구니 *****");
		int totalPrice = 0;
		for(Product prod : cart.keySet()) {
			String prodCode = prod.getProdCode();
			String prodName = prod.getProdName();
			int prodPrice = prod.getProdPrice();
			int qty = cart.get(prod);
			totalPrice += (prodPrice * qty);
			
			System.out.println(prodCode + " | " + prodName + " | " + prodPrice + " | " + qty + " | " + (prodPrice * qty));
		}
		System.out.println("총 가격 : " + totalPrice);
	}

	public static void printMessage(String message) {
		System.out.println(message);
	}

}
