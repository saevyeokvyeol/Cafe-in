package cafe.mvc.view;

import java.util.List;
import java.util.Map;

import javax.swing.ListCellRenderer;

import cafe.mvc.model.dto.OrderLineDTO;
import cafe.mvc.model.dto.OrdersDTO;
import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.model.dto.StatisticsDTO;
import cafe.mvc.model.dto.UsersDTO;

public class SuccessView {
	/**
	 * 일간 판매 통계 출력
	 * */
	public static void printDailyStatistics(Map<String, Integer> map) {
		System.out.println("***** " + map.get("date") + " 일간 판매 통계 *****");
		System.out.println("판매 음료 수 : " + map.get("drinkSalesQty") + "개");
		System.out.println("판매 디저트 수 : " + map.get("dessertSalesQty") + "개");
		System.out.println("총 주문 횟수 : " + (map.get("orderTimes")) + "건");
		System.out.print("일간 총 매출 : ");
		System.out.printf("%,d", map.get("salesPrice"));
		System.out.println("원");
	}
	
	/**
	 * 제품별 판매 통계 출력
	 * */
	public static void printProdStatistics(List<StatisticsDTO> list) {
		System.out.println("***** 제품별 판매 통계 *****");
		String prodGroup = "";
		for(StatisticsDTO statistics : list) {
			String prodCode = statistics.getProdCode().substring(0, 1);
			if(!prodGroup.equals(prodCode)) {
				System.out.println();
				prodGroup = prodCode;
			}
			
			System.out.print(statistics.getProdCode() + " | " + statistics.getProdName() + " | ￦");
			System.out.printf("%,d", statistics.getProdPrice());
			System.out.print(" * " + statistics.getSalesQty() + "개 | ￦");
			System.out.printf("%,d\n", statistics.getSalesPrice());
		}
	}

	/**
	 * 장바구니 출력
	 * */
	public static void printCart(String userName, Map<ProductDTO, Integer> cart) {
		System.out.println("***** " + userName + " 님의 장바구니 *****");
		int totalPrice = 0;
		for (ProductDTO prod : cart.keySet()) {
			String prodCode = prod.getProdCode();
			String prodName = prod.getProdName();
			int prodPrice = prod.getProdPrice();
			int qty = cart.get(prod);
			totalPrice += (prodPrice * qty);

			System.out.println(prodCode + " | " + prodName + " | " + prodPrice + " | " + qty + " | " + (prodPrice * qty));
		}
		System.out.println("----------------------------------------");

		System.out.println("\t\t\t총 가격 : " + totalPrice);
	}

	/**
	 * 회원의 지난 주문 내역 조회
	 */
	public static void printSelectByUserTel(List<OrdersDTO> list, String userName) {
		
		
		System.out.println("***** " + userName + " 회원 님의 지난 주문 내역 *****");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		for (OrdersDTO ordersDTO : list) {
			int orderNum = ordersDTO.getOrderNum();
			String payMethod = ordersDTO.getPayMethod();
			int totalPrice = ordersDTO.getTotalPrice();
			String orderDate = ordersDTO.getOrderDate();
			String takeOut = null;
			if(ordersDTO.getTakeOut() == 0) {
				takeOut = "매장";
			} else {
				takeOut = "포장";
			}

			System.out.print("주문번호 : " + orderNum + " | " + payMethod + " | " + takeOut + " | " + orderDate + " | ￦");
			System.out.printf("%,d", totalPrice);
			System.out.println();
//			
			for(OrderLineDTO orderLine : ordersDTO.getOrderLineList()) {

				String prodCode = orderLine.getProdCode();
				String prodName = orderLine.getProduct().getProdName();
				int qty = orderLine.getQty();
				int prodPrice = orderLine.getProduct().getProdPrice();
				int priceQty = orderLine.getPriceQty();
				
				
				System.out.print("\t│ " + prodCode +  " | " + prodName + " | " + qty + " * ￦");
				System.out.printf("%,d", prodPrice);
				System.out.print(" | ￦");
				System.out.printf("%,d\n", priceQty);

			}
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		}
	}

	/**
	 * 현재 진행 중인 주문 검색: 픽업 완료, 주문 취소 상태가 아닌 모든 주문 검색
	 */
	public static void selectOngoingOrder(List<OrdersDTO> list) {
		// int주문상태코드, String이름, String상품명,int수량 int판매가격, int가격*주문수량
		for (OrdersDTO orders : list) {

			int orderNum = orders.getOrderNum();
			String userTel = orders.getUserTel();
			String orderDate = orders.getOrderDate();
			
			String stateCode = null;
			if(orders.getStateCode() == 0) {
				stateCode = "접수 대기";
			} else if(orders.getStateCode() == 1) {
				stateCode = "주문 접수";
			} else if(orders.getStateCode() == 2) {
				stateCode = "상품 준비 중";
			} else {
				stateCode = "상품 준비 완료";
			}
			System.out.println("----------------------------------------------------------------------");
			System.out.println(stateCode + " | " + orderNum+ " | " + orderDate);
			
			for(OrderLineDTO orderLine : orders.getOrderLineList()) {
				int orderLineCode = orderLine.getOrderLineCode();
				String prodCode = orderLine.getProdCode();
				String prodName = orderLine.getProduct().getProdName();
				int qty = orderLine.getQty();
				
				
				System.out.println("\t" + orderLineCode +  " | " + prodCode + " | " + prodName + " | " + qty + "개");
			}
		
		} 

		System.out.println(
				"----------------------------------------------------------------------");
	}


	/**
	 * 전체 상품 조회
	 */
	public static void printSelectAll(List<ProductDTO> list) {
		System.out.println("***** 총 " + list.size() + "개의 상품이 있습니다 *****");

		String prodGroup = "";

		for (ProductDTO product : list) {
			
			String prodState = null;
			if(product.getProdState() == 0) {
				prodState = "판매 중지";
			} else if(product.getProdState() == 1) {
				prodState = "판매중";
			} else {
				prodState = "일시 품절";
			}
			
			if(!prodGroup.equals(product.getProdGroup())) {
				System.out.println();
				prodGroup = product.getProdGroup();
			}
			
			System.out.print(product.getProdCode() + " | " + product.getProdName() + " | ");
			System.out.printf("%,d", product.getProdPrice());
			System.out.print(" | " + prodState);
			if(product.getStock() != null) {
				System.out.print(" | 남은 재고 : " + product.getStock().getProdStock());
			}
			System.out.println();
			
		}
		
	}

	/**
	 * 코드로 검색한 상품 출력
	 * */
	public static void printSelectProduct(ProductDTO productDTO) {
		System.out.println(productDTO.getProdCode() + " | " + productDTO.getProdName());
	}

	/**
	 * 메시지 출력
	 * */
	public static void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * 카테고리별 상품 출력
	 * */
	public static void printByCategory(List<ProductDTO> productList) {
		for (ProductDTO product : productList) {
			
			if(product.getProdState() == 1) {
				System.out.println(product.getProdCode() + " | " + product.getProdName() + " | " + product.getProdPrice() + " | " + product.getProdDetail());
			} else {
				System.out.println(product.getProdCode() + " | " + product.getProdName() + " | 일시 품절");
			}
		}
	}
	
	/**
	 * 전화번호로 검색한 회원 정보 출력
	 * */
	public static void printUsersInfo(UsersDTO users) {
		System.out.println("\n" + "***** " + users.getUserName() + " 회원님의 정보를 조회합니다 *****");
		System.out.println(users.getUserName() + " | " + users.getUserTel() + " | " + users.getUserPoint() + " | " + users.getRegDate());
	}
	
	/**
	 * 전체 회원 리스트 출력
	 * */
	public static void printUserSelectAll(List<UsersDTO> list) {
		System.out.println("\n" + "***** 전체 회원 리스트를 조회합니다 *****");
		for(UsersDTO users : list) {
			System.out.print(users.getUserName() + " | " + users.getUserTel() + " | 적립금 : ");
			System.out.printf("%,d", users.getUserPoint());
			System.out.println(" | " + users.getRegDate());
			
		}
	}
}
