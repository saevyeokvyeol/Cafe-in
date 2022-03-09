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
import cafe.mvc.model.dto.UsersDTO;

import cafe.mvc.session.Session;
import cafe.mvc.session.SessionSet;
import cafe.mvc.util.DbUtil;

public class UsersServiceImpl implements UsersService{
	private UsersDAO usersDAO = new UsersDAOImpl();


	/**
	 * ȸ������: user ���̺� insert
	 * */
	@Override
	public void userInsert(UsersDTO usersDTO) throws SQLException, AddException, DuplicatedException {
		int result = usersDAO.userInsert(usersDTO);
		if(result==0)throw new SQLException("��ϵ����ʾҽ��ϴ�.^^");
	}

	/**
	 * ȸ�� ���� ����: user ���̺� update(��ȭ��ȣ/�̸�/������...?)
	 * */
	@Override
	public void userPwdUpdate(UsersDTO usersDTO) throws SQLException, ModifyException, NotFoundException {
		int result = usersDAO.userPwdUpdate(usersDTO);
		if(result==0)throw new SQLException("��ϵ����ʾҽ��ϴ�.^^");
		
	}
	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	@Override
	public UsersDTO userPointCh(String userTel) throws SQLException, ModifyException, NotFoundException {
		UsersDTO usersDTO = usersDAO.userPointCh(userTel);
		if(usersDTO == null) {
			throw new SQLException("����");
		}
		return usersDTO;
		
	}

	/**
	 * �α���
	 * */
	@Override
	public UsersDTO login(String userTel, int userPwd) throws SQLException, NotFoundException {
		UsersDTO usersDTO = usersDAO.login(userTel, userPwd);
		
		if(usersDTO == null) { // ���̵�� ��й�ȣ�� ��ġ�ϴ��� Ȯ��
			throw new NotFoundException("���̵� Ȥ�� ��й�ȣ�� �߸� �Ǿ����ϴ�.");
		}
		
		// �α��� �� ������ ���� ��ü�� ����
		Session session = new Session(userTel);
		
		// ���ǿ� ������ �α��� ������ ���Ǽ¿� ����
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		return usersDTO;
	}
	
	/**
	 * ��ü ���� �˻�
	 * */
	@Override
	public List<UsersDTO> userSelectAll() throws SQLException, NotFoundException {
		List<UsersDTO> list = usersDAO.userSelectAll();
		
		if(list == null) {
			throw new NotFoundException("ȸ�� ����Ʈ�� ������ �� �����ϴ�.");
		}
		return list;
	}
	
	/**
	 * ��ȭ��ȣ�� ���� �˻�
	 * */
	@Override
	public UsersDTO selectByUserTel(String userTel) throws SQLException, NotFoundException {
		UsersDTO usersDTO = usersDAO.selectByUserTel(userTel);
		
		if(usersDTO == null) {
			throw new NotFoundException("ȸ�� ������ ã�� �� �����ϴ�.");
		}
		return usersDTO;
	}
}
