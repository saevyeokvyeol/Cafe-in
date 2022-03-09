package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dao.ProductDAO;
import cafe.mvc.model.dao.ProductDAOImpl;
import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.model.dto.StockDTO;

public class ProductServiceImpl implements ProductService {

	ProductDAO productDao = new ProductDAOImpl();

	/**
	 * 상품등록: product 테이블 레코드 insert 등록하기 전에, 음료코드 중복체크 - selectByProdCode
	 */
	@Override
	public void productInsert(ProductDTO product) throws SQLException, AddException, DuplicatedException {
		int result = productDao.productInsert(product);
		if (result == 0)
			throw new SQLException("등록 실패");
	}

	/**
	 * 상품 수정: product 테이블 레코드 update(판매 가격, 상세 정보, 품절 여부)
	 */
	@Override
	public void productUpdate(ProductDTO productDTO) throws SQLException, ModifyException, NotFoundException {
		int result = productDao.productUpdate(productDTO);
		if (result == 0)
			throw new SQLException("수정 실패");
	}

	/**
	 * 디저트 재고 수정 : stock 테이블 레코드 update, product 테이블 레코드 update 만일 stock 테이블 재고 수량이
	 * 0 이하로 내려가지 못하도록 하고 stock 테이블 재고 수량이 0일 때 자동으로 디저트 품절 여부가 yes가 되도록...?
	 */
	@Override
	public void dessertStockUpdate(StockDTO stockDTO) throws SQLException, ModifyException, NotFoundException {
		int result = productDao.dessertStockUpdate(stockDTO);
		if (result == 0)
			throw new SQLException("수정 실패");

	}


	/**
	 * 상품 상태 변경
	 */
	@Override
	public void productStateUpdate(String prodCode, int prodState)
			throws SQLException, ModifyException, NotFoundException {
		int result = productDao.productStateUpdate(prodCode, prodState);
		if (result == 0)
			throw new SQLException("수정 실패");
	}

	/**
	 * 전체상품검색
	 */
	@Override
	public List<ProductDTO> productSelectAll() throws SQLException, NotFoundException {
		List<ProductDTO> list = productDao.productSelectAll();
		if (list.size() == 0)
			throw new NotFoundException("현재 상품이 없습니다.");
		return list;
	}

	/**
	 * 카테고리별 상품 보기 : 상품분류코드를 통해 각 카테고리에 맞는 상품만 조회
	 */
	// List<Product>
	@Override
	public List<ProductDTO> productSelectByGroup(String groupCode) throws SQLException, NotFoundException {
		List<ProductDTO> productList = productDao.productSelectByGroup(groupCode);
		return productList;
	}

	/**
	 * 상품 코드로 상품 검색
	 */
	public ProductDTO productSelectByProdCode(String prodCode) throws SQLException, NotFoundException {
		ProductDTO productDTO = productDao.productSelectByProdCode(prodCode);
		if (productDTO == null) {
			throw new NotFoundException(prodCode + " 상품을 찾을 수 없습니다.");
		}

		return productDTO;
	}
}
