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
	 * ��ٱ��� �߰�
	 * */
	public static void putCart(String userTel, String prodCode, int qty) {
		try {

			ProductDTO product = productService.productSelectByProdCode(prodCode); // ��ǰ ��ȣ�� �ش��ϴ� ��ǰ �˻�

			
			if(qty <= 0) {
				throw new AddException("��ǰ ������ 1�� �̻� �Է����ּ���.");
			}
			
			if(product.getStock() != null) {
				if(product.getStock().getProdStock() < qty) {
					throw new AddException("����� ������ " + product.getProdName() + " ��ǰ�� ���� �� �����ϴ�.");
				}
			}
			
			// id�� �ش��ϴ� ������ �˻�
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userTel);
			
			// ���ǿ��� ��ٱ��� ���� ��������
			Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>) session.getAttributes("cart");
			
			// ���� ��ٱ��� ������ �������� ������ ��ٱ��� ����
			if(cart == null) {
				cart = new HashMap<>();
				session.setAttribute("cart", cart);
			}
			
			// ��ٱ��Ͽ��� ��ǰ ã��
			Integer oldQty = cart.get(product);
			if(oldQty != null) { // ��ٱ��Ͽ� �̹� �ش� ��ǰ�� �ִٸ�
				qty += oldQty; // ������ ����
			}
			
			cart.put(product, qty);
			
			SuccessView.printMessage("��ٱ��Ͽ� ��ǰ�� ��ҽ��ϴ�.");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * ��ٱ��� ����
	 * */
	public static void viewCart(String userTel) {
		try {
			//id(=��ȭ��ȣ)�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userTel);
			
			// ���ǿ��� ��ٱ��� ���� ��������
			Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>) session.getAttributes("cart");
			
			if(cart == null || cart.isEmpty()) { // ��ٱ��Ͽ� ��ǰ�� ���� ���
				FailView.errorMessage("��ٱ��Ͽ� ��ǰ�� �����ϴ�.");
			}

			String userName = new UsersServiceImpl().selectByUserTel(userTel).getUserName();
			SuccessView.printCart(userName, cart);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * ��ٱ��� �κ� ����
	 * */
	public static void deleteCartByCode(String userTel, String prodCode) {
		try {
			//id(=��ȭ��ȣ)�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userTel);
			
			// ���ǿ��� ��ٱ��� ���� ��������
			Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>) session.getAttributes("cart");
			
			if(cart == null || cart.isEmpty()) { // ��ٱ��Ͽ� ��ǰ�� ���� ���
				FailView.errorMessage("��ٱ��Ͽ� ��ǰ�� �����ϴ�.");
			}
					
			Object deleteValue = cart.remove(prodCode);
			if(deleteValue == null) {
				FailView.errorMessage("��ٱ��Ͽ� " + prodCode + "��ǰ�� �����ϴ�.");
			}
			SuccessView.printMessage(prodCode + " ��ǰ�� ��ٱ��Ͽ��� �����߽��ϴ�.");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * ��ٱ��� ��ü ����
	 * */
	public static void deleteCartAll(String userTel) {
		try {
			//id(=��ȭ��ȣ)�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userTel);
			
			// ���ǿ��� ��ٱ��� ���� ��������
			Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>) session.getAttributes("cart");
			
			if(cart == null || cart.isEmpty()) { // ��ٱ��Ͽ� ��ǰ�� ���� ���
				FailView.errorMessage("��ٱ��Ͽ� ��ǰ�� �����ϴ�.");
			}
			
			cart.clear();
			SuccessView.printMessage("��ٱ��Ͽ� ��� ��ǰ�� ��� �����߽��ϴ�.");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
}
