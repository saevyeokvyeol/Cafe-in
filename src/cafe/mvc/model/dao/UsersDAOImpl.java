package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import cafe.mvc.model.dto.UsersDTO;
import cafe.mvc.util.DbUtil;

public class UsersDAOImpl implements UsersDAO{

	private Properties proFile = DbUtil.getProFile();

	/**
	 * ȸ������: user ���̺� insert
	 * */
	@Override
	public int userInsert(UsersDTO usersDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("users.insert");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, usersDTO.getUserTel());
			ps.setString(2, usersDTO.getUserName());
			ps.setInt(3, usersDTO.getUserPwd());
			
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
	public int userUpdate(UsersDTO usersDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("users.update");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, usersDTO.getUserPwd());
			ps.setString(2, usersDTO.getUserTel());
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	/**
	 * ������ Ȯ��: user ���̺� select
	 * */
	@Override
	public UsersDTO userPointCh(String userTel) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UsersDTO usersDTO = null;
		String sql = proFile.getProperty("users.check");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userTel);
			
			rs = ps.executeQuery();
			if(rs.next()){
				usersDTO = new UsersDTO(userTel, null, rs.getInt(1), 0);
			}
			
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return usersDTO;
	}

	/**
	 * �α���
	 * */
	@Override
	public UsersDTO login(String userTel, int userPwd) throws SQLException {
		// con, ps, rs, ���ϰ� ����, sql�� �ܾ����
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UsersDTO usersDTO = null;
		String sql = proFile.getProperty("users.login");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userTel);
			ps.setInt(2, userPwd);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				usersDTO = new UsersDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return usersDTO;
	}

	/**
	 * ��ȭ��ȣ�� ���� �˻�
	 * */
	@Override
	public UsersDTO selectByUserTel(String userTel) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UsersDTO usersDTO = null;
		String sql = proFile.getProperty("order.selectByUserTel");		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userTel);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				usersDTO = new UsersDTO(userTel, rs.getString(2), rs.getInt(3), rs.getString(4));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return usersDTO;
	}

}
