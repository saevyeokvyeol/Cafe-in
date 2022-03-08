package cafe.mvc.view;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


import cafe.mvc.controller.OrdersController;
import cafe.mvc.controller.UsersController;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.service.ProductService;
import cafe.mvc.model.service.ProductServiceImpl;
import cafe.mvc.session.SessionSet;
import cafe.mvc.controller.ProductController;


public class MenuView {
	
	//private static ProductServiceImpl productService;
	
	static ProductServiceImpl productService = new ProductServiceImpl();
	
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
				MenuView.login();
				break;
			case 3 :
				MenuView.printNotUserMenu();
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
	
	//ȸ������ �α����ؾߺ��̴� ȭ��

	public static void printUserMenu(String userTel, String userName) {

		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			System.out.println("����ʽÿ�. " +userName+ " ��"); //��ȭ��ȣ���� ȸ���̸����̰� �ϰ����..
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
					//�޴�����(ī�װ� ����)
					MenuView.category();
					break;
				case 4 :
					//OrderStart();
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
			System.out.println("  1.�޴����� |  2.�ֹ��ϱ�  | 3. ��� ��ǰ��ȸ |  9.����  ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					//�޴�����
					MenuView.category();
					break;
				case 2 :
					//OrderStart();
					break;
				case 3 : 
					//��ü��ǰ ��ȸ
					MenuView.productAll();
					break;
				case 9 :
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
//	//ī�װ��� �޴�����
    public static void category() {
    	while(true) {
			System.out.println("  1.Ŀ�� |  2.Ƽ  |  3.������  |  4.����Ʈ | 5. �ڷ� ���� ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					//Ŀ�Ǹ޴�����ִ�....
					String coffeeGroup = "C";
					//List<Product> productListC =  productService.selectByGroup(coffeeGroup);
					//MenuView.getMenu(productListC);
					break;
				case 2 :
					//Ƽ�޴�����ִ�....
					String teaGroup = "T";
					//List<Product> productListT =  productService.selectByGroup(teaGroup);
					//MenuView.getMenu(productListT);
					break;
				case 3 :
					//������޴�����ִ�....
					String SmoothieGroup = "S";
					//List<Product> productListS =  productService.selectByGroup(SmoothieGroup);
					//MenuView.getMenu(productListS);
					break;
				case 4 :
					//����Ʈ�޴�����ִ�....
					String dessertGroup = "D";
					//List<Product> productListD =  productService.selectByGroup(dessertGroup);
					//MenuView.getMenu(productListD);
					break;
				case 5 :
					//�޴� �� ������ �ڷ� ����!
					return;
				}
		}
    }
//ī�װ� �˻� for
    public static void getMenu(List<Product> productList) {
	for(Product p : productList) {
		System.out.println(
				p.getProdName()  + "|" +
				p.getProdPrice() + "|" +
				p.getProdDetail() + "|" +
				p.getSoldOut());
	}
    }
//��ü ��ǰ��ȸ
    public static void productAll()  {
    	if(true) {
    		//List<Product> productList =  productService.selectAll(null);
			//MenuView.getMenu(productList);
    	}
    }
 
//
//    //��ٱ��� ����
//	public static void viewCart() {
//	//���⿡�ٰ� ��ٱ��� ���� �޴��� ������..�ƴϸ� �ٱ����� ����??
//	
//	}
//	
}



