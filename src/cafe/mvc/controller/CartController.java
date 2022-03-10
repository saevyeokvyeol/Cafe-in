package cafe.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cafe.mvc.exception.AddException;
import cafe.mvc.model.dto.OrderLineDTO;
import cafe.mvc.model.dto.OrdersDTO;
import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.model.service.ProductService;
import cafe.mvc.model.service.ProductServiceImpl;
import cafe.mvc.model.service.UsersServiceImpl;
import cafe.mvc.session.Session;
import cafe.mvc.session.SessionSet;
import cafe.mvc.view.FailView;
import cafe.mvc.view.SuccessView;

public class CartController {
	private static ProductService productService = new ProductServiceImpl();
	
	/**
	 * 장바구니 추가
	 * */
	public static void putCart(String userTel, String prodCode, int qty) {
		try {

			ProductDTO product = productService.productSelectByProdCode(prodCode); // 상품 번호에 해당하는 상품 검색

			
			if(qty <= 0) {
				throw new AddException("상품 수량을 1개 이상 입력해주세요.");
			}
			
			if(product.getStock() != null) {
				if(product.getStock().getProdStock() < qty) {
					throw new AddException("재고량이 부족해 " + product.getProdName() + " 상품을 담을 수 없습니다.");
				}
			}
			
			// id에 해당하는 세션을 검색
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userTel);
			
			// 세션에서 장바구니 정보 가져오기
			Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>) session.getAttributes("cart");
			
			// 만일 장바구니 정보가 존재하지 않으면 장바구니 생성
			if(cart == null) {
				cart = new HashMap<>();
				session.setAttribute("cart", cart);
			}
			
			// 장바구니에서 상품 찾기
			Integer oldQty = cart.get(product);
			if(oldQty != null) { // 장바구니에 이미 해당 상품이 있다면
				qty += oldQty; // 수량을 누적
			}
			
			cart.put(product, qty);
			
			SuccessView.printMessage("장바구니에 상품을 담았습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니 보기
	 * */
	public static void viewCart(String userTel) {
		try {
			//id(=전화번호)에 해당하는 세션찾기
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userTel);
			
			// 세션에서 장바구니 정보 가져오기
			Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>) session.getAttributes("cart");
			
			if(cart == null || cart.isEmpty()) { // 장바구니에 상품이 없을 경우
				FailView.errorMessage("장바구니에 상품이 없습니다.");
			}

			String userName = new UsersServiceImpl().selectByUserTel(userTel).getUserName();
			SuccessView.printCart(userName, cart);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니 부분 삭제
	 * */
	public static void deleteCartByCode(String userTel, String prodCode) {
		try {
			//id(=전화번호)에 해당하는 세션찾기
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userTel);
			
			// 세션에서 장바구니 정보 가져오기
			Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>) session.getAttributes("cart");
			
			if(cart == null || cart.isEmpty()) { // 장바구니에 상품이 없을 경우
				FailView.errorMessage("장바구니에 상품이 없습니다.");
			}
					
			Object deleteValue = cart.remove(prodCode);
			if(deleteValue == null) {
				FailView.errorMessage("장바구니에 " + prodCode + "상품이 없습니다.");
			}
			SuccessView.printMessage(prodCode + " 상품을 장바구니에서 제거했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니 전체 삭제
	 * */
	public static void deleteCartAll(String userTel) {
		try {
			//id(=전화번호)에 해당하는 세션찾기
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userTel);
			
			// 세션에서 장바구니 정보 가져오기
			Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>) session.getAttributes("cart");
			
			if(cart == null || cart.isEmpty()) { // 장바구니에 상품이 없을 경우
				FailView.errorMessage("장바구니에 상품이 없습니다.");
			}
			
			cart.clear();
			SuccessView.printMessage("장바구니에 담긴 상품을 모두 삭제했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
}
