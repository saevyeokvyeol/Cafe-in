package cafe.mvc.model.service;

import java.sql.SQLException;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.UsersDTO;

public interface UsersService {
	/**
	 * ȸ������: user ���̺� insert
	 * @return 
	 * */
	void userInsert(UsersDTO usersDTO) throws SQLException, AddException, DuplicatedException;
	
	/**
	 * ȸ�� ���� ����: user ���̺� update(��ȭ��ȣ/�̸�/������...?)
	 * */
	void userPwdUpdate(UsersDTO usersDTO) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * �α���
	 * */
	UsersDTO login(String userTel, int userPwd) throws SQLException, NotFoundException;
	
	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	UsersDTO userPointCh(String userTel) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * ��ȭ��ȣ�� ���� �˻�
	 * */
	UsersDTO selectByUserTel(String userTel) throws SQLException, NotFoundException;
	
	/**
	 * �� �ʿ��� �޼ҵ� �������?
	 * */
}
