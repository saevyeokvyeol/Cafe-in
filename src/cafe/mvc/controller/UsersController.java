package cafe.mvc.controller;

import cafe.mvc.model.dto.Users;
import cafe.mvc.model.service.UsersService;
import cafe.mvc.model.service.UsersServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.SuccessView;


public class UsersController {
	static UsersService usersService = new UsersServiceImpl();

	/**
	 * 로그인
	 * */
	public static void login(String userTel, int userPwd) {
		try {
			Users users = usersService.login(userTel, userPwd);
			MenuView.printUserMenu(users.getUserTel(), users.getUserName());
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());



	
	/**
	 * 회원가입
	 */
	public static void registor(Users users) {
		try {
			usersService.userInsert(users);
			SuccessView.printMessage("가입완료");
			//MenuView.menu();
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			

		}
	}
}
