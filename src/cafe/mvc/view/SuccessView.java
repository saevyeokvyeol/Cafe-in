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
		for (Product prod : cart.keySet()) {
			String prodCode = prod.getProdCode();
			String prodName = prod.getProdName();
			int prodPrice = prod.getProdPrice();
			int qty = cart.get(prod);
			totalPrice += (prodPrice * qty);

			System.out
					.println(prodCode + " | " + prodName + " | " + prodPrice + " | " + qty + " | " + (prodPrice * qty));
		}
		
		System.out.println("총 가격 : " + totalPrice);
	}

	/**
	 * 회원의 지난 주문 내역 조회
	 */
	public static void printSelectByUserTel(List<Orders> list, String userTel) {
		System.out.println("*********" + userTel + " 회원님의 지난 주문 내역");
		System.out.println("\t----*** 0 : eat in  1 : take out ***----");
		System.out.println(
				"〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓|");

		for (Orders orders : list) {
			// int orderNum, String userTel, int stateCode, String payMethod, int payPoint,
			// int totalPrice, String orderDate, int takeOut
			int orderNum = orders.getOrderNum();
			// userTel = orders.getUserTel();
			String payMethod = orders.getPayMethod();
			int totalPrice = orders.getTotalPrice();
			String orderDate = orders.getOrderDate();
			int takeOut = orders.getTakeOut();

			System.out.println("주문번호 : " + orderNum + "번 | take out 여부 : " + takeOut + " 결제방법 : " + payMethod
					+ " | 주문 일자 : " + orderDate + " | 총 결제금액 : " + totalPrice + "￦");
//			System.out.println(
//					"〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓|");
//			
			for(OrderLine orderline : orders.getOrderLineList()) {
				System.out.println("\t*********주문 상세*********");
				
				int orderLineCode = orderline.getOrderLineCode();
				String prodCode = orderline.getProdCode();
				int qty = orderline.getQty();
				int priceQty = orderline.getPriceQty();
				System.out.println("주문상세코드 : "+orderLineCode+ " | 상품코드 : "+prodCode+" | 수량 : "+qty+" | ");
				System.out.println(
						"〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓|");
	
			}	
		}
		
//		Orders orders=new Orders();
//		for(OrderLine orderLine : orders.getOrdelLineList()) {
//			int a = orderLine.getOrderLineCode();
//					System.out.println(a);
//		}
	}

	/**
	 * 현재 진행 중인 주문 검색: 픽업 완료, 주문 취소 상태가 아닌 모든 주문 검색
	 */
	public static void selectOngoingOrder(List<Orders> list) {
		// int주문상태코드, String이름, String상품명,int수량 int판매가격, int가격*주문수량
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓|");
		System.out.println("0 : 접수대기 | 1 : 주문접수 | 2 : 상품준비중 | 3 : 상품준비완료");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓|");
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
				System.out.println("현재 주문 상태 : " + stateCode + " | " + userTel + " 회원님 | 결제방법 : "+payMethod+" | 주문 일자 : "+ orderDate +" | 총 결제 금액 :"+totalPrice+"￦");
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
