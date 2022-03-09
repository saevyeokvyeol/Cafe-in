package cafe.mvc.model.dao;

import java.sql.SQLException;

import cafe.mvc.model.dto.UsersDTO;
// user
public interface UsersDAO {
	/**
	 * 회원가입: user 테이블 insert
	 * */
	int userInsert(UsersDTO usersDTO) throws SQLException;
	
	/**
	 * 회원 정보 수정: user 테이블 update(전화번호/이름/적립금...?)
	 * */
	int userUpdate(UsersDTO usersDTO) throws SQLException;
	
	/**
	 * 로그인
	 * */
	UsersDTO login(String userTel, int userPwd) throws SQLException;
	
	/**
	 * 적립금 확인: user 테이블 select
	 * */
	UsersDTO userPointCh(String userTel) throws SQLException;
	
	/**
	 * 전화번호로 유저 검색
	 * */
	UsersDTO selectByUserTel(String userTel) throws SQLException;
	
	/**
	 * 더 필요한 메소드 있을까요?
	 * */
}