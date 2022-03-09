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
	 * 음료 등록: product 테이블 레코드 insert
	 */
	@Override
	public int drinkInsert(ProductDTO productDTO) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.drinkInsert");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, productDTO.getProdCode());
			ps.setString(2, productDTO.getProdCode().substring(0, 1));
			ps.setString(3, productDTO.getProdName());
			ps.setInt(4, productDTO.getProdPrice());
			ps.setString(5, productDTO.getProdDetail());
			ps.setInt(6, productDTO.getProdState());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * 디저트 등록: product 테이블, stock 테이블 레코드 insert
	 */
	@Override
	public int dessertInsert(ProductDTO productDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.dessertInsert");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			drinkInsert(productDTO);
			ps.setString(1, productDTO.getProdCode());
			ps.setInt(2, productDTO.getStock().getProdStock());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}

		return result;
	}

	/**
	 * 상품 수정: product 테이블 레코드 update(판매 가격, 상세 정보, 품절 여부..?)
	 */
	@Override
	public int productUpdate(ProductDTO productDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.update");
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
	 * 디저트 재고 수정
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
	 * 상품 삭제: product 테이블 레코드 delete
	 */
	@Override
	public int productDelete(String prodCode) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.delete");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, prodCode);

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * 디저트 재고 삭제: product 테이블, stock 테이블 레코드 delete
	 */
	@Override
	public int stockDelete(String prodCode) throws SQLException {
		productDelete(prodCode);
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = profile.getProperty("product.dessertStockDelete");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, productDTO.getProdCode());
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}

		return result;
	}

	/**
	 * 디저트의 재고가 0이면 상품상태 0(판매중지)만들기
	 */
	public int dessertsoldOutUpdate(String prodCode) throws SQLException {
		productDelete(prodCode);
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
	 * 상품 상태 변경
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
	 * 전체 상품 보기 : (카테고리 구분 X)
	 */
	@Override
	public List<ProductDTO> selectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> list = new ArrayList<>();
		String sql = profile.getProperty("product.selectAll");

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductDTO productDTO = new ProductDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getInt(6));
				if (productDTO.getProdCode().substring(0, 1).equals("D")) {
					productDTO.setStock(selectStock(con, productDTO.getProdCode()));
				}
				list.add(productDTO);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	/**
	 * 상품에 스톡 입력
	 */
	public StockDTO selectStock(Connection con, String prodCode) throws SQLException {
		String sql = profile.getProperty("product.selectStock");

		PreparedStatement ps = null;
		ResultSet rs = null;

		StockDTO stockDTO = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, prodCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				stockDTO = new StockDTO(prodCode, rs.getInt(1));
			}
		} finally {
			DbUtil.close(null, ps, rs);
		}
		return stockDTO;
	}

	/**
	 * 카테고리별 상품 보기 : 상품분류코드를 통해 각 카테고리에 맞는 상품만 조회
	 */
	@Override
	public List<ProductDTO> selectByGroup(String groupCode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productList = new ArrayList<>();

		try {
			conn = DbUtil.getConnection();
			String sql = profile.getProperty("product.selectByGroup");
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupCode);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProductDTO productDTO = new ProductDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getInt(6));
				productList.add(productDTO);
			}
		} finally {
			DbUtil.close(conn, ps, rs);
		}
		return productList;
	}

	/**
	 * 상품 코드로 상품 검색
	 */
	@Override
	public ProductDTO selectByProdCode(String prodCode) throws SQLException {
		String sql = profile.getProperty("product.selectByProdCode");

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
