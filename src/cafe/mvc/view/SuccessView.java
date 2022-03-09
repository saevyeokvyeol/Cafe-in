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

	public static void printStatistics(StatisticsDTO statistic) {
		System.out.println("***** " + statistic.getDate() + " �ϰ� ���� *****");
		System.out.println("�� �ֹ� Ƚ�� : " + statistic.getDailyOrderTimes() + "��");
		System.out.println("�Ǹ� ��ǰ �� : " + statistic.getDailySalesQty() + "��");
		System.out.print("�ϰ� �� ���� : ");
		System.out.printf("%,d", statistic.getDailySalesPrice());
		System.out.println("��");
	}

	public static void printCart(String userTel, Map<ProductDTO, Integer> cart) {
		System.out.println("***** " + userTel + " ���� ��ٱ��� *****");
		int totalPrice = 0;
		for (ProductDTO prod : cart.keySet()) {
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
	public static void printSelectByUserTel(List<OrdersDTO> list, String userTel) {
		System.out.println("*********" + userTel + " ȸ������ ���� �ֹ� ����");
		System.out.println("\t----*** 0 : eat in  1 : take out ***----");
		System.out.println("�����������������������������������������������������������|");

		for (OrdersDTO ordersDTO : list) {
			// int orderNum, String userTel, int stateCode, String payMethod, int payPoint,
			// int totalPrice, String orderDate, int takeOut
			int orderNum = ordersDTO.getOrderNum();
			// userTel = orders.getUserTel();
			String payMethod = ordersDTO.getPayMethod();
			int totalPrice = ordersDTO.getTotalPrice();
			String orderDate = ordersDTO.getOrderDate();
			int takeOut = ordersDTO.getTakeOut();

			System.out.println("�ֹ���ȣ : " + orderNum + "�� | take out ���� : " + takeOut + " ������� : " + payMethod
					+ " | �ֹ� ���� : " + orderDate + " | �� �����ݾ� : " + totalPrice + "��");
//			System.out.println(
//					"�����������������������������������������������������������|");
//			
			for(OrderLineDTO orderline : ordersDTO.getOrderLineList()) {
				System.out.println("\t*********�ֹ� ��*********");

				int orderLineCode = orderline.getOrderLineCode();
				String prodCode = orderline.getProdCode();
				int qty = orderline.getQty();
				int priceQty = orderline.getPriceQty();
				System.out.println("�ֹ����ڵ� : " + orderLineCode + " | ��ǰ�ڵ� : " + prodCode + " | ���� : " + qty + " | ");
				System.out.println("�����������������������������������������������������������|");

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
	public static void selectOngoingOrder(List<OrdersDTO> list) {
		// int�ֹ������ڵ�, String�̸�, String��ǰ��,int���� int�ǸŰ���, int����*�ֹ�����
		System.out.println("�������������������������������|");
		System.out.println("0 : ������� | 1 : �ֹ����� | 2 : ��ǰ�غ��� | 3 : ��ǰ�غ�Ϸ�");
		System.out.println("�������������������������������|");
		for (OrdersDTO ordersDTO : list) {

			int orderNum = ordersDTO.getOrderNum();
			String userTel = ordersDTO.getUserTel();
			String payMethod = ordersDTO.getPayMethod();
			int totalPrice = ordersDTO.getTotalPrice();
			String orderDate = ordersDTO.getOrderDate();
			int takeOut = ordersDTO.getTakeOut();
			int stateCode = ordersDTO.getStateCode();
			System.out.println(
					"---------------------------------------------------------------------------------------------------------|");
			if (userTel != null) {
				System.out.println("�ֹ���ȣ : "+orderNum+ "�� | ���� �ֹ� ���� : " + stateCode + " | " + userTel + " ȸ���� | ������� : "+payMethod+" | �ֹ� ���� : "+ orderDate +" | �� ���� �ݾ� :"+totalPrice+"��");	
			} 			
		}

		System.out.println(
				"---------------------------------------------------------------------------------------------------------|");
	}


	/**
	 * ��ü ��ǰ ��ȸ
	 */
	public static void printSelectAll(List<ProductDTO> list) {
		System.out.println("*********��ǰ " + list.size() + "��*********");
		for (ProductDTO productDTO : list) {
			String prodCode = productDTO.getProdCode();
			String prodGroup = productDTO.getProdGroup();
			String prodName = productDTO.getProdName();
			int prodPrice = productDTO.getProdPrice();
			String prodDetail = productDTO.getProdDetail();
			int prodState = productDTO.getProdState();

			if (productDTO.getStock() == null) {
				System.out.println(productDTO.getProdCode() + " | " + productDTO.getProdGroup() + " | "
						+ productDTO.getProdName() + " | " + productDTO.getProdPrice() + " | " + productDTO.getProdDetail()
						+ " | " + productDTO.getProdState());
			} else
				System.out.println(productDTO.getProdCode() + " | " + productDTO.getProdGroup() + " | "
						+ productDTO.getProdName() + " | " + productDTO.getProdPrice() + " | " + productDTO.getProdDetail()
						+ " | " + productDTO.getProdState() + " | " + productDTO.getStock().getProdStock());
		}
	}

	public static void printSelectProduct(ProductDTO productDTO) {
		System.out.println(productDTO.getProdCode() + " | " + productDTO.getProdName());
	}

	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printByCategory(List<ProductDTO> productList) {
		for (ProductDTO p : productList) {
			System.out.println(p.getProdCode() + " | " + p.getProdGroup() + " | " + p.getProdName() + " | "
					+ p.getProdPrice() + " | " + p.getProdDetail());
		}
	}
	
	public static void printUsersInfo(UsersDTO usersDTO) {
		System.out.println(usersDTO.getUserName() + " | " + usersDTO.getUserTel() + " | " + usersDTO.getUserPoint() + " | " + usersDTO.getRegDate());
	}
}
