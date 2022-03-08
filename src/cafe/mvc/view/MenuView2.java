package cafe.mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cafe.mvc.controller.CartController;
import cafe.mvc.controller.OrdersController;
import cafe.mvc.controller.UsersController;
import cafe.mvc.model.dto.OrderLine;
import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Product;
import cafe.mvc.session.Session;
import cafe.mvc.session.SessionSet;

public class MenuView2 {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * ���� �޴�
	 * */
	public static void mainMenu() {
		while(true) {
			System.out.println("[ 1. ȸ������  |  2. �α���  |  3. ��ȸ���ֹ�  |  0. ���� ]");
			System.out.print("�� ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				break;
			case 2 :
				MenuView2.login();
				break;
			case 4 : 
				MenuView2.orderMenu(null, null);
				break;
			case 0 : 
				System.exit(0);
			}
		}
	}
	
	/**
	 * �α��� ���� �޴�
	 * */
	public static void userMenu(String userTel, String userName) {
		while(true) {
			System.out.println("\n" + userName + " �� �������.");
			System.out.println("[ 1. �ֹ��ϱ�  |  2. ���� �ֹ� ����  |  3. ������ Ȯ��  |  9. �α׾ƿ�  |  0. ���� ]");
			System.out.print("�� ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					MenuView2.orderInsert(userTel);
					// MenuView2.orderMenu(userTel, userName);
					break;
				case 2 :
					MenuView2.putCart(userTel);
					break;
				case 3 :
					CartController.viewCart(userTel);
					break;
				case 9 :
					MenuView2.deleteCartByCode(userTel);
					return;
				case 0 : 
					CartController.deleteCartAll(userTel);
			}
		}
		
	}
	
	/**
	 * �ֹ� �޴�
	 * */
	public static void orderMenu(String userTel, String userName) {
		while(true) {
			System.out.println("\n" + "[ 1. Ŀ�� �޴� ����  |  2. ������ �޴� ����  |  3. �� �޴� ����  |  4. ����Ʈ �޴� ����  |  5. ��ٱ��� ����  |  9. �ڷ� ����  |  0. ���� ]");
			System.out.print("�� ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					break;
				case 2 :
					break;
				case 3 :
					break;
				case 4 :
					break;
				case 5 :
					MenuView2.cartMenu(userTel, userName);
				case 9 :
					return;
				case 0 :
					//����
			}
		}
	}
	
	/**
	 * ī�װ��� ��ǰ ������
	 * */
	public static void categoryMenu(String userTel, String userName) {
		while(true) {
			System.out.println("\n" + "[ 1. ��ٱ��Ͽ� ��ǰ ���  |  9. �ڷ� ����  |  0. ���� ]");
			System.out.print("�� ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					break;
				case 9 :
					return;
				case 0 :
					//����
			}
		}
	}
    
    /**
     * ������ �޴�
     * */
	public static void adminMenu(String userTel, String userName) {
		while(true) {
			System.out.println("\n" + "������ �޴�");
			System.out.println("[ 1. ��ǰ ��ȸ  |  2. ��ǰ ���  |  3. ��ǰ ����  |  4. ȸ�� ���� ����  |  5. ���� �ֹ� ��ȸ  |  6. �ֹ� ���� ����  |  7. �ϰ� ���� ���  |  9. �α׾ƿ�  |  0. ���� ]");
			System.out.print("�� ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				break;
			case 2 :
				break;
			case 3 :
				break;
			case 4 :
				break;
			case 5 :
				break;
			case 6 :
				break;
			case 7 :
				break;
			case 9 : 
				return;
			case 0 : 
				System.exit(0);
			}
		}
	}
    
    /**
     * ��ٱ��� �޴�
     * */
	public static void cartMenu(String userTel, String userName) {
		while(true) {
			System.out.println("\n" + "[ 1. ����  |  2. ��ٱ��� ��ǰ ����  |  3. ��ٱ��� ��ü ����  |  9. �ڷ� ����  |  0. ���� ]");
			System.out.print("�� ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				break;
			case 2 :
				break;
			case 3 :
				break;
			case 9 : 
				return;
			case 0 : 
				System.exit(0);
			}
		}
	}
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * �α��� �޼ҵ�
	 * */
	public static void login() {
		System.out.println("��ȭ��ȣ�� �Է����ּ���");
		System.out.print("�� ");
		String userTel = sc.nextLine();
		
		System.out.println("��й�ȣ�� �Է����ּ���");
		System.out.print("�� ");
		int userPwd = Integer.parseInt(sc.nextLine());
		
		UsersController.login(userTel, userPwd);
	}
	
	/**
	 * ������ �α��� �޼ҵ�
	 * */
	public static void adminLogin() {
		
	}

	/**
	 * �α׾ƿ� �޼ҵ�
	 * */
	public static void logout() {
		
	}
	
	/**
	 * ȸ������ �޼ҵ�
	 * */
	public static void ȸ������() {
		
	}
	
	/**
	 * ��ٱ��� �߰� �޼ҵ�
	 * */
	public static void putCart(String userTel) {
		System.out.println("��ٱ��Ͽ� �߰��� ��ǰ �ڵ带 �Է����ּ���");
		System.out.print("�� ");
		String prodCode = sc.nextLine();
		
		System.out.println("���Ͻô� ��ǰ ������ �Է����ּ���.");
		System.out.print("�� ");
		int qty = Integer.parseInt(sc.nextLine());
		
		CartController.putCart(userTel, prodCode, qty);
	}
	
	/**
	 * ��ٱ��� �κ� ���� �޼ҵ�
	 * */
	public static void deleteCartByCode(String userTel) {
		System.out.println("��ٱ��Ͽ��� ������ ��ǰ �ڵ带 �Է����ּ���");
		System.out.print("�� ");
		String prodCode = sc.nextLine();
		
		CartController.deleteCartByCode(userTel, prodCode);
	}
	
	/**
	 * ���� �޼ҵ�
	 * */
	public static void orderInsert(String userTel) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(userTel);
		
		int payPoint = 0;
		
		if(userTel != null) {
			System.out.println("����Ͻ� ������ �׼��� �Է����ּ���.");
			System.out.print("�� ");
			payPoint = Integer.parseInt(sc.nextLine());
		}
		
		System.out.println("���� ����� �������ּ���.");
		System.out.println("[ ī�� / ���� ]");
		System.out.print("�� ");
		String payMethod = sc.nextLine();
		
		System.out.println("���� ���θ� �������ּ���.");
		System.out.println("[ 0. ���忡�� �԰� ���Կ�  |  1. ������ �ּ��� ]");
		System.out.print("�� ");
		int takeout = Integer.parseInt(sc.nextLine());
		
		List<OrderLine> list = new ArrayList<OrderLine>();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttributes("cart");
		
		Orders orders = new Orders(0, userTel, 0, payMethod, payPoint, 0, null, takeout);
		orders.setOrdelLineList(list);
		
		OrdersController.orderInsert(orders);
		
	}
	
	/**
	 * ��ǰ ��� �޼ҵ�
	 * */
	public static void productInsert() {
		
	}
	
	/**
	 * ��ǰ ���� �޼ҵ�
	 * */
	public static void productUpdate() {
		
	}
	
	/**
	 * ȸ�� ��й�ȣ ���� �޼ҵ�
	 * */
	public static void userUpdate() {
		
	}
	
	/**
	 * �ֹ� ���� ���� �޼ҵ�
	 * */
	public static void orderStateUpdate() {
		
	}
}
