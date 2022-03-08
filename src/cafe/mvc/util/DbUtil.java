package cafe.mvc.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * DB������ ���� �ε� ���� �ݱ�
 * */
public class DbUtil {
	// ��� ������ ���� ��ü�� ���
	private static Properties proFile = new Properties();
	
	/**
	 * �ε�
	 * */
	static {
		try {
			// �ܺ� ~.properties ���� �ε�
			proFile.load(new FileInputStream("resources/dbInfo.properties"));
			proFile.load(new FileInputStream("resources/users.properties"));
			proFile.load(new FileInputStream("resources/cafeinQuery.properties"));
			// cafeinQuery�� ���� ������ �� �ּ� Ǯ���ּ���
			
			Class.forName(proFile.getProperty("driverName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProFile() {
		return proFile;
	}

	/**
	 * ����
	 * */
	public static Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(
				proFile.getProperty("url"),
				proFile.getProperty("userName"),
				proFile.getProperty("userPass")
			);
		return con;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("***** ���� *****");
			Connection con = DbUtil.getConnection();
			System.out.println("con : " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ݱ�(DDL �Ǵ� DML�� ��� ���: create, insert, update, delete) 
	 * */
	public static void close(Connection con, Statement st) {
		try {
			if(st != null) st.close();
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * �ݱ�(DQL�� ��� ���: select)
	 * */
	public static void close(Connection con, Statement st, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				close(con, st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
