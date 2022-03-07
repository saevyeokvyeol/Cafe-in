package cafe.mvc.model.service;

import java.sql.SQLException;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dao.UsersDAO;
import cafe.mvc.model.dao.UsersDAOImpl;
import cafe.mvc.model.dto.Users;
import cafe.mvc.session.Session;
import cafe.mvc.session.SessionSet;

public class UsersServiceImpl implements UsersService{
	UsersDAO usersDao = new UsersDAOImpl();
	
	/**
	 * 회원가입: user 테이블 insert
	 * */
	@Override
	public void userInsert(Users users) throws SQLException, AddException, DuplicatedException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 회원 정보 수정: user 테이블 update(전화번호/이름/적립금...?)
	 * */
	@Override
	public void userUpdate(Users users) throws SQLException, ModifyException, NotFoundException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 로그인
	 * */
	@Override
	public Users login(String userTel, int userPwd) throws SQLException, NotFoundException {
		Users users = usersDao.login(userTel, userPwd);
		
		if(users == null) { // 아이디와 비밀번호가 일치하는지 확인
			throw new NotFoundException("아이디 혹은 비밀번호가 잘못 되었습니다.");
		}
		
		// 로그인 된 정보를 세션 객체로 저장
		Session session = new Session(userTel);
		
		// 세션에 저장한 로그인 정보를 세션셋에 저장
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		return users;
	}
	/**
	 * 적립금 확인: user 테이블 select
	 * */
	@Override
	public Users selectPointByUserTel(String userTel) throws SQLException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
