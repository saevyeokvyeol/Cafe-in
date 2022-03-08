package cafe.mvc.model.service;

import java.sql.SQLException;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.Users;

public interface UsersService {
	/**
	 * ȸ������: user ���̺� insert
	 * @return 
	 * */
	void userInsert(Users users) throws SQLException, AddException, DuplicatedException;
	
	/**
	 * ȸ�� ���� ����: user ���̺� update(��ȭ��ȣ/�̸�/������...?)
	 * */
	void userUpdate(Users users) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * �α���
	 * */
	Users login(String userTel, int userPwd) throws SQLException, NotFoundException;
	
	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	Users selectPointByUserTel(String userTel) throws SQLException, NotFoundException;
	
	/**
	 * �� �ʿ��� �޼ҵ� �������?
	 * */
}
