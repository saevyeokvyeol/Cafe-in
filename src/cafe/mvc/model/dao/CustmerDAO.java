package cafe.mvc.model.dao;

import java.sql.SQLException;

import cafe.mvc.model.dto.Users;
// user
public interface CustmerDAO {
	/**
	 * ȸ������: user ���̺� insert
	 * */
	int userInsert(Users users) throws SQLException;
	
	/**
	 * ȸ�� ���� ����: user ���̺� update(��ȭ��ȣ/�̸�/������...?)
	 * */
	int userUpdate(Users users) throws SQLException;
	
	/**
	 * �α���
	 * */
	Users login(String userTel, String userPwd) throws SQLException;
	
	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	Users selectPointByUserTel(String userTel) throws SQLException;
	
	/**
	 * �� �ʿ��� �޼ҵ� �������?
	 * */
}