package cafe.mvc.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * DB연동을 위한 로드 연결 닫기
 * */
public class DbUtil {
	// 모든 곳에서 같은 객체를 사용
	private static Properties proFile = new Properties();
	
	/**
	 * 로드
	 * */
	static {
		try {
			// 외부 ~.properties 파일 로딩
			proFile.load(new FileInputStream("resources/dbInfo.properties"));
			proFile.load(new FileInputStream("resources/users.properties"));
			proFile.load(new FileInputStream("resources/cafeinQuery.properties"));
			// cafeinQuery는 쿼리 생겼을 때 주석 풀어주세요
			
			Class.forName(proFile.getProperty("driverName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProFile() {
		return proFile;
	}

	/**
	 * 연결
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
			System.out.println("***** 시작 *****");
			Connection con = DbUtil.getConnection();
			System.out.println("con : " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 닫기(DDL 또는 DML인 경우 사용: create, insert, update, delete) 
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
	 * 닫기(DQL인 경우 사용: select)
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
