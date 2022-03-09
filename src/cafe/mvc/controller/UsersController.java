package cafe.mvc.controller;

import cafe.mvc.model.dto.UsersDTO;
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
			UsersDTO users = usersService.login(userTel, userPwd);
			if(users.getUserTel().equals("999-9999-9999")) {
				MenuView2.adminMenu(users.getUserTel());
			} else {
//				MenuView.printUserMenu(userTel, userTel);
				MenuView2.userMenu(users.getUserTel());
			}
			System.out.println("\n" + users.getUserName() + " ��, �湮���ּż� �����մϴ�.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());

		}
	}
	
	/**
	 * ȸ������
	 */
	public static void userInsert(UsersDTO usersDTO) {
		try {
			usersService.userInsert(usersDTO);
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
	public static void userPwdUpdate(UsersDTO usersDTO) {
		try {
			usersService.userPwdUpdate(usersDTO);
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
			UsersDTO usersDTO = usersService.userPointCh(userTel);
			SuccessView.printMessage("������ : " + usersDTO.getUserPoint() + "��");
			//MenuView.menu();
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * ��ȭ��ȣ�� ���� �˻�
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
