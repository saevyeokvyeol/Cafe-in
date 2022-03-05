package cafe.mvc.model.service;

import java.sql.SQLException;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import dto.User;

public interface CustomerService {
	/**
	 * ȸ������: user ���̺� insert
	 * */
	void userInsert(User user) throws SQLException, AddException, DuplicatedException;
	
	/**
	 * ȸ�� ���� ����: user ���̺� update(��ȭ��ȣ/�̸�/������...?)
	 * */
	void userUpdate(User user) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * �α���
	 * */
	User login(String userTel, String userPwd) throws SQLException, NotFoundException;
	
	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	User selectPointByUserTel(String userTel) throws SQLException, NotFoundException;
	
	/**
	 * �� �ʿ��� �޼ҵ� �������?
	 * */
}
