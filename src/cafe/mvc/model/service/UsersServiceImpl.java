package cafe.mvc.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dao.UsersDAO;
import cafe.mvc.model.dao.UsersDAOImpl;
import cafe.mvc.model.dto.Users;

import cafe.mvc.session.Session;
import cafe.mvc.session.SessionSet;
import cafe.mvc.util.DbUtil;

public class UsersServiceImpl implements UsersService{
	private UsersDAO usersDAO = new UsersDAOImpl();


	/**
	 * ȸ������: user ���̺� insert
	 * */
	@Override
	public void userInsert(Users users) throws SQLException, AddException, DuplicatedException {
		int result = usersDAO.userInsert(users);
		if(result==0)throw new SQLException("��ϵ����ʾҽ��ϴ�.^^");
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
	public Users login(String userTel, int userPwd) throws SQLException, NotFoundException {
		Users users = usersDAO.login(userTel, userPwd);
		
		if(users == null) { // ���̵�� ��й�ȣ�� ��ġ�ϴ��� Ȯ��
			throw new NotFoundException("���̵� Ȥ�� ��й�ȣ�� �߸� �Ǿ����ϴ�.");
		}
		
		// �α��� �� ������ ���� ��ü�� ����
		Session session = new Session(userTel);
		
		// ���ǿ� ������ �α��� ������ ���Ǽ¿� ����
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		return users;
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