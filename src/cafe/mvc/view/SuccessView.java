package cafe.mvc.view;

import java.util.List;
import java.util.Map;

import cafe.mvc.model.dto.OrderLine;
import cafe.mvc.model.dto.Orders;
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
	/**
	 * 회원의 지난 주문 내역 조회
	 * */
	public static void printSelectByUserTel(List<Orders> list, String userTel) {
		System.out.println("******"+ userTel + " 회원님의 지난 주문 내역");
		for(Orders orders : list) {
			//전화번호,이름,주문수량,상품명,판매가격,가격*주문수량
//			String userName = orders.getUserName();
			
			for(OrderLine orderLine : orders.getOrdelLineList()) {
				int qty = orderLine.getQty();
//				String prodName = orderLine.getProdName();
//				int prodPrice = orderLine.getProdPrice();
				int priceQty = orderLine.getPriceQty();
				
//				System.out.println("번호 : "+ userTel + " | " + userName + "님  | 수량 : " + qty + "  | " + prodName + " | " + prodPrice + "￦ |  총 결제금액 : " + priceQty + "￦");
				
			}
//			
			
			
		}
		
		
	}

	/**
	 * 현재 진행 중인 주문 검색: 픽업 완료, 주문 취소 상태가 아닌 모든 주문 검색
	 * */
	public static void selectOngoingOrder(List<Orders> list) {
		//int주문상태코드, String이름, String상품명,int수량 int판매가격, int가격*주문수량
		System.out.println("0 : 접수대기 | 1 : 주문접수 | 2 : 상품준비중 | 3 : 상품준비완료");
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
//			System.out.println("주문상태코드 :" + stateCode + userName + "님 | 수량 : " + qty + " | " + prodName + " | " + prodPrice + "￦ | 총 결제금액 : " +priceQty + "￦");
		}
		
	}
	
	public static void printSelectProduct(Product product) {
		System.out.println(product.getProdCode() + " | " + product.getProdName());
	}
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	

}
