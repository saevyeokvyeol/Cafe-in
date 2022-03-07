package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import cafe.mvc.model.dto.Users;
import cafe.mvc.util.DbUtil;

public class UsersDAOImpl implements UsersDAO{

	private Properties proFile = DbUtil.getProFile();

	/**
	 * ȸ������: user ���̺� insert
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
	 * ȸ�� ���� ����: user ���̺� update(��ȭ��ȣ/�̸�/������...?)
	 * */

	@Override
	public int userUpdate(Users users) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * �α���
	 * */
	@Override
	public Users login(String userTel, int userPwd) throws SQLException {
		// con, ps, rs, ���ϰ� ����, sql�� �ܾ����
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Users users = null;
		String sql = proFile.getProperty("users.login");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userTel);
			ps.setInt(2, userPwd);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				users = new Users(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return users;
	}

	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	@Override
	public Users selectPointByUserTel(String userTel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}