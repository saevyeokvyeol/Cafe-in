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
	 * �α���
	 * */
	public static void login(String userTel, int userPwd) {
		try {
			Users users = usersService.login(userTel, userPwd);
			if(users.getUserTel().equals("999-9999-9999")) {
				MenuView2.adminMenu(users.getUserTel());
			} else {
				MenuView.printUserMenu(userTel, userTel);
				//MenuView.userMenu(users.getUserTel());
			}
			System.out.println("\n" + users.getUserName() + " ��, �湮���ּż� �����մϴ�.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());

		}
	}
	
	/**
	 * ȸ������
	 */
	public static void registor(Users users) {
		try {
			usersService.userInsert(users);
			SuccessView.printMessage("���ԿϷ�");
			//MenuView.menu();
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			

		}
	}
	
	/**
	 * ��й�ȣ����
	 */
	public static void pwdUpdate(Users users) {
		try {
			usersService.userUpdate(users);
			SuccessView.printMessage("����Ϸ�");
			//MenuView.menu();
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	/**
	 * ������Ȯ��
	 */
	public static void userPointCh(String userTel) {
		try {
			Users users = usersService.userPointCh(userTel);
			SuccessView.printMessage("������ : " + users.getUserPoint() + "��");
			//MenuView.menu();
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * ����Ȯ��
	 */
//	public static void userCouponCh(Users users) {
//		try {
//			usersService.userPointCh(users);
//			SuccessView.printMessage("����Ȯ�οϷ�");
//			//MenuView.menu();
//		}catch (Exception e) {
//			e.printStackTrace();
//			FailView.errorMessage(e.getMessage());
//		}
//		
//	}
	
	/**
	 * ��ȭ��ȣ�� ���� �˻�
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
