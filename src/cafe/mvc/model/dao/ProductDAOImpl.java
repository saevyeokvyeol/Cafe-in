package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import cafe.mvc.controller.ProductController;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Stock;
import cafe.mvc.util.DbUtil;



public class ProductDAOImpl implements ProductDAO {
	Properties profile = DbUtil.getProFile();
	Product product = new Product();

	/**
	 * ���� ���: product ���̺� ���ڵ� insert
	 * */
	@Override
	public int drinkInsert(Product product) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		String sql = profile.getProperty("drink.insert"); 
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getProdCode());
			ps.setString(2, Integer.toString(product.getProdCode().charAt(0)));
			ps.setString(3, product.getProdName());
			ps.setInt(4, product.getProdPrice());
			ps.setString(5, product.getProdDetail());
			ps.setInt(6, product.getProdState());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}


	/**
	 * ����Ʈ ���: product ���̺�, stock ���̺� ���ڵ� insert
	 * */
	@Override
	public int dessertInsert(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		String sql = profile.getProperty("dessert.insert");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			drinkInsert(product);
			ps.setString(1, product.getProdCode());
			ps.setInt(2, product.getStock().getProdStock());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		
		return result;
	}
	
	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����..?)
	 * */
	@Override
	public int productUpdate(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		String sql = profile.getProperty("product.update");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, product.getProdPrice());
			ps.setString(2, product.getProdDetail());
			ps.setString(3, product.getProdCode());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	
	/**
	 * ����Ʈ ��� ����
	 * */
	@Override
	public int dessertStockUpdate(Stock stock) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		String sql = profile.getProperty("dessertStock.update");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, stock.getProdStock());
			ps.setString(2, stock.getProdCode());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		
		return result;
	}


	/**
	 * ��ǰ ����: product ���̺� ���ڵ� delete
	 * */
	@Override
	public int productDelete(String prodCode) throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		String sql = profile.getProperty("product.delete");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, prodCode);
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	
	/**
	 * ����Ʈ ��� ����: product ���̺�, stock ���̺� ���ڵ� delete
	 * */
	@Override
	public int stockDelete(String prodCode) throws SQLException {
		productDelete(prodCode);
		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		String sql = profile.getProperty("dessertStock.delete");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getProdCode());
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}

		return result;
	}
	
	
	/**
	 * ����Ʈ�� ��� 0�̸� ��ǰ���� 0(�Ǹ�����)�����
	 * */
	public int dessertsoldOutUpdate(String prodCode) throws SQLException {
		productDelete(prodCode);
		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		String sql = profile.getProperty("soldout"); //stock 0 ->soldOut
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	
	/**
	 * ��ǰ ���� ����
	 * */
	@Override
	public int productStateUpdate(String prodCode, int prodState) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		String sql = profile.getProperty("productState.update");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, prodState);
			ps.setString(2, prodCode);
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * ��ü ��ǰ ����
	 * : (ī�װ� ���� X)
	 * */
	@Override
	public List<Product> selectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<>();
		String sql = profile.getProperty("product.selectAll");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		
			while(rs.next()) {	
				Product product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
				list.add(product);				
			}
			}finally {
					DbUtil.close(con, ps, rs);
				}
				return list;
	}
	/**
	 * ī�װ��� ��ǰ ����
	 * : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 * */
	@Override
	public List<Product> selectByGroup(String groupCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();
		
		try {
			conn = DbUtil.getConnection();
			String sql = profile.getProperty("product.selectByGroup");
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupCode);
			rs = ps.executeQuery();
		
			while(rs.next()) {	
				Product product = new Product(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6)
					);
				
				productList.add(product);				
			}
		}finally {
				DbUtil.close(conn, ps, rs);
			}
			
			return productList;
			
		}
		
	/**
	 * ��ǰ �ڵ�� ��ǰ �˻�
	 * */
	@Override
	public Product selectByProdCode(String prodCode) throws SQLException {
		String sql = profile.getProperty("product.selectByProdCode");
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Product product = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, prodCode);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
				
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return product;
	}
}
