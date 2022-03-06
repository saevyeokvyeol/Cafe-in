package cafe.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.model.dto.Product;

public class ProductDAOImpl implements ProductDAO {

	/**
	 * 음료 등록: product 테이블 레코드 insert
	 * */
	@Override
	public int drinkInsert(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * 디저트 등록: product 테이블, stock 테이블 레코드 insert
	 * */
	@Override
	public int dessertInsert(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 상품 수정: product 테이블 레코드 update(판매 가격, 상세 정보, 품절 여부)
	 * */
	@Override
	public int productUpdate(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 디저트 재고 수정
	 * : stock 테이블 레코드 update, product 테이블 레코드 update
	 *   만일 stock 테이블 재고 수량이 0 이하로 내려가지 못하도록 하고
	 *   stock 테이블 재고 수량이 0일 때 자동으로 디저트 품절 여부가 yes가 되도록...?
	 * */
	@Override
	public int dessertStockUpdate(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 음료 삭제: product 테이블 레코드 delete
	 * */
	@Override
	public int drinkDelete(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 디저트 삭제: product 테이블, stock 테이블 레코드 delete
	 * */
	@Override
	public int dessertDelete(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 전체 상품 보기
	 * : 상품분류코드를 통해 각 카테고리에 맞는 상품만 조회
	 * */
	@Override
	public List<Product> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 카테고리별 상품 보기
	 * : 상품분류코드를 통해 각 카테고리에 맞는 상품만 조회
	 * */
	@Override
	public List<Product> selectByGroup(String groupCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 상품 코드로 상품 검색
	 * */

	@Override
	public Product selectByProdCode(String ProdCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
