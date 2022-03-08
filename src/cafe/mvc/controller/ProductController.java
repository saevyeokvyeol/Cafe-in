package cafe.mvc.controller;

import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Stock;
import cafe.mvc.model.service.ProductService;
import cafe.mvc.model.service.ProductServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.SuccessView;

public class ProductController {
	static ProductService productService = new ProductServiceImpl();
	
	/**
	 * 음료 메뉴 등록
	 * */
	public static void drinkInsert(Product product) {
		try {
			productService.drinkInsert(product);
			SuccessView.printMessage("음료 등록 완료");
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 디저트 메뉴등록
	 * */
	public static void dessertInsert(Product product) {
		try {
			productService.dessertInsert(product);
			SuccessView.printMessage("디저트 등록 완료");
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 메뉴수정(디저트 & 음료정보)
	 * */
	public static void productUpdate(Product product) {
		try {
		  productService.productUpdate(product);
		  SuccessView.printMessage("정보 수정 완료");
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * 상품 메뉴 삭제
	 * */
	public static void productDelete(String prodCode) {
		try {
		  productService.productDelete(prodCode);
		// if() 상품코드 찾아서 stock 0개인 제품찾아서 아래 메소드 호출
//		  productService.stockDelete(prodCode); //디저트 상품삭제(스턱)
		  SuccessView.printMessage("상품 삭제 완료");
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * 디저트 재고 수정
	 * */
	public static void dessertStockUpdate(Stock stock) {
		try {
		  productService.dessertStockUpdate(stock);
		  SuccessView.printMessage("디저트 재고 수정 완료");
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 상품 코드로 상품 검색
	 * */
	
	public static void selectByProdCode(String prodCode) {
		try {
			Product product = productService.selectByProdCode(prodCode);
			SuccessView.printSelectProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 상품 상태 변경
	 * */
	public static void productStateUpdate(String prodCode, int prodState) {
		try {
			  productService.productStateUpdate(prodCode,prodState);
			  SuccessView.printMessage("상품 상태 변경 완료");
			}catch (Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
		
	}
}
