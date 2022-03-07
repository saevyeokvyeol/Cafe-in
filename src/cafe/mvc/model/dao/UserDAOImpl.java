package cafe.mvc.model.dao;

import java.sql.SQLException;

import cafe.mvc.model.dto.Users;

public class UserDAOImpl implements UsersDAO{

	/**
	 * 회원가입: user 테이블 insert
	 * */
	@Override
	public int userInsert(Users users) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 회원 정보 수정: user 테이블 update(전화번호/이름/적립금...?)
	 * */

	@Override
	public int userUpdate(Users users) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 로그인
	 * */
	@Override
	public Users login(String userTel, String userPwd) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 적립금 확인: user 테이블 select
	 * */
	@Override
	public Users selectPointByUserTel(String userTel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
