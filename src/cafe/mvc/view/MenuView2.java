package cafe.mvc.view;

import java.util.Scanner;

import cafe.mvc.controller.UsersController;
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
