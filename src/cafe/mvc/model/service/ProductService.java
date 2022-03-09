package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.model.dto.StockDTO;

public interface ProductService {
	/**
	 * 상품 등록: product 테이블 레코드 insert
	 */
	void productInsert(ProductDTO product) throws SQLException, AddException, DuplicatedException;

	/**
	 * 상품 수정: product 테이블 레코드 update(판매 가격, 상세 정보, 품절 여부)
	 */
	void productUpdate(ProductDTO productDTO) throws SQLException, ModifyException, NotFoundException;

	/**
	 * 디저트 재고 수정 : stock 테이블 레코드 update, product 테이블 레코드 update 만일 stock 테이블 재고 수량이
	 * 0 이하로 내려가지 못하도록 하고 stock 테이블 재고 수량이 0일 때 자동으로 디저트 품절 여부가 yes가 되도록...?
	 */
	void dessertStockUpdate(StockDTO stockDTO) throws SQLException, ModifyException, NotFoundException;

	
	/**
	 * 상품상태변경
	 */
	void productStateUpdate(String prodCode, int prodState) throws SQLException, ModifyException, NotFoundException;

	/**
	 * 카테고리별 상품 보기 : 상품분류코드를 통해 각 카테고리에 맞는 상품만 조회
	 */

	List<ProductDTO> productSelectByGroup(String groupCode) throws SQLException, NotFoundException;

	/**
	 * 전체상품 메뉴보기(커피/티/스무디/디저트 순서로 나옴)
	 */
	List<ProductDTO> productSelectAll() throws SQLException, NotFoundException;


	/**
	 * 검색한 상품 메뉴보기
	 */
	ProductDTO productSelectByProdCode(String prodCode) throws SQLException, NotFoundException;


}
