package cafe.mvc.model.dao;

import java.sql.SQLException;

import cafe.mvc.model.dto.Users;

public class UserDAOImpl implements UsersDAO{

	/**
	 * ȸ������: user ���̺� insert
	 * */
	@Override
	public int userInsert(Users users) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * ȸ�� ���� ����: user ���̺� update(��ȭ��ȣ/�̸�/������...?)
	 * */

	@Override
	public int userUpdate(Users users) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * �α���
	 * */
	@Override
	public Users login(String userTel, String userPwd) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	@Override
	public Users selectPointByUserTel(String userTel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
