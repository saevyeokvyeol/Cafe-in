package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cafe.mvc.model.dto.Product;
import cafe.mvc.util.DbUtil;

public class ProductDAOImpl implements ProductDAO {

	/**
	 * ���� ���: product ���̺� ���ڵ� insert
	 * */
	@Override
	public int drinkInsert(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * ����Ʈ ���: product ���̺�, stock ���̺� ���ڵ� insert
	 * */
	@Override
	public int dessertInsert(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����)
	 * */
	@Override
	public int productUpdate(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ����Ʈ ��� ����
	 * : stock ���̺� ���ڵ� update, product ���̺� ���ڵ� update
	 *   ���� stock ���̺� ��� ������ 0 ���Ϸ� �������� ���ϵ��� �ϰ�
	 *   stock ���̺� ��� ������ 0�� �� �ڵ����� ����Ʈ ǰ�� ���ΰ� yes�� �ǵ���...?
	 * */
	@Override
	public int dessertStockUpdate(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ���� ����: product ���̺� ���ڵ� delete
	 * */
	@Override
	public int drinkDelete(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ����Ʈ ����: product ���̺�, stock ���̺� ���ڵ� delete
	 * */
	@Override
	public int dessertDelete(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ��ü ��ǰ ����
	 * : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
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
