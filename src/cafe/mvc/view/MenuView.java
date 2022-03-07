package cafe.mvc.view;


import java.util.Scanner;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			//session :전화번호로 로그인하면 이름보여주면 좋을듯..?
			
			MenuView.printMenu();
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				// 가입
				break;
			case 2 :
				// 로그인
				break;

			case 9 : 
				System.exit(0);
			}
		}

	}
	
	
	public static void printMenu() {
		System.out.println("=== Cafe-in 카페에 오신걸 환영합니다 ==="); //멘트 이쁜걸로...?
		System.out.println("1. 회원가입   |   2. 회원주문   |  3. 비회원주문  |  4. 관리자설정  |  9. 종료");
	}
	
	//회원으로 로그인해야보이는 화면
	public static void printUserMenu(String userPhoneNo, String name) {
		while(true) {
			//SessionSet ss = SessionSet.getInstance();
			//System.out.println(ss.getSet());
			//System.out.println("어서오십시오. " +username+ " 님"); //전화번호쓰면 회원이름보이게 하고싶음..
			System.out.println("  1.로그아웃 |  2.마이페이지  |  3.메뉴보기  |  4.주문하기  |  5.주문취소  ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					//로그아웃
					return;
				case 2 :
					//마이페이지(적립금확인,지난주문내역)
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
				}
		}
		
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

	/**
	//마이페이지
	public static void 마이페이지 메소드 이름부여() {
		System.out.println("1. 적립금확인  |  2. 지난주문내역   | 9. 나가기");
	}
    
    //관리자메뉴
	public static void 관리자메뉴 메소드 이름부여() {
		System.out.println("관리자 메뉴");
		System.out.println("  1.상품등록  |  2.상품수정  |  3.상품삭제  |  4.주문상태변경  |  5.회원정보변경  |  9. 나가기  ");
		
	}
	
    //로그인
	public static void login() {
		 System.out.print("전화번호 : ");
		 System.out.print("비밀번호 : ");

	}
	
	//로그아웃
	public static void logout() {
		
	}
	
	//주문하기-장바구니에 자동으로 담아지게
    public static void 주문하기메소드이름() {
    	while(true) {
			System.out.println("  1.커피 |  2.티  |  3.스무디  |  4.디저트  |  5.장바구니담기  |  6.결제하기  ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					//커피메뉴띄어주는....
					break;
				case 2 :
					//티메뉴띄어주는....
					break;
				case 3 :
					//스무디메뉴띄어주는....
					break;
				case 4 :
					//디저트메뉴띄어주는....
					break;
				case 5 :
					//장바구니담기
					break;
			    case 6 :
					//결제하기
					break;
				}
		}
    }
    

    //장바구니 보기
	public static void viewCart() {
	//여기에다가 장바구니 삭제 메뉴를 만들까용..아니면 바깥으로 빼기??
	
	}
	*/
}
