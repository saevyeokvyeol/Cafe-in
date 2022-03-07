package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import cafe.mvc.model.dto.Users;
import cafe.mvc.util.DbUtil;

public class UsersDAOImpl implements UsersDAO{
	private Properties proFile = DbUtil.getProFile();

	/**
	 * 회원가입: user 테이블 insert
	 * */
	@Override
	public int userInsert(Users users) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("users.insert");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, users.getUserTel());
			ps.setString(2, users.getUserName());
			ps.setInt(3, users.getUserPwd());
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
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
