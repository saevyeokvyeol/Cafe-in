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
import cafe.mvc.controller.ProductController;
import cafe.mvc.model.dto.Stock;
import cafe.mvc.session.SessionSet;

public class MenuView2 {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 메인 메뉴
	 * */
	public static void mainMenu() {
		while(true) {
			System.out.println("[ 1. 회원가입  |  2. 로그인  |  3. 비회원주문  |  0. 종료 ]");
			System.out.print("▶ ");
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
	 * 로그인 유저 메뉴
	 * */
	public static void userMenu(String userTel, String userName) {
		while(true) {
			System.out.println("\n" + userName + " 님 어서오세요.");
			System.out.println("[ 1. 주문하기  |  2. 지난 주문 내역  |  3. 적립금 확인  |  9. 로그아웃  |  0. 종료 ]");
			System.out.print("▶ ");
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
	 * 주문 메뉴
	 * */
	public static void orderMenu(String userTel, String userName) {
		while(true) {
			System.out.println("\n" + "[ 1. 커피 메뉴 보기  |  2. 스무디 메뉴 보기  |  3. 차 메뉴 보기  |  4. 디저트 메뉴 보기  |  5. 장바구니 보기  |  9. 뒤로 가기  |  0. 종료 ]");
			System.out.print("▶ ");
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
					//종료
			}
		}
	}
	
	/**
	 * 카테고리별 상품 페이지
	 * */
	public static void categoryMenu(String userTel, String userName) {
		while(true) {
			System.out.println("\n" + "[ 1. 장바구니에 상품 담기  |  9. 뒤로 가기  |  0. 종료 ]");
			System.out.print("▶ ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					break;
				case 9 :
					return;
				case 0 :
					//종료
			}
		}
	}
    
    /**
     * 관리자 메뉴
     * */
	public static void adminMenu(String userTel, String userName) {
		while(true) {
			System.out.println("\n" + "관리자 메뉴");
			System.out.println("[ 1. 상품 조회  |  2. 상품 등록  |  3. 상품 수정  | 4. 상품 상태 변경  |  5. 회원 정보 수정  |  6. 현재 주문 조회  |  7. 주문 상태 변경  |  8. 일간 매출 통계  |  9. 로그아웃  |  0. 종료 ]");
			System.out.print("▶ ");
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
     * 장바구니 메뉴
     * */
	public static void cartMenu(String userTel, String userName) {
		while(true) {
			System.out.println("\n" + "[ 1. 결제  |  2. 장바구니 상품 삭제  |  3. 장바구니 전체 삭제  |  9. 뒤로 가기  |  0. 종료 ]");
			System.out.print("▶ ");
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
	 * 로그인 메소드
	 * */
	public static void login() {
		System.out.println("전화번호를 입력해주세요");
		System.out.print("▶ ");
		String userTel = sc.nextLine();
		
		System.out.println("비밀번호를 입력해주세요");
		System.out.print("▶ ");
		int userPwd = Integer.parseInt(sc.nextLine());
		
		UsersController.login(userTel, userPwd);
	}
	
	/**
	 * 관리자 로그인 메소드
	 * */
	public static void adminLogin() {
		
	}

	/**
	 * 로그아웃 메소드
	 * */
	public static void logout() {
		
	}
	
	/**
	 * 회원가입 메소드
	 * */
	public static void 회원가입() {
		
	}
	
	/**
	 * 장바구니 추가 메소드
	 * */
	public static void putCart(String userTel) {
		System.out.println("장바구니에 추가할 상품 코드를 입력해주세요");
		System.out.print("▶ ");
		String prodCode = sc.nextLine();
		
		System.out.println("원하시는 상품 수량을 입력해주세요.");
		System.out.print("▶ ");
		int qty = Integer.parseInt(sc.nextLine());
		
		CartController.putCart(userTel, prodCode, qty);
	}
	
	/**
	 * 장바구니 부분 삭제 메소드
	 * */
	public static void deleteCartByCode(String userTel) {
		System.out.println("장바구니에서 제외할 상품 코드를 입력해주세요");
		System.out.print("▶ ");
		String prodCode = sc.nextLine();
		
		CartController.deleteCartByCode(userTel, prodCode);
	}
	
	/**
	 * 결제 메소드
	 * */
	public static void orderInsert(String userTel) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(userTel);
		
		int payPoint = 0;
		
		if(userTel != null) {
			System.out.println("사용하실 적립금 액수를 입력해주세요.");
			System.out.print("▶ ");
			payPoint = Integer.parseInt(sc.nextLine());
		}
		
		System.out.println("결제 방법을 선택해주세요.");
		System.out.println("[ 카드 / 현금 ]");
		System.out.print("▶ ");
		String payMethod = sc.nextLine();
		
		System.out.println("포장 여부를 선택해주세요.");
		System.out.println("[ 0. 매장에서 먹고 갈게요  |  1. 포장해 주세요 ]");
		System.out.print("▶ ");
		int takeout = Integer.parseInt(sc.nextLine());
		
		List<OrderLine> list = new ArrayList<OrderLine>();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttributes("cart");
		
		Orders orders = new Orders(0, userTel, 0, payMethod, payPoint, 0, null, takeout);
		orders.setOrdelLineList(list);
		
		OrdersController.orderInsert(orders);
		
	}
	
	/**
	 * 상품 등록 메소드
	 * */
	public static void productInsert() {
		 System.out.print("상품코드 ▶ ");
		 String prodCode = sc.nextLine();
		 
		 System.out.print("상품이름 ▶ ");
		 String prodName = sc.nextLine();
		 
		 System.out.print("상품가격 ▶ ");
		 int prodPrice = Integer.parseInt(sc.nextLine());
		 
		 System.out.print("상품소개 ▶ ");
		 String prodDetail = sc.nextLine();
		 
		 System.out.print("상품상태 ▶ ");
		 int prodState = Integer.parseInt(sc.nextLine());
		 
		 char group = prodCode.charAt(0);
		 if(group == 'D') { 
			 System.out.print("디저트 재고량 ▶ ");
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
	 * 상품 수정 메소드
	 * */
	public static void productUpdate() {
		 System.out.print("상품코드 ▶ ");
		 String prodCode = sc.nextLine();
		 
		 System.out.print("상품가격 ▶ ");
		 int prodPrice = Integer.parseInt(sc.nextLine());
		 
		 System.out.print("상품소개 ▶ ");
		 String prodDetail = sc.nextLine();
	
		 
		 
		 //디저트재고량
		 char group = prodCode.charAt(0);
		 if(group == 'D') { 
			 System.out.print("디저트 재고량 ▶ ");
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
	 * 상품 상태 변경 메소드
	 * */
	public static void productStateUpdate() {
		 System.out.print("상품코드 ▶ ");
		 String prodCode = sc.nextLine();
		 
		 System.out.print("상품상태 ▶ ");
		 int prodState = Integer.parseInt(sc.nextLine());
		 ProductController.productStateUpdate(prodCode, prodState);
	}
	
	/**
	 * 회원 비밀번호 수정 메소드
	 * */
	public static void userUpdate() {
		
	}
	
	/**
	 * 주문 상태 변경 메소드
	 * */
	public static void orderStateUpdate() {
		
	}
}
