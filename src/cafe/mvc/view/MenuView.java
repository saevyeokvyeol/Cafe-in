package cafe.mvc.view;


import java.util.Scanner;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			//session :��ȭ��ȣ�� �α����ϸ� �̸������ָ� ������..?
			
			MenuView.printMenu();
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				// ����
				break;
			case 2 :
				// �α���
				break;

			case 9 : 
				System.exit(0);
			}
		}

	}
	
	
	public static void printMenu() {
		System.out.println("=== Cafe-in ī�信 ���Ű� ȯ���մϴ� ==="); //��Ʈ �̻۰ɷ�...?
		System.out.println("1. ȸ������   |   2. ȸ���ֹ�   |  3. ��ȸ���ֹ�  |  4. �����ڼ���  |  9. ����");
	}
	
	//ȸ������ �α����ؾߺ��̴� ȭ��
	public static void printUserMenu(String userPhoneNo, String name) {
		while(true) {
			//SessionSet ss = SessionSet.getInstance();
			//System.out.println(ss.getSet());
			//System.out.println("����ʽÿ�. " +username+ " ��"); //��ȭ��ȣ���� ȸ���̸����̰� �ϰ����..
			System.out.println("  1.�α׾ƿ� |  2.����������  |  3.�޴�����  |  4.�ֹ��ϱ�  |  5.�ֹ����  ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					//�α׾ƿ�
					return;
				case 2 :
					//����������(������Ȯ��,�����ֹ�����)
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
				}
		}
		
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

	/**
	//����������
	public static void ���������� �޼ҵ� �̸��ο�() {
		System.out.println("1. ������Ȯ��  |  2. �����ֹ�����   | 9. ������");
	}
    
    //�����ڸ޴�
	public static void �����ڸ޴� �޼ҵ� �̸��ο�() {
		System.out.println("������ �޴�");
		System.out.println("  1.��ǰ���  |  2.��ǰ����  |  3.��ǰ����  |  4.�ֹ����º���  |  5.ȸ����������  |  9. ������  ");
		
	}
	
    //�α���
	public static void login() {
		 System.out.print("��ȭ��ȣ : ");
		 System.out.print("��й�ȣ : ");

	}
	
	//�α׾ƿ�
	public static void logout() {
		
	}
	
	//�ֹ��ϱ�-��ٱ��Ͽ� �ڵ����� �������
    public static void �ֹ��ϱ�޼ҵ��̸�() {
    	while(true) {
			System.out.println("  1.Ŀ�� |  2.Ƽ  |  3.������  |  4.����Ʈ  |  5.��ٱ��ϴ��  |  6.�����ϱ�  ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					//Ŀ�Ǹ޴�����ִ�....
					break;
				case 2 :
					//Ƽ�޴�����ִ�....
					break;
				case 3 :
					//������޴�����ִ�....
					break;
				case 4 :
					//����Ʈ�޴�����ִ�....
					break;
				case 5 :
					//��ٱ��ϴ��
					break;
			    case 6 :
					//�����ϱ�
					break;
				}
		}
    }
    

    //��ٱ��� ����
	public static void viewCart() {
	//���⿡�ٰ� ��ٱ��� ���� �޴��� ������..�ƴϸ� �ٱ����� ����??
	
	}
	*/
}
