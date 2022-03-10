package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.model.dto.StockDTO;
import cafe.mvc.util.DbUtil;

public class ProductDAOImpl implements ProductDAO {
	Properties profile = DbUtil.getProFile();
	ProductDTO productDTO = new ProductDTO();

	/**
	 * ��ǰ ���: product ���̺� ���ڵ� insert
	 */
	@Override
	public int productInsert(ProductDTO product) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.productInsert");
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getProdCode());
			ps.setString(2, product.getProdCode().substring(0, 1));
			ps.setString(3, product.getProdName());
			ps.setInt(4, product.getProdPrice());
			ps.setString(5, product.getProdDetail());
			ps.setInt(6, product.getProdState());

			result = ps.executeUpdate();
			
			con.commit();
			
		} finally {
			con.commit();
			DbUtil.close(con, ps);
		}
		return result;
	}
	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����..?)
	 */
	@Override
	public int productUpdate(ProductDTO productDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.productUpdate");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, productDTO.getProdPrice());
			ps.setString(2, productDTO.getProdDetail());
			ps.setString(3, productDTO.getProdCode());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	/**
	 * ����Ʈ ��� ���
	 */
	@Override
	public int dessertStockInsert(StockDTO stock) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.dessertStockInsert");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, stock.getProdStock());
			ps.setString(2, stock.getProdCode());
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}


	/**
	 * ����Ʈ ��� ����
	 */
	@Override
	public int dessertStockUpdate(StockDTO stockDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.dessertStockUpdate");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, stockDTO.getProdStock());
			ps.setString(2, stockDTO.getProdCode());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	/**
	 * ����Ʈ ��� ��ȸ
	 */
	public StockDTO selectStock(Connection con, String prodCode) throws SQLException {
		String sql = profile.getProperty("product.selectStock");

		PreparedStatement ps = null;
		ResultSet rs = null;

		StockDTO stock = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, prodCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				stock = new StockDTO(prodCode, rs.getInt(1));
			}
		} finally {
			DbUtil.close(null, ps, rs);
		}
		return stock;
	}


	/**
	 * ����Ʈ�� ��� 0�̸� ��ǰ���� 0(�Ǹ�����)�����
	 */
	public int dessertsoldOutUpdate(String prodCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.soldout"); // stock 0 ->soldOut
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * ��ǰ ���� ����
	 */
	@Override
	public int productStateUpdate(String prodCode, int prodState) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.stateUpdate");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, prodState);
			ps.setString(2, prodCode);
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * ��ü ��ǰ ���� : (ī�װ� ���� X) <productSelectAll>
	 */
	@Override

	public List<ProductDTO> productSelectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> list = new ArrayList<>();
		String sql = profile.getProperty("product.productSelectAll");

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductDTO product = new ProductDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getInt(6));
				if (product.getProdCode().substring(0, 1).equals("D")) {
					product.setStock(selectStock(con, product.getProdCode()));
				}
				list.add(product);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	/**
	 * ī�װ��� ��ǰ ���� : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 */
	@Override

	public List<ProductDTO> productSelectByGroup(String groupCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productList = new ArrayList<>();
		String sql = profile.getProperty("product.productSelectByGroup");


		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, groupCode);
			rs = ps.executeQuery();

			while (rs.next()) { 
				ProductDTO product = new ProductDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getInt(6));
				if (product.getProdCode().substring(0, 1).equals("D")) {
					product.setStock(selectStock(con, product.getProdCode()));
				}
				productList.add(product);

			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return productList;
	}

	/**
	 * ��ǰ �ڵ�� ��ǰ �˻�
	 */
	@Override
	public ProductDTO productSelectByProdCode(String prodCode) throws SQLException {

		String sql = profile.getProperty("product.productSelectByProdCode");

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ProductDTO productDTO = null;

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, prodCode);

			rs = ps.executeQuery();
			
			if(rs.next()) {
				productDTO = new ProductDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
			}
			
			if(prodCode.substring(0, 1).equals("D")) {
				productDTO.setStock(selectStock(con, prodCode));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return productDTO;
	}
}
