package cafe.mvc.controller;

import java.sql.SQLException;
import java.util.List;
import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.model.dto.StockDTO;
import cafe.mvc.model.service.ProductService;
import cafe.mvc.model.service.ProductServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.SuccessView;

public class ProductController {
	static ProductService productService = new ProductServiceImpl();

	/**
	 * 상품등록
	 */
	public static void productInsert(ProductDTO product) {
		try {
			productService.productInsert(product);
			SuccessView.printMessage("상품 등록 완료");
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}



	/**
	 * 메뉴수정(디저트 & 음료정보)
	 */
	public static void productUpdate(ProductDTO productDTO) {
		try {
			productService.productUpdate(productDTO);
			SuccessView.printMessage("정보 수정 완료");
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * 디저트 재고 수정
	 */
	public static void dessertStockUpdate(StockDTO stockDTO) {
		try {
			productService.dessertStockUpdate(stockDTO);
			SuccessView.printMessage("디저트 재고 수정 완료");
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 상품 코드로 상품 검색
	 */

	public static void selectByProdCode(String prodCode) {
		try {

			ProductDTO productDTO = productService.productSelectByProdCode(prodCode);
			SuccessView.printSelectProduct(productDTO);

		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 카테고리별 상품 조회
	 */
	public static void selectByGroup(String groupCode) {

		try {

			List<ProductDTO> list = productService.selectByGroup(groupCode);

			SuccessView.printByCategory(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 전체 상품 조회
	 */
	public static void productSelectAll() {
		try {
			List<ProductDTO> list = productService.productSelectAll();
			SuccessView.printSelectAll(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 상품 상태 변경
	 */
	public static void productStateUpdate(String prodCode, int prodState) {
		try {
			productService.productStateUpdate(prodCode, prodState);
			SuccessView.printMessage("상품 상태 변경 완료");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}
}
