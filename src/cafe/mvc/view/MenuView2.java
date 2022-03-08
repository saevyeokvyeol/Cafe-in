package cafe.mvc.view;

import java.util.Scanner;

import cafe.mvc.controller.ProductController;
import cafe.mvc.controller.UsersController;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Stock;
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
				MenuView2.orderMenu();
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
					MenuView2.orderMenu();
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
	
	/**
	 * �ֹ� �޴�
	 * */
	public static void orderMenu() {
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
					MenuView2.cartMenu();
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
	public static void categoryMenu() {
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
	public static void adminMenu() {
		while(true) {
			System.out.println("\n" + "������ �޴�");
			System.out.println("[ 1. ��ǰ ��ȸ  |  2. ��ǰ ���  |  3. ��ǰ ����  | 4. ��ǰ ���� ����  |  5. ȸ�� ���� ����  |  6. ���� �ֹ� ��ȸ  |  7. �ֹ� ���� ����  |  8. �ϰ� ���� ���  |  9. �α׾ƿ�  |  0. ���� ]");
			System.out.print("�� ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				break;
			case 2 :
				productInsert();
				break;
			case 3 :
				productUpdate();
				break;
			case 4 :
				productStateUpdate();
				break;
			case 5 :
				break;
			case 6 :
				break;
			case 7 :
				break;
			case 8 :
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
	public static void cartMenu() {
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
	public static void putCart() {
		
	}
	
	/**
	 * ��ٱ��� �κ� ���� �޼ҵ�
	 * */
	public static void deleteCartByCode() {
		
	}
	
	/**
	 * ���� �޼ҵ�
	 * */
	public static void orderInsert() {
		
	}
	
	/**
	 * ��ǰ ��� �޼ҵ�
	 * */
	public static void productInsert() {
		 System.out.print("��ǰ�ڵ� �� ");
		 String prodCode = sc.nextLine();
		 
		 System.out.print("��ǰ�̸� �� ");
		 String prodName = sc.nextLine();
		 
		 System.out.print("��ǰ���� �� ");
		 int prodPrice = Integer.parseInt(sc.nextLine());
		 
		 System.out.print("��ǰ�Ұ� �� ");
		 String prodDetail = sc.nextLine();
		 
		 System.out.print("��ǰ���� �� ");
		 int prodState = Integer.parseInt(sc.nextLine());
		 
		 char group = prodCode.charAt(0);
		 if(group == 'D') { 
			 System.out.print("����Ʈ ��� �� ");
			 int prodStock = Integer.parseInt(sc.nextLine());
			 Product product = new Product(prodCode, null, prodName, prodPrice, prodDetail, prodState);
			 Stock stock = new Stock(prodCode, prodStock);
			 product.setStock(stock);
			 ProductController.dessertInsert(product);
		 } else {
			 ProductController.drinkInsert(new Product(prodCode, null, prodName, prodPrice, prodDetail, prodState));
		 }
	
	}
	
	/**
	 * ��ǰ ���� �޼ҵ�
	 * */
	public static void productUpdate() {
		 System.out.print("��ǰ�ڵ� �� ");
		 String prodCode = sc.nextLine();
		 
		 System.out.print("��ǰ���� �� ");
		 int prodPrice = Integer.parseInt(sc.nextLine());
		 
		 System.out.print("��ǰ�Ұ� �� ");
		 String prodDetail = sc.nextLine();
	
		 
		 
		 //����Ʈ���
		 char group = prodCode.charAt(0);
		 if(group == 'D') { 
			 System.out.print("����Ʈ ��� �� ");
			 int prodStock = Integer.parseInt(sc.nextLine());
			 Product product = new Product(prodCode, null, null, prodPrice, prodDetail, 0);
			 Stock stock = new Stock(prodCode, prodStock);
			 product.setStock(stock);
			 ProductController.dessertStockUpdate(stock);
		 } else {
			 ProductController.productUpdate(new Product(prodCode, null, null, prodPrice, prodDetail, 0));
		 }
		
	}
	
	/**
	 * ��ǰ ���� ���� �޼ҵ�
	 * */
	public static void productStateUpdate() {
		 System.out.print("��ǰ�ڵ� �� ");
		 String prodCode = sc.nextLine();
		 
		 System.out.print("��ǰ���� �� ");
		 int prodState = Integer.parseInt(sc.nextLine());
		 ProductController.productStateUpdate(prodCode, prodState);
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
