package cafe.mvc.view;

import java.util.Scanner;

import cafe.mvc.controller.UsersController;
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
				MenuView2.orderMenu();
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
	 * 주문 메뉴
	 * */
	public static void orderMenu() {
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
					MenuView2.cartMenu();
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
	public static void categoryMenu() {
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
	public static void adminMenu() {
		while(true) {
			System.out.println("\n" + "관리자 메뉴");
			System.out.println("[ 1. 상품 조회  |  2. 상품 등록  |  3. 상품 수정  |  4. 회원 정보 수정  |  5. 현재 주문 조회  |  6. 주문 상태 변경  |  7. 일간 매출 통계  |  9. 로그아웃  |  0. 종료 ]");
			System.out.print("▶ ");
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
     * 장바구니 메뉴
     * */
	public static void cartMenu() {
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
	public static void putCart() {
		
	}
	
	/**
	 * 장바구니 부분 삭제 메소드
	 * */
	public static void deleteCartByCode() {
		
	}
	
	/**
	 * 결제 메소드
	 * */
	public static void orderInsert() {
		
	}
	
	/**
	 * 상품 등록 메소드
	 * */
	public static void productInsert() {
		
	}
	
	/**
	 * 상품 수정 메소드
	 * */
	public static void productUpdate() {
		
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
