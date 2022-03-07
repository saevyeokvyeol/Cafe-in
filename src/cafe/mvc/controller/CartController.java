package cafe.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import cafe.mvc.exception.AddException;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.service.ProductService;
import cafe.mvc.model.service.ProductServiceImpl;
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
			Product product = productService.selectByProdCode(prodCode); // ��ǰ ��ȣ�� �ش��ϴ� ��ǰ �˻�
			
			if(product.getStock().getProdStock() < qty) {
				throw new AddException("����� ������ " + product.getProdName() + " ��ǰ�� ���� �� �����ϴ�.");
			}
			
			// id�� �ش��ϴ� ������ �˻�
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userTel);
			
			// ���ǿ��� ��ٱ��� ���� ��������
			Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttributes("cart");
			
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
		//id(=��ȭ��ȣ)�� �ش��ϴ� ����ã��
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(userTel);
		
		// ���ǿ��� ��ٱ��� ���� ��������
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttributes("cart");
		
		if(cart == null || cart.isEmpty()) { // ��ٱ��Ͽ� ��ǰ�� ���� ���
			FailView.errorMessage("��ٱ��Ͽ� ��ǰ�� �����ϴ�.");
		}
		
		SuccessView.printCart(userTel, cart);
	}
	
	/**
	 * ��ٱ��� �κ� ����
	 * */
	public static void cartDeleteByCode(String userTel, String prodCode) {
		//id(=��ȭ��ȣ)�� �ش��ϴ� ����ã��
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(userTel);
		
		// ���ǿ��� ��ٱ��� ���� ��������
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttributes("cart");
		
		if(cart == null || cart.isEmpty()) { // ��ٱ��Ͽ� ��ǰ�� ���� ���
			FailView.errorMessage("��ٱ��Ͽ� ��ǰ�� �����ϴ�.");
		}
		
		String deleteValue = Integer.toString(cart.remove(prodCode));
		if(deleteValue == null) {
			FailView.errorMessage("��ٱ��Ͽ� " + prodCode + "��ǰ�� �����ϴ�.");
		}
		SuccessView.printMessage(prodCode + " ��ǰ�� ��ٱ��Ͽ��� �����߽��ϴ�.");
	}
	
	/**
	 * ��ٱ��� ��ü ����
	 * */
	public static void cartDelete(String userTel) {
		//id(=��ȭ��ȣ)�� �ش��ϴ� ����ã��
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(userTel);
		
		// ���ǿ��� ��ٱ��� ���� ��������
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttributes("cart");
		
		if(cart == null || cart.isEmpty()) { // ��ٱ��Ͽ� ��ǰ�� ���� ���
			FailView.errorMessage("��ٱ��Ͽ� ��ǰ�� �����ϴ�.");
		}
		
		cart.clear();
		SuccessView.printMessage("��ٱ��Ͽ� ��� ��ǰ�� ��� �����߽��ϴ�.");
	}
}
