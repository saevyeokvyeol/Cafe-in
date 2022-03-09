package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Stock;
import cafe.mvc.util.DbUtil;

public class ProductDAOImpl implements ProductDAO {
	Properties profile = DbUtil.getProFile();
	Product product = new Product();

	/**
	 * 상품 등록: product 테이블 레코드 insert
	 */
	@Override
	public int productInsert(Product product) throws SQLException {

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
	 * 상품 수정: product 테이블 레코드 update(판매 가격, 상세 정보, 품절 여부..?)
	 */
	@Override
	public int productUpdate(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.update");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, product.getProdPrice());
			ps.setString(2, product.getProdDetail());
			ps.setString(3, product.getProdCode());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	/**
	 * 디저트 재고 등록
	 */
	@Override
	public int dessertStockInsert(Stock stock) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("dessertStock.insert");
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
	 * 디저트 재고 수정
	 */
	@Override
	public int dessertStockUpdate(Stock stock) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("dessertStock.update");
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
	 * 디저트 재고 조회
	 */
	public Stock selectStock(Connection con, String prodCode) throws SQLException {
		String sql = profile.getProperty("product.selectStock");

		PreparedStatement ps = null;
		ResultSet rs = null;

		Stock stock = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, prodCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				stock = new Stock(prodCode, rs.getInt(1));
			}
		} finally {
			DbUtil.close(null, ps, rs);
		}
		return stock;
	}


	/**
	 * 디저트의 재고가 0이면 상품상태 0(판매중지)만들기
	 */
	public int dessertsoldOutUpdate(String prodCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("soldout"); // stock 0 ->soldOut
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
	 * 상품 상태 변경
	 */
	@Override
	public int productStateUpdate(String prodCode, int prodState) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("productState.update");
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
	 * 전체 상품 보기 : (카테고리 구분 X) <productSelectAll>
	 */
	@Override
	public List<Product> productSelectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<>();
		String sql = profile.getProperty("product.selectAll");

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
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
	 * 카테고리별 상품 보기 : 상품분류코드를 통해 각 카테고리에 맞는 상품만 조회
	 */
	@Override
	public List<Product> productSelectByGroup(String groupCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();
		String sql = profile.getProperty("product.selectByGroup");

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, groupCode);
			rs = ps.executeQuery();

			while (rs.next()) { 
				Product product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
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
	 * 상품 코드로 상품 검색
	 */
	@Override
	public Product productSelectByProdCode(String prodCode) throws SQLException {
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
			
			if(prodCode.substring(0, 1).equals("D")) {
				product.setStock(selectStock(con, prodCode));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return product;
	}
}
