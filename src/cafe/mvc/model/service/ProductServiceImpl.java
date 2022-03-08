package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dao.ProductDAO;
import cafe.mvc.model.dao.ProductDAOImpl;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Stock;


public class ProductServiceImpl implements ProductService {

	ProductDAO productDao = new ProductDAOImpl();
	
	/**
	 * 음료 등록: product 테이블 레코드 insert
	 * 등록하기 전에, 음료코드 중복체크 - selectByProdCode
	 * */
	@Override
	public void drinkInsert(Product product) throws SQLException, AddException, DuplicatedException {
		int result = productDao.drinkInsert(product);
		if(result==0)throw new SQLException("등록 실패");
	}

	/**
	 * 디저트 등록: product 테이블, stock 테이블 레코드 insert
	 * */
	@Override
	public void dessertInsert(Product product) throws SQLException, AddException, DuplicatedException {
		int result = productDao.dessertInsert(product);
		if(result==0)throw new SQLException("등록 실패");
	}

	/**
	 * 상품 수정: product 테이블 레코드 update(판매 가격, 상세 정보, 품절 여부)
	 * */
	@Override
	public void productUpdate(Product product) throws SQLException, ModifyException, NotFoundException {
		int result = productDao.productUpdate(product);
		if(result==0)throw new SQLException("수정 실패");
	}
	/**
	 * 디저트 재고 수정
	 * : stock 테이블 레코드 update, product 테이블 레코드 update
	 *   만일 stock 테이블 재고 수량이 0 이하로 내려가지 못하도록 하고
	 *   stock 테이블 재고 수량이 0일 때 자동으로 디저트 품절 여부가 yes가 되도록...?
	 * */
	@Override
	public void dessertStockUpdate(Stock stock) throws SQLException, ModifyException, NotFoundException {
		int result = productDao.dessertStockUpdate(stock);
		if(result==0)throw new SQLException("수정 실패");

	}
	/**
	 * 상품 삭제: product 테이블 레코드 delete
	 * */
	@Override
	public void productDelete(String prodCode) throws SQLException {
		int result = productDao.productDelete(prodCode);
		if(result == 0) {
			throw new SQLException("삭제 실패");
		}
	}

	/**
	 * 전체상품검색
	 * */
	@Override
	public List<Product> selectAll(String ProdCode) throws SQLException, NotFoundException {
		// TODO Auto-generated method stub
		List<Product> productList = productDao.selectAll();
		return productList;
	}

    //디저트재고
	public void stockDelete(String prodCode) throws SQLException, ModifyException, NotFoundException {
		int result = productDao.stockDelete(prodCode);
		if(result==0)throw new SQLException("삭제 실패");
	}

	/**
	 * 카테고리별 상품 보기
	 * : 상품분류코드를 통해 각 카테고리에 맞는 상품만 조회
	 * */
	// List<Product>
	@Override
	public List<Product> selectByGroup(String groupCode) throws SQLException, NotFoundException {
		List<Product> productList = productDao.selectByGroup(groupCode);
		return productList;
	}
	
	/**
	 * 상품 코드로 상품 검색
	 * */
	public Product selectByProdCode(String prodCode) throws SQLException, NotFoundException {
		Product product = productDao.selectByProdCode(prodCode);
		
		if(product == null) {
			throw new NotFoundException(prodCode + " 상품을 찾을 수 없습니다.");
		}
		
		return product;
	}
}
