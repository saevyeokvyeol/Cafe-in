package cafe.mvc.model.service;

import java.sql.SQLException;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.Users;

public class UsersServiceImpl implements UsersService{

	/**
	 * ȸ������: user ���̺� insert
	 * */
	@Override
	public void userInsert(Users users) throws SQLException, AddException, DuplicatedException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ȸ�� ���� ����: user ���̺� update(��ȭ��ȣ/�̸�/������...?)
	 * */
	@Override
	public void userUpdate(Users users) throws SQLException, ModifyException, NotFoundException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * �α���
	 * */
	@Override
	public Users login(String userTel, String userPwd) throws SQLException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	@Override
	public Users selectPointByUserTel(String userTel) throws SQLException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
