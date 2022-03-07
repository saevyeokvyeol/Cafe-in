package cafe.mvc.model.service;

import java.sql.SQLException;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.Users;

public interface UsersService {
	/**
	 * 회원가입: user 테이블 insert
	 * @return 
	 * */
	void userInsert(Users users) throws SQLException, AddException, DuplicatedException;
	
	/**
	 * 회원 정보 수정: user 테이블 update(전화번호/이름/적립금...?)
	 * */
	void userUpdate(Users users) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * 로그인
	 * */
	Users login(String userTel, int userPwd) throws SQLException, NotFoundException;
	
	/**
	 * 적립금 확인: user 테이블 select
	 * */
	Users selectPointByUserTel(String userTel) throws SQLException, NotFoundException;
	
	/**
	 * 더 필요한 메소드 있을까요?
	 * */
}
