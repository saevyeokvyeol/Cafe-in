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
			UsersDTO usersDTO = usersService.login(userTel, userPwd);
			if(usersDTO.getUserTel().equals("999-9999-9999")) {
				MenuView2.adminMenu(usersDTO.getUserTel());
			} else {
				MenuView.printUserMenu(userTel, userTel);
				//MenuView.userMenu(users.getUserTel());
			}
			System.out.println("\n" + usersDTO.getUserName() + " ��, �湮���ּż� �����մϴ�.");
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
	public static void pwdUpdate(UsersDTO usersDTO) {
		try {
			usersService.userUpdate(usersDTO);
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
			UsersDTO usersDTO = usersService.selectByUserTel(userTel);
			SuccessView.printUsersInfo(usersDTO);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
}
