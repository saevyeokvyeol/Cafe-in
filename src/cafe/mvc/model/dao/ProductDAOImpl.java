package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Stock;
import cafe.mvc.util.DbUtil;



public class ProductDAOImpl implements ProductDAO {
	Properties profile = DbUtil.getProFile();
	Product product = new Product();

	//���� ���: product ���̺� ���ڵ� insert
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
			ps.setString(2, product.getProdGroup());
			ps.setString(3, product.getProdName());
			ps.setInt(4, product.getProdPrice());
			ps.setString(5, product.getProdDetail());
			ps.setInt(6, product.getSoldOut());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}


	//����Ʈ ���: product ���̺�, stock ���̺� ���ڵ� insert
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
	
	//��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����..?)
	@Override
	public int productUpdate(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		String sql = profile.getProperty("product.update");
		// update product set prod_price = ?, prod_detail = ?, soldOut = ? where prod_code = ? 
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, product.getProdPrice());
			ps.setString(2, product.getProdDetail());
			ps.setInt(3, product.getSoldOut());
			ps.setString(4, product.getProdCode());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	//����Ʈ ��� ����
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

	
	//��ǰ ����: product ���̺� ���ڵ� delete
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
	
	//����Ʈ ��� ����: product ���̺�, stock ���̺� ���ڵ� delete
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
	
	//�ֵ�ƿ����� ���� soldout //�̰� ��ǰ�˻� �޼ҵ� �ϼ��ǰ�
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
	 * ��ü ��ǰ ����
	 * : (ī�װ� ���� X)
	 * */
	@Override
	public List<Product> selectAll() throws SQLException {

		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();
		
		try {
			conn = DbUtil.getConnection();
			String sql = String.format("select * from product", selectByProdCode(null));
			ps = conn.prepareStatement(sql);
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
			String sql = String.format("select * from product where PROD_GROUP = '%s'",  groupCode);
			ps = conn.prepareStatement(sql);
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
	public Product selectByProdCode(String ProdCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
