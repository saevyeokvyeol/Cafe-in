package cafe.mvc.model.dao;

import java.sql.SQLException;

import cafe.mvc.model.dto.UsersDTO;
// user
public interface UsersDAO {
	/**
	 * ȸ������: user ���̺� insert
	 * */
	int userInsert(UsersDTO usersDTO) throws SQLException;
	
	/**
	 * ȸ�� ���� ����: user ���̺� update(��ȭ��ȣ/�̸�/������...?)
	 * */
	int userUpdate(UsersDTO usersDTO) throws SQLException;
	
	/**
	 * �α���
	 * */
	UsersDTO login(String userTel, int userPwd) throws SQLException;
	
	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	UsersDTO userPointCh(String userTel) throws SQLException;
	
	/**
	 * ��ȭ��ȣ�� ���� �˻�
	 * */
	UsersDTO selectByUserTel(String userTel) throws SQLException;
	
	/**
	 * �� �ʿ��� �޼ҵ� �������?
	 * */
}