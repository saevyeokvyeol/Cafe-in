package cafe.mvc.controller;

import cafe.mvc.model.dto.Users;
import cafe.mvc.model.service.UsersService;
import cafe.mvc.model.service.UsersServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.MenuView;
import cafe.mvc.view.MenuView2;
import cafe.mvc.view.SuccessView;


public class UsersController {
	static UsersService usersService = new UsersServiceImpl();

	/**
	 * 로그인
	 * */
	public static void login(String userTel, int userPwd) {
		try {
			Users users = usersService.login(userTel, userPwd);
			if(users.getUserTel().equals("999-9999-9999")) {
				MenuView2.adminMenu(users.getUserTel());
			} else {
				MenuView2.userMenu(users.getUserTel());
			}
			System.out.println("\n" + users.getUserName() + " 님, 방문해주셔서 감사합니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());

		}
	}
	
	/**
	 * 회원가입
	 */
	public static void registor(Users users) {
		try {
			usersService.userUpdate(users);
			SuccessView.printMessage("가입완료");
			//MenuView.menu();
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			

		}
	}
	
	/**
	 * 비밀번호변경
	 */
	public static void pwdUpdate(Users users) {
		try {
			usersService.userUpdate(users);
			SuccessView.printMessage("변경완료");
			//MenuView.menu();
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	/**
	 * 적립금확인
	 */
	public static void userPointCh(Users users) {
		try {
			usersService.userPointCh(users);
			SuccessView.printMessage("적립금확인완료");
			//MenuView.menu();
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * 쿠폰확인
	 */
	public static void userCouponCh(Users users) {
		try {
			usersService.userPointCh(users);
			SuccessView.printMessage("쿠폰확인완료");
			//MenuView.menu();
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * 전화번호로 유저 검색
	 * */
	public static void selectByUserTel(String userTel) {
		try {
			Users users = usersService.selectByUserTel(userTel);
			SuccessView.printUsersInfo(users);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
}
