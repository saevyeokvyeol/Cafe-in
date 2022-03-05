package kosta.mvc.model.dao;

import java.sql.SQLException;

import dto.User;
// user
public interface CustmerDAO {
	/**
	 * ȸ������: user ���̺� insert
	 * */
	int userInsert(User user) throws SQLException;
	
	/**
	 * ȸ�� ���� ����: user ���̺� update(��ȭ��ȣ/�̸�/������...?)
	 * */
	int userUpdate(User user) throws SQLException;
	
	/**
	 * �α���
	 * */
	User login(String userTel, String userPwd) throws SQLException;
	
	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	User selectPointByUserTel(String userTel) throws SQLException;
	
	/**
	 * �� �ʿ��� �޼ҵ� �������?
	 * */
}