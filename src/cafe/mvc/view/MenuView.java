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
			//session :전화번호로 로그인하면 이름보여주면 좋을듯..?
			
			MenuView.printMenu();
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView.register();
				break;
			case 2 :

				// 로그인
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
		System.out.println("=== Cafe-in 카페에 오신걸 환영합니다 ==="); //멘트 이쁜걸로...?
		System.out.println("1. 회원가입   |   2. 회원주문   |  3. 비회원주문  |  4. 관리자설정  |  9. 종료");
	}
	
	public static void register() {
		System.out.print("전화번호 ex)010-1111-1111 : ");
		String userTel = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		 
		System.out.print("비밀번호 : ");
		int userPwd = Integer.parseInt(sc.nextLine());	
		
		Users users = new Users(userTel, userName, userPwd);
		UsersController.registor(users);
	}
	
	//회원으로 로그인해야보이는 화면

	public static void printUserMenu(String userTel, String userName) {

		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			System.out.println("어서오십시오. " +userName+ " 님"); //전화번호쓰면 회원이름보이게 하고싶음..
			System.out.println("  1.로그아웃 |  2.마이페이지  |  3.메뉴보기  |  4.주문하기  |  5.주문취소  | 6.적립금 확인 ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					//로그아웃
					return;
				case 2 :
					//마이페이지(적립금확인,지난주문내역)
					MenuView.pwdUpdate(userTel);
					break;
				case 3 :
					//메뉴보기
					break;
				case 4 :
					//주문하기(1.커피 2.티 3.스무디 4.디저트 5.장바구니담기 6.결제하기 로 가게하면됨..또while문...?ㅠ)
					break;
				case 5 :
					//주문취소(결제된거 취소)
					break;
				case 6 :
					//적립금확인
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
		System.out.print("변결할비밀번호 : ");
		int userPwd = Integer.parseInt(sc.nextLine());
		
		Users users = new Users(userTel, null, userPwd);
		UsersController.pwdUpdate(users);
	}
	
	//비회원이 보는 화면
	public static void printNotUserMenu() {
		while(true) {
			System.out.println("어서오십시오."); //비회원한테 하는말..
			System.out.println("  1.메뉴보기 |  2.주문하기  |  9.종료  ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					//메뉴보기
					break;
				case 2 :
					//주문하기(1.커피 2.티 3.스무디 4.디저트 5.장바구니담기 6.결제하기 로 가게하면됨..또while문...?ㅠ)
					break;
				case 3 :
					//종료
					break;
				}
		}
		
	}

	
//	//마이페이지
//	public static void 마이페이지 메소드 이름부여() {
//		System.out.println("1. 적립금확인  |  2. 지난주문내역   | 9. 나가기");
//	}
//    
//    //관리자메뉴
	public static void adminMenu() {
		System.out.println("관리자 메뉴");
		System.out.println("  1.상품등록  |  2.상품수정  |  3.상품삭제  |  4.주문상태변경  |  5.회원정보변경  | 6. 일간 매출 조회 |  9. 나가기  ");
		System.out.print("메뉴 입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
		case 1 :
			// 가입
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
		
	
    //로그인
	public static void login() {
		 System.out.println("전화번호를 입력해주세요");
		 System.out.print("▶ ");
		 String userTel = sc.nextLine();
		 
		 System.out.println("비밀번호를 입력해주세요");
		 System.out.print("▶ ");
		 int userPwd = Integer.parseInt(sc.nextLine());
		 
		 UsersController.login(userTel, userPwd);

	}
//	
//	//로그아웃
//	public static void logout() {
//		
//	}
//	
//	//주문하기-장바구니에 자동으로 담아지게
//    public static void 주문하기메소드이름() {
//    	while(true) {
//			System.out.println("  1.커피 |  2.티  |  3.스무디  |  4.디저트  |  5.장바구니담기  |  6.결제하기  ");
//			int menu =Integer.parseInt( sc.nextLine());
//			switch(menu) {
//				case 1 :
//					//커피메뉴띄어주는....
//					break;
//				case 2 :
//					//티메뉴띄어주는....
//					break;
//				case 3 :
//					//스무디메뉴띄어주는....
//					break;
//				case 4 :
//					//디저트메뉴띄어주는....
//					break;
//				case 5 :
//					//장바구니담기
//					break;
//			    case 6 :
//					//결제하기
//					break;
//				}
//		}
//    }
//    
//
//    //장바구니 보기
//	public static void viewCart() {
//	//여기에다가 장바구니 삭제 메뉴를 만들까용..아니면 바깥으로 빼기??
//	
//	}
//	
}
