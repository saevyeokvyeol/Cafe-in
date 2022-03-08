package cafe.mvc.model.dao;

import java.sql.SQLException;

import cafe.mvc.model.dto.Users;
// user
public interface UsersDAO {
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
	Users login(String userTel, int userPwd) throws SQLException;
	
	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	int userPointCh(Users users) throws SQLException;
	
	/**
	 * �� �ʿ��� �޼ҵ� �������?
	 * */
}