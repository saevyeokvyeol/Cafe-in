package cafe.mvc.view;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import cafe.mvc.controller.OrdersController;
import cafe.mvc.controller.UsersController;
import cafe.mvc.model.dto.Users;
import cafe.mvc.session.SessionSet;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			//session :��ȭ��ȣ�� �α����ϸ� �̸������ָ� ������..?
			
			MenuView.printMenu();
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView.register();
				break;
			case 2 :

				// �α���
				MenuView.login();


				break;
			case 4 : 
				MenuView.adminMenu();
			case 9 : 
				System.exit(0);
			}
		}
	}
	
	public static void printMenu() {
		System.out.println("=== Cafe-in ī�信 ���Ű� ȯ���մϴ� ==="); //��Ʈ �̻۰ɷ�...?
		System.out.println("1. ȸ������   |   2. ȸ���ֹ�   |  3. ��ȸ���ֹ�  |  4. �����ڼ���  |  9. ����");
	}
	
	public static void register() {
		System.out.print("��ȭ��ȣ ex)010-1111-1111 : ");
		String userTel = sc.nextLine();
		
		System.out.print("�̸� : ");
		String userName = sc.nextLine();
		 
		System.out.print("��й�ȣ : ");
		int userPwd = Integer.parseInt(sc.nextLine());	
		
		Users users = new Users(userTel, userName, userPwd);
		UsersController.registor(users);
	}
	
	//ȸ������ �α����ؾߺ��̴� ȭ��

	public static void printUserMenu(String userTel, String userName) {

		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			System.out.println("����ʽÿ�. " +userName+ " ��"); //��ȭ��ȣ���� ȸ���̸����̰� �ϰ����..
			System.out.println("  1.�α׾ƿ� |  2.����������  |  3.�޴�����  |  4.�ֹ��ϱ�  |  5.�ֹ����  | 6.������ Ȯ�� ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					//�α׾ƿ�
					return;
				case 2 :
					//����������(������Ȯ��,�����ֹ�����)
					MenuView.pwdUpdate(userTel);
					break;
				case 3 :
					//�޴�����
					break;
				case 4 :
					//�ֹ��ϱ�(1.Ŀ�� 2.Ƽ 3.������ 4.����Ʈ 5.��ٱ��ϴ�� 6.�����ϱ� �� �����ϸ��..��while��...?��)
					break;
				case 5 :
					//�ֹ����(�����Ȱ� ���)
					break;
				case 6 :
					//������Ȯ��
					MenuView.userPointCh(userTel);
					break;
				}
		}
		
	}
	public static void userPointCh(String userTel) {
		Users users = new Users(userTel, null, 0);
		UsersController.userPointCh(users);
	}
	public static void pwdUpdate(String userTel) {
		System.out.print("�����Һ�й�ȣ : ");
		int userPwd = Integer.parseInt(sc.nextLine());
		
		Users users = new Users(userTel, null, userPwd);
		UsersController.pwdUpdate(users);
	}
	
	//��ȸ���� ���� ȭ��
	public static void printNotUserMenu() {
		while(true) {
			System.out.println("����ʽÿ�."); //��ȸ������ �ϴ¸�..
			System.out.println("  1.�޴����� |  2.�ֹ��ϱ�  |  9.����  ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					//�޴�����
					break;
				case 2 :
					//�ֹ��ϱ�(1.Ŀ�� 2.Ƽ 3.������ 4.����Ʈ 5.��ٱ��ϴ�� 6.�����ϱ� �� �����ϸ��..��while��...?��)
					break;
				case 3 :
					//����
					break;
				}
		}
		
	}

	
//	//����������
//	public static void ���������� �޼ҵ� �̸��ο�() {
//		System.out.println("1. ������Ȯ��  |  2. �����ֹ�����   | 9. ������");
//	}
//    
//    //�����ڸ޴�
	public static void adminMenu() {
		System.out.println("������ �޴�");
		System.out.println("  1.��ǰ���  |  2.��ǰ����  |  3.��ǰ����  |  4.�ֹ����º���  |  5.ȸ����������  | 6. �ϰ� ���� ��ȸ |  9. ������  ");
		System.out.print("�޴� �Է� > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
		case 1 :
			// ����
			break;
		case 2 :
			break;
		case 6 : 
			String date = new SimpleDateFormat("yyMMdd").format(new Date());
			//OrdersController.dailySalesStatistic(date);
			break;
		case 9 : 
			System.exit(0);
		}
	}
		
	
    //�α���
	public static void login() {
		 System.out.println("��ȭ��ȣ�� �Է����ּ���");
		 System.out.print("�� ");
		 String userTel = sc.nextLine();
		 
		 System.out.println("��й�ȣ�� �Է����ּ���");
		 System.out.print("�� ");
		 int userPwd = Integer.parseInt(sc.nextLine());
		 
		 UsersController.login(userTel, userPwd);

	}
//	
//	//�α׾ƿ�
//	public static void logout() {
//		
//	}
//	
//	//�ֹ��ϱ�-��ٱ��Ͽ� �ڵ����� �������
//    public static void �ֹ��ϱ�޼ҵ��̸�() {
//    	while(true) {
//			System.out.println("  1.Ŀ�� |  2.Ƽ  |  3.������  |  4.����Ʈ  |  5.��ٱ��ϴ��  |  6.�����ϱ�  ");
//			int menu =Integer.parseInt( sc.nextLine());
//			switch(menu) {
//				case 1 :
//					//Ŀ�Ǹ޴�����ִ�....
//					break;
//				case 2 :
//					//Ƽ�޴�����ִ�....
//					break;
//				case 3 :
//					//������޴�����ִ�....
//					break;
//				case 4 :
//					//����Ʈ�޴�����ִ�....
//					break;
//				case 5 :
//					//��ٱ��ϴ��
//					break;
//			    case 6 :
//					//�����ϱ�
//					break;
//				}
//		}
//    }
//    
//
//    //��ٱ��� ����
//	public static void viewCart() {
//	//���⿡�ٰ� ��ٱ��� ���� �޴��� ������..�ƴϸ� �ٱ����� ����??
//	
//	}
//	
}
