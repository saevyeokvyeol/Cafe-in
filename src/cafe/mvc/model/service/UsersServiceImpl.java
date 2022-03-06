package cafe.mvc.model.service;

import java.sql.SQLException;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.Users;

public class UsersServiceImpl implements UsersService{

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
	public Users login(String userTel, String userPwd) throws SQLException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
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
