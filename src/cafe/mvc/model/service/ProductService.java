package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Stock;

public interface ProductService {
	/**
	 * 음료 등록: product 테이블 레코드 insert
	 * */
	void drinkInsert(Product product) throws SQLException, AddException, DuplicatedException;
	
	/**
	 * 디저트 등록: product 테이블, stock 테이블 레코드 insert
	 * */
	void dessertInsert(Product product) throws SQLException, AddException, DuplicatedException;
	
	/**
	 * 상품 수정: product 테이블 레코드 update(판매 가격, 상세 정보, 품절 여부)
	 * */
   void productUpdate(Product product) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * 디저트 재고 수정
	 * : stock 테이블 레코드 update, product 테이블 레코드 update
	 *   만일 stock 테이블 재고 수량이 0 이하로 내려가지 못하도록 하고
	 *   stock 테이블 재고 수량이 0일 때 자동으로 디저트 품절 여부가 yes가 되도록...?
	 * */
	void dessertStockUpdate(Stock stock) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * 음료 삭제: product 테이블 레코드 delete
	 * */
	void productDelete(String prodCode) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * 디저트 재고 삭제: product 테이블, stock 테이블 레코드 delete
	 * */
	void stockDelete(String prodCode) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * 카테고리별 상품 보기
	 * : 상품분류코드를 통해 각 카테고리에 맞는 상품만 조회
	 * */
	List<Product> selectByGroup(String groupCode) throws SQLException, NotFoundException;
	
	/**
	 * 전체상품 메뉴보기(커피/티/스무디/디저트 순서로 나옴)
	 * */
	List<Product> selectAll(String ProdCode) throws SQLException, NotFoundException;

	
}
