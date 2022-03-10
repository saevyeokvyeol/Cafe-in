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
	 * �ϰ� �Ǹ� ��� ���
	 * */
	public static void printDailyStatistics(Map<String, Integer> map) {
		System.out.println("***** " + map.get("date") + " �ϰ� �Ǹ� ��� *****");
		System.out.println("�Ǹ� ���� �� : " + map.get("drinkSalesQty") + "��");
		System.out.println("�Ǹ� ����Ʈ �� : " + map.get("dessertSalesQty") + "��");
		System.out.println("�� �ֹ� Ƚ�� : " + (map.get("orderTimes")) + "��");
		System.out.print("�ϰ� �� ���� : ");
		System.out.printf("%,d", map.get("salesPrice"));
		System.out.println("��");
	}
	
	/**
	 * ��ǰ�� �Ǹ� ��� ���
	 * */
	public static void printProdStatistics(List<StatisticsDTO> list) {
		System.out.println("***** ��ǰ�� �Ǹ� ��� *****");
		String prodGroup = "";
		for(StatisticsDTO statistics : list) {
			String prodCode = statistics.getProdCode().substring(0, 1);
			if(!prodGroup.equals(prodCode)) {
				System.out.println();
				prodGroup = prodCode;
			}
			
			System.out.print(statistics.getProdCode() + " | " + statistics.getProdName() + " | ��");
			System.out.printf("%,d", statistics.getProdPrice());
			System.out.print(" * " + statistics.getSalesQty() + "�� | ��");
			System.out.printf("%,d\n", statistics.getSalesPrice());
		}
	}

	/**
	 * ��ٱ��� ���
	 * */
	public static void printCart(String userName, Map<ProductDTO, Integer> cart) {
		System.out.println("***** " + userName + " ���� ��ٱ��� *****");
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

		System.out.println("\t\t\t�� ���� : " + totalPrice);
	}

	/**
	 * ȸ���� ���� �ֹ� ���� ��ȸ
	 */
	public static void printSelectByUserTel(List<OrdersDTO> list, String userName) {
		
		
		System.out.println("***** " + userName + " ȸ�� ���� ���� �ֹ� ���� *****");
		System.out.println("�����������������������������������������������������������");

		for (OrdersDTO ordersDTO : list) {
			int orderNum = ordersDTO.getOrderNum();
			String payMethod = ordersDTO.getPayMethod();
			int totalPrice = ordersDTO.getTotalPrice();
			String orderDate = ordersDTO.getOrderDate();
			String takeOut = null;
			if(ordersDTO.getTakeOut() == 0) {
				takeOut = "����";
			} else {
				takeOut = "����";
			}

			System.out.print("�ֹ���ȣ : " + orderNum + " | " + payMethod + " | " + takeOut + " | " + orderDate + " | ��");
			System.out.printf("%,d", totalPrice);
			System.out.println();
//			
			for(OrderLineDTO orderLine : ordersDTO.getOrderLineList()) {

				String prodCode = orderLine.getProdCode();
				String prodName = orderLine.getProduct().getProdName();
				int qty = orderLine.getQty();
				int prodPrice = orderLine.getProduct().getProdPrice();
				int priceQty = orderLine.getPriceQty();
				
				
				System.out.print("\t�� " + prodCode +  " | " + prodName + " | " + qty + " * ��");
				System.out.printf("%,d", prodPrice);
				System.out.print(" | ��");
				System.out.printf("%,d\n", priceQty);

			}
			System.out.println("�����������������������������������������������������������");
		}
	}

	/**
	 * ���� ���� ���� �ֹ� �˻�: �Ⱦ� �Ϸ�, �ֹ� ��� ���°� �ƴ� ��� �ֹ� �˻�
	 */
	public static void selectOngoingOrder(List<OrdersDTO> list) {
		// int�ֹ������ڵ�, String�̸�, String��ǰ��,int���� int�ǸŰ���, int����*�ֹ�����
		for (OrdersDTO orders : list) {

			int orderNum = orders.getOrderNum();
			String userTel = orders.getUserTel();
			String orderDate = orders.getOrderDate();
			
			String stateCode = null;
			if(orders.getStateCode() == 0) {
				stateCode = "���� ���";
			} else if(orders.getStateCode() == 1) {
				stateCode = "�ֹ� ����";
			} else if(orders.getStateCode() == 2) {
				stateCode = "��ǰ �غ� ��";
			} else {
				stateCode = "��ǰ �غ� �Ϸ�";
			}
			System.out.println("----------------------------------------------------------------------");
			System.out.println(stateCode + " | " + orderNum+ " | " + orderDate);
			
			for(OrderLineDTO orderLine : orders.getOrderLineList()) {
				int orderLineCode = orderLine.getOrderLineCode();
				String prodCode = orderLine.getProdCode();
				String prodName = orderLine.getProduct().getProdName();
				int qty = orderLine.getQty();
				
				
				System.out.println("\t" + orderLineCode +  " | " + prodCode + " | " + prodName + " | " + qty + "��");
			}
		
		} 

		System.out.println(
				"----------------------------------------------------------------------");
	}


	/**
	 * ��ü ��ǰ ��ȸ
	 */
	public static void printSelectAll(List<ProductDTO> list) {
		System.out.println("***** �� " + list.size() + "���� ��ǰ�� �ֽ��ϴ� *****");

		String prodGroup = "";

		for (ProductDTO product : list) {
			
			String prodState = null;
			if(product.getProdState() == 0) {
				prodState = "�Ǹ� ����";
			} else if(product.getProdState() == 1) {
				prodState = "�Ǹ���";
			} else {
				prodState = "�Ͻ� ǰ��";
			}
			
			if(!prodGroup.equals(product.getProdGroup())) {
				System.out.println();
				prodGroup = product.getProdGroup();
			}
			
			System.out.print(product.getProdCode() + " | " + product.getProdName() + " | ");
			System.out.printf("%,d", product.getProdPrice());
			System.out.print(" | " + prodState);
			if(product.getStock() != null) {
				System.out.print(" | ���� ��� : " + product.getStock().getProdStock());
			}
			System.out.println();
			
		}
		
	}

	/**
	 * �ڵ�� �˻��� ��ǰ ���
	 * */
	public static void printSelectProduct(ProductDTO productDTO) {
		System.out.println(productDTO.getProdCode() + " | " + productDTO.getProdName());
	}

	/**
	 * �޽��� ���
	 * */
	public static void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * ī�װ��� ��ǰ ���
	 * */
	public static void printByCategory(List<ProductDTO> productList) {
		for (ProductDTO product : productList) {
			
			if(product.getProdState() == 1) {
				System.out.println(product.getProdCode() + " | " + product.getProdName() + " | " + product.getProdPrice() + " | " + product.getProdDetail());
			} else {
				System.out.println(product.getProdCode() + " | " + product.getProdName() + " | �Ͻ� ǰ��");
			}
		}
	}
	
	/**
	 * ��ȭ��ȣ�� �˻��� ȸ�� ���� ���
	 * */
	public static void printUsersInfo(UsersDTO users) {
		System.out.println("\n" + "***** " + users.getUserName() + " ȸ������ ������ ��ȸ�մϴ� *****");
		System.out.println(users.getUserName() + " | " + users.getUserTel() + " | " + users.getUserPoint() + " | " + users.getRegDate());
	}
	
	/**
	 * ��ü ȸ�� ����Ʈ ���
	 * */
	public static void printUserSelectAll(List<UsersDTO> list) {
		System.out.println("\n" + "***** ��ü ȸ�� ����Ʈ�� ��ȸ�մϴ� *****");
		for(UsersDTO users : list) {
			System.out.print(users.getUserName() + " | " + users.getUserTel() + " | ������ : ");
			System.out.printf("%,d", users.getUserPoint());
			System.out.println(" | " + users.getRegDate());
			
		}
	}
}
