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
	 * 회원가입: user 테이블 insert
	 * */
	@Override
	public void userInsert(UsersDTO usersDTO) throws SQLException, AddException, DuplicatedException {
		int result = usersDAO.userInsert(usersDTO);
		if(result==0)throw new SQLException("등록되지않았습니다.^^");
	}

	/**
	 * 회원 정보 수정: user 테이블 update(전화번호/이름/적립금...?)
	 * */
	@Override
	public void userPwdUpdate(UsersDTO usersDTO) throws SQLException, ModifyException, NotFoundException {
		int result = usersDAO.userPwdUpdate(usersDTO);
		if(result==0)throw new SQLException("등록되지않았습니다.^^");
		
	}
	/**
	 * 적립금 확인: user 테이블 select
	 * */
	@Override
	public UsersDTO userPointCh(String userTel) throws SQLException, ModifyException, NotFoundException {
		UsersDTO usersDTO = usersDAO.userPointCh(userTel);
		if(usersDTO == null) {
			throw new SQLException("오류");
		}
		return usersDTO;
		
	}

	/**
	 * 로그인
	 * */
	@Override
	public UsersDTO login(String userTel, int userPwd) throws SQLException, NotFoundException {
		UsersDTO usersDTO = usersDAO.login(userTel, userPwd);
		
		if(usersDTO == null) { // 아이디와 비밀번호가 일치하는지 확인
			throw new NotFoundException("아이디 혹은 비밀번호가 잘못 되었습니다.");
		}
		
		// 로그인 된 정보를 세션 객체로 저장
		Session session = new Session(userTel);
		
		// 세션에 저장한 로그인 정보를 세션셋에 저장
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		return usersDTO;
	}
	
	/**
	 * 전체 유저 검색
	 * */
	@Override
	public List<UsersDTO> userSelectAll() throws SQLException, NotFoundException {
		List<UsersDTO> list = usersDAO.userSelectAll();
		
		if(list == null) {
			throw new NotFoundException("회원 리스트를 가져올 수 없습니다.");
		}
		return list;
	}
	
	/**
	 * 전화번호로 유저 검색
	 * */
	@Override
	public UsersDTO selectByUserTel(String userTel) throws SQLException, NotFoundException {
		UsersDTO usersDTO = usersDAO.selectByUserTel(userTel);
		
		if(usersDTO == null) {
			throw new NotFoundException("회원 정보를 찾을 수 없습니다.");
		}
		return usersDTO;
	}
}
