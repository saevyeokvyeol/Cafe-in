package cafe.mvc.model.dao;

import java.sql.SQLException;

import cafe.mvc.model.dto.Users;
// user
public interface CustmerDAO {
	/**
	 * 회원가입: user 테이블 insert
	 * */
	int userInsert(Users users) throws SQLException;
	
	/**
	 * 회원 정보 수정: user 테이블 update(전화번호/이름/적립금...?)
	 * */
	int userUpdate(Users users) throws SQLException;
	
	/**
	 * 로그인
	 * */
	Users login(String userTel, String userPwd) throws SQLException;
	
	/**
	 * 적립금 확인: user 테이블 select
	 * */
	Users selectPointByUserTel(String userTel) throws SQLException;
	
	/**
	 * 더 필요한 메소드 있을까요?
	 * */
}