package cafe.mvc.controller;

import java.util.List;

import cafe.mvc.model.dto.UsersDTO;
import cafe.mvc.model.service.UsersService;
import cafe.mvc.model.service.UsersServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.MenuView;
import cafe.mvc.view.MenuView;
import cafe.mvc.view.SuccessView;


public class UsersController {
	static UsersService usersService = new UsersServiceImpl();

	/**
	 * 로그인
	 * */
	public static void login(String userTel, int userPwd) {
		try {
			UsersDTO users = usersService.login(userTel, userPwd);
			if(users.getUserTel().equals("999-9999-9999")) {
				MenuView.adminMenu(users.getUserTel());
			} else {
				System.out.println("\n" + users.getUserName() + " 님, 방문해주셔서 감사합니다.");
				MenuView.userMenu(users.getUserTel());
			}
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());

		}
	}
	
	/**
	 * 회원가입
	 */
	public static void userInsert(UsersDTO usersDTO) {
		try {
			usersService.userInsert(usersDTO);
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
	public static void userPwdUpdate(UsersDTO usersDTO) {
		try {
			usersService.userPwdUpdate(usersDTO);
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
	public static void userPointCh(String userTel) {
		try {
			UsersDTO usersDTO = usersService.userPointCh(userTel);
			SuccessView.printMessage("적립금 : " + usersDTO.getUserPoint() + "원");
			//MenuView.menu();
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * 전체 회원 조회(관리자 제외)
	 * */
	public static void userSelectAll() {
		try {
			List<UsersDTO> list = usersService.userSelectAll();
			SuccessView.printUserSelectAll(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 전화번호로 유저 검색
	 * */
	public static void selectByUserTel(String userTel) {
		try {
			UsersDTO usersDTO = usersService.selectByUserTel(userTel);
			SuccessView.printUsersInfo(usersDTO);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
}
