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
	 * 메인 메뉴
	 * */
	public static void mainMenu() {
		while(true) {
			System.out.println("\n" + "[ 1. 회원가입  |  2. 로그인  |  3. 비회원주문  |  0. 종료 ]");
			System.out.print("▶ ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView2.userInsert();
				break;
			case 2 :
				// 관리자 아이디로 로그인 할 경우 관리자 메뉴로 이동함
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
	 * 로그인 유저 메뉴
	 * */
	public static void userMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. 주문하기  |  2. 지난 주문 내역  |  3. 적립금 확인  |  4. 비밀번호 변경  |  9. 로그아웃  |  0. 종료 ]");
			System.out.print("▶ ");
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
	 * 주문 메뉴
	 * */
	public static void orderMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. 커피 메뉴 보기  |  2. 스무디 메뉴 보기  |  3. 차 메뉴 보기  |  4. 디저트 메뉴 보기  |  5. 장바구니 보기  |  9. 뒤로 가기  |  0. 종료 ]");
			System.out.print("▶ ");
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
	 * 카테고리별 상품 페이지
	 * */
	public static void categoryMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. 장바구니에 상품 담기  |  9. 뒤로 가기  |  0. 종료 ]");
			System.out.print("▶ ");
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
     * 관리자 메뉴
     * */
	public static void adminMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 관리자 메뉴 ]");
			System.out.println("[ 1. 상품 관리  |  2. 회원 관리  |  3. 주문 관리  |  9. 로그아웃  |  0. 종료 ]");
			System.out.print("▶ ");
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
	 * 상품 관리 메뉴
	 * */
	public static void prodManageMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. 상품 조회  |  2. 상품 등록  |  3. 상품 수정  | 4. 상품 상태 변경  |  9. 뒤로 가기  |  0. 종료 ]");
			System.out.print("▶ ");
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
	 * 회원 관리 메뉴
	 * */
	public static void usersManageMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. 전체 회원 조회  |  2. 회원 정보 검색  |  9. 뒤로 가기  |  0. 종료 ]");
			System.out.print("▶ ");
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
	 * 주문 관리 메뉴
	 * */
	public static void ordersManageMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. 현재 주문 조회  |  2. 주문 상태 변경  |  3. 일간 매출 통계  |  4. 제품별 판매 통계  |  9. 뒤로 가기  |  0. 종료 ]");
			System.out.print("▶ ");
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
     * 장바구니 메뉴
     * */
	public static void cartMenu(String userTel) {
		while(true) {
			System.out.println("\n" + "[ 1. 결제  |  2. 장바구니 상품 삭제  |  3. 장바구니 전체 삭제  |  9. 뒤로 가기  |  0. 종료 ]");
			System.out.print("▶ ");
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
	 * 로그인 메소드
	 * */
	public static void login() {
		System.out.println("전화번호를 입력해주세요");
		System.out.print("▶ ");
		String userTel = sc.nextLine();
		
		System.out.println("비밀번호를 입력해주세요");
		System.out.print("▶ ");
		String stringPwd = sc.nextLine();
		int userPwd = 0;
		
		if(stringPwd.matches("[+-]?\\d*(\\.\\d+)?")) {
			userPwd = Integer.parseInt(stringPwd);
		} else {
			System.out.println("비밀번호는 숫자만 입력해주세요");
			return;
		}
		
		UsersController.login(userTel, userPwd);
	}
	
	/**
	 * 비회원 임시 로그인 메소드
	 * */
	public static void guestLogin() {
		Session session = new Session(guestId);
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		MenuView2.userMenu(guestId);
	}

	/**
	 * 로그아웃 메소드
	 * */
	public static void logout(String userTel) {
		Session session = new Session(userTel);
		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);
	}
	
	/**
	 * 회원가입 메소드
	 */
	public static void userInsert() {
		System.out.print("전화번호 ex)010-1111-1111 : ");
		String userTel = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		 
		System.out.print("비밀번호 : ");
		int userPwd = Integer.parseInt(sc.nextLine());	
		
		UsersDTO usersDTO = new UsersDTO(userTel, userName, userPwd);
		UsersController.userInsert(usersDTO);
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
		
		if(!userTel.equals(guestId)) {
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
	 * 상품 등록 메소드
	 * */
	public static void productInsert() {
		 System.out.print("상품코드 ▶ ");
		 String prodCode = sc.nextLine();
		 //잘맞게 들어왔는지 체크 '알파벳 숫자 숫자'
		 if (prodCode.matches("^[C][0-9][0-9]*$")|| prodCode.matches("^[T][0-9][0-9]*$") || prodCode.matches("^[S][0-9][0-9]*$") | prodCode.matches("^[D][0-9][0-9]*$")){
			 System.out.print("상품이름 ▶ ");
			 String prodName = sc.nextLine();
			 
			 System.out.print("상품가격 ▶ ");
			 int prodPrice = Integer.parseInt(sc.nextLine());
			 
			 System.out.print("상품소개 ▶ ");
			 String prodDetail = sc.nextLine();
			 
			 System.out.print("상품상태 ▶ ");
			 int prodState = Integer.parseInt(sc.nextLine());

			 ProductDTO product = new ProductDTO(prodCode, null, prodName, prodPrice, prodDetail, prodState);
			 
			 if(prodCode.substring(0, 1).equals("D")) { 
				 System.out.print("디저트 재고량 ▶ ");
				 int prodStock = Integer.parseInt(sc.nextLine());
				 StockDTO stock = new StockDTO(prodCode, prodStock);
				 product.setStock(stock);

			 }

			 ProductController.productInsert(product);
		 }else {
			 System.out.println("상품코드는 알파벳1개(C,T,S,D 중) 숫자2개로 입력해주세요.");
			 return;
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
	
		 //디저트재고량 수정
		 char group = prodCode.charAt(0);
		 if(group == 'D') { 
			 System.out.print("디저트 재고량 ▶ ");
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
	public static void userPwdUpdate(String userTel) {
		System.out.print("변경할 비밀번호 : ");
		int userPwd = Integer.parseInt(sc.nextLine());
		
		UsersDTO usersDTO = new UsersDTO(userTel, null, userPwd);
		UsersController.userPwdUpdate(usersDTO);
	}
	
	/**
	 * 주문 상태 변경 메소드
	 * */
	public static void orderStateUpdate() {
		
	}
	
	/**
	 * 회원 정보 검색
	 * */
	public static void userSelectByUserTel() {
		System.out.println("검색할 회원의 전화번호를 입력해주세요.");
		System.out.print("▶ ");
		String userTel = sc.nextLine();
		
		UsersController.selectByUserTel(userTel);
	}
}
