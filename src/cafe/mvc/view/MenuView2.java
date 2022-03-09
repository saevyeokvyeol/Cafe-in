package cafe.mvc.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cafe.mvc.controller.CartController;
import cafe.mvc.controller.OrdersController;
import cafe.mvc.controller.UsersController;
import cafe.mvc.model.dto.OrderLineDTO;
import cafe.mvc.model.dto.OrdersDTO;
import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.session.Session;
import cafe.mvc.controller.ProductController;
import cafe.mvc.model.dto.StockDTO;
import cafe.mvc.model.dto.UsersDTO;
import cafe.mvc.session.SessionSet;

public class MenuView2 {
	private static Scanner sc = new Scanner(System.in);
	static String guestId = "guest";
	
	/**
	 * ���� �޴�
	 * */
	public static void mainMenu() {
		while(true) {
			System.out.println("\n" + "[ 1. ȸ������  |  2. �α���  |  3. ��ȸ���ֹ�  |  0. ���� ]");
			System.out.print("�� ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView2.userInsert();
				break;
			case 2 :
				// ������ ���̵�� �α��� �� ��� ������ �޴��� �̵���
				MenuView2.login();
				break;
			case 3 : 
				MenuView2.guestLogin();
				break;
			case 0 : 
				System.exit(0);
			}
		}
	}
	
	/**
	 * �α��� ���� �޴�
	 * */
	public static void userMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. �ֹ��ϱ�  |  2. ���� �ֹ� ����  |  3. ������ Ȯ��  |  4. ��й�ȣ ����  |  9. �α׾ƿ�  |  0. ���� ]");
			System.out.print("�� ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					MenuView2.orderMenu(userTel);
					break;
				case 2 :
					OrdersController.selectByUserTel(userTel);
					break;
				case 3 :
					UsersController.userPointCh(userTel);
					break;
				case 4 :
					MenuView2.userPwdUpdate(userTel);
					break;
				case 9 :
					MenuView2.logout(userTel);
					return;
				case 0 : 
					System.exit(0);
			}
		}
		
	}
	
	/**
	 * �ֹ� �޴�
	 * */
	public static void orderMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. Ŀ�� �޴� ����  |  2. ������ �޴� ����  |  3. �� �޴� ����  |  4. ����Ʈ �޴� ����  |  5. ��ٱ��� ����  |  9. �ڷ� ����  |  0. ���� ]");
			System.out.print("�� ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					String coffeeGroup = "C";
					ProductController.selectByGroup(coffeeGroup);
					System.out.println();
					break;
				case 2 :
					String teaGroup = "T";
					ProductController.selectByGroup(teaGroup);
					break;
				case 3 :
					String SmoothieGroup = "S";
					ProductController.selectByGroup(SmoothieGroup);
					break;
				case 4 :
					String dessertGroup = "D";
					ProductController.selectByGroup(dessertGroup);
					break;
				case 5 :
					MenuView2.cartMenu(userTel);
					break;
				case 9 :
					if(userTel.equals(guestId)) {
						MenuView2.logout(userTel);
					}
					return;
				case 0 :
					System.exit(0);
			}
		}
	}
	
	/**
	 * ī�װ��� ��ǰ ������
	 * */
	public static void categoryMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. ��ٱ��Ͽ� ��ǰ ���  |  9. �ڷ� ����  |  0. ���� ]");
			System.out.print("�� ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					MenuView2.putCart(userTel);
					break;
				case 9 :
					return;
				case 0 :
					System.exit(0);
			}
		}
	}
    
    /**
     * ������ �޴�
     * */
	public static void adminMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ ������ �޴� ]");
			System.out.println("[ 1. ��ǰ ����  |  2. ȸ�� ����  |  3. �ֹ� ����  |  9. �α׾ƿ�  |  0. ���� ]");
			System.out.print("�� ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView2.prodManageMenu(userTel);
				break;
			case 2 :
				MenuView2.usersManageMenu(userTel);
				break;
			case 3 :
				MenuView2.ordersManageMenu(userTel);
				break;
			case 9 : 
				MenuView2.logout(userTel);
				return;
			case 0 : 
				System.exit(0);
			}
		}
	}
	
	/**
	 * ��ǰ ���� �޴�
	 * */
	public static void prodManageMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. ��ǰ ��ȸ  |  2. ��ǰ ���  |  3. ��ǰ ����  | 4. ��ǰ ���� ����  |  9. �ڷ� ����  |  0. ���� ]");
			System.out.print("�� ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				ProductController.productSelectAll();
				break;
			case 2 :
				MenuView2.productInsert();
				break;
			case 3 :
				MenuView2.productUpdate();
				break;
			case 4 :
				MenuView2.productStateUpdate();
				break;
			case 9 : 
				return;
			case 0 : 
				System.exit(0);
			}
		}
	}
	
	/**
	 * ȸ�� ���� �޴�
	 * */
	public static void usersManageMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. ��ü ȸ�� ��ȸ  |  2. ȸ�� ���� �˻�  |  9. �ڷ� ����  |  0. ���� ]");
			System.out.print("�� ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				UsersController.userSelectAll();
				break;
			case 2 :
				MenuView2.userSelectByUserTel();
				break;
			case 9 : 
				return;
			case 0 : 
				System.exit(0);
			}
		}
	}
	
	/**
	 * �ֹ� ���� �޴�
	 * */
	public static void ordersManageMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. ���� �ֹ� ��ȸ  |  2. �ֹ� ���� ����  |  3. �ϰ� ���� ���  |  4. ��ǰ�� �Ǹ� ���  |  9. �ڷ� ����  |  0. ���� ]");
			System.out.print("�� ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				OrdersController.selectOngoingOrder();
				break;
			case 2 :
				MenuView2.orderStateUpdate();
				break;
			case 3 :
				OrdersController.dailySalesStatistic(new SimpleDateFormat("yyMMdd").format(new Date()));
				break;
			case 4 :
				OrdersController.productSalesStatistic();
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
	public static void cartMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. ����  |  2. ��ٱ��� ��ǰ ����  |  3. ��ٱ��� ��ü ����  |  9. �ڷ� ����  |  0. ���� ]");
			System.out.print("�� ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView2.orderInsert(userTel);
				return;
			case 2 :
				MenuView2.deleteCartByCode(userTel);
				break;
			case 3 :
				CartController.deleteCartAll(userTel);
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
		String stringPwd = sc.nextLine();
		int userPwd = 0;
		
		if(stringPwd.matches("[+-]?\\d*(\\.\\d+)?")) {
			userPwd = Integer.parseInt(stringPwd);
		} else {
			System.out.println("��й�ȣ�� ���ڸ� �Է����ּ���");
			return;
		}
		
		UsersController.login(userTel, userPwd);
	}
	
	/**
	 * ��ȸ�� �ӽ� �α��� �޼ҵ�
	 * */
	public static void guestLogin() {
		Session session = new Session(guestId);
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		MenuView2.userMenu(guestId);
	}

	/**
	 * �α׾ƿ� �޼ҵ�
	 * */
	public static void logout(String userTel) {
		Session session = new Session(userTel);
		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);
	}
	
	/**
	 * ȸ������ �޼ҵ�
	 */
	public static void userInsert() {
		System.out.print("��ȭ��ȣ ex)010-1111-1111 : ");
		String userTel = sc.nextLine();
		
		System.out.print("�̸� : ");
		String userName = sc.nextLine();
		 
		System.out.print("��й�ȣ : ");
		int userPwd = Integer.parseInt(sc.nextLine());	
		
		UsersDTO usersDTO = new UsersDTO(userTel, userName, userPwd);
		UsersController.userInsert(usersDTO);
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
		
		if(!userTel.equals(guestId)) {
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
		
		List<OrderLineDTO> list = new ArrayList<OrderLineDTO>();
		Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>) session.getAttributes("cart");
		
		for(ProductDTO productDTO : cart.keySet()) {
			list.add(new OrderLineDTO(0, 0, productDTO.getProdCode(), cart.get(productDTO), 0));

		}
		
		OrdersDTO ordersDTO = new OrdersDTO(0, userTel, 0, payMethod, payPoint, 0, null, takeout);
		ordersDTO.setOrderLineList(list);
		
		OrdersController.orderInsert(ordersDTO);

	}
	
	/**
	 * ��ǰ ��� �޼ҵ�
	 * */
	public static void productInsert() {
		 System.out.print("��ǰ�ڵ� �� ");
		 String prodCode = sc.nextLine();
		 //�߸°� ���Դ��� üũ '���ĺ� ���� ����'
		 if (prodCode.matches("^[C][0-9][0-9]*$")|| prodCode.matches("^[T][0-9][0-9]*$") || prodCode.matches("^[S][0-9][0-9]*$") | prodCode.matches("^[D][0-9][0-9]*$")){
			 System.out.print("��ǰ�̸� �� ");
			 String prodName = sc.nextLine();
			 
			 System.out.print("��ǰ���� �� ");
			 int prodPrice = Integer.parseInt(sc.nextLine());
			 
			 System.out.print("��ǰ�Ұ� �� ");
			 String prodDetail = sc.nextLine();
			 
			 System.out.print("��ǰ���� �� ");
			 int prodState = Integer.parseInt(sc.nextLine());

			 ProductDTO product = new ProductDTO(prodCode, null, prodName, prodPrice, prodDetail, prodState);
			 
			 if(prodCode.substring(0, 1).equals("D")) { 
				 System.out.print("����Ʈ ��� �� ");
				 int prodStock = Integer.parseInt(sc.nextLine());
				 StockDTO stock = new StockDTO(prodCode, prodStock);
				 product.setStock(stock);

			 }

			 ProductController.productInsert(product);
		 }else {
			 System.out.println("��ǰ�ڵ�� ���ĺ�1��(C,T,S,D ��) ����2���� �Է����ּ���.");
			 return;
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
	
		 //����Ʈ��� ����
		 char group = prodCode.charAt(0);
		 if(group == 'D') { 
			 System.out.print("����Ʈ ��� �� ");
			 int prodStock = Integer.parseInt(sc.nextLine());
			 ProductDTO productDTO = new ProductDTO(prodCode, null, null, prodPrice, prodDetail, 0);
			 StockDTO stockDTO = new StockDTO(prodCode, prodStock);
			 productDTO.setStock(stockDTO);
			 ProductController.dessertStockUpdate(stockDTO);
		 } else {
			 ProductController.productUpdate(new ProductDTO(prodCode, null, null, prodPrice, prodDetail, 0));
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
	public static void userPwdUpdate(String userTel) {
		System.out.print("������ ��й�ȣ : ");
		int userPwd = Integer.parseInt(sc.nextLine());
		
		UsersDTO usersDTO = new UsersDTO(userTel, null, userPwd);
		UsersController.userPwdUpdate(usersDTO);
	}
	
	/**
	 * �ֹ� ���� ���� �޼ҵ�
	 * */
	public static void orderStateUpdate() {
		
	}
	
	/**
	 * ȸ�� ���� �˻�
	 * */
	public static void userSelectByUserTel() {
		System.out.println("�˻��� ȸ���� ��ȭ��ȣ�� �Է����ּ���.");
		System.out.print("�� ");
		String userTel = sc.nextLine();
		
		UsersController.selectByUserTel(userTel);
	}
}
