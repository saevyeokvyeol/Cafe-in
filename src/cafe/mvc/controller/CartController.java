package cafe.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.NotFoundException;
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

			if(product.getProdState() != 1) {
				throw new AddException("�ش� ��ǰ�� ���� �Ǹ����� �ƴմϴ�.");
			}
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
			Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>)session.getAttributes("cart");
			
			// ���� ��ٱ��� ������ �������� ������ ��ٱ��� ����
			if(cart == null) {
				cart = new HashMap<>();
				session.setAttribute("cart", cart);
			}
			
			// ��ٱ��Ͽ��� ��ǰ ã��
			for(ProductDTO oldProd : cart.keySet()) {
				if(oldProd.getProdCode().equals(product.getProdCode())) {
					product = oldProd;
				}
			}
			
			Integer oldQty = cart.get(product);
			if(oldQty != null) { // ��ٱ��Ͽ� �̹� �ش� ��ǰ�� �ִٸ�
				qty += oldQty; // ������ ����
			}
			
			cart.put(product, qty);
			
			SuccessView.printMessage("��ٱ��Ͽ� ��ǰ�� ��ҽ��ϴ�.");
		} catch (Exception e) {
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
				throw new NotFoundException("��ٱ��Ͽ� ��ǰ�� �����ϴ�.");
			}
			
			if(!userTel.equals("guest")) {
				String userName = new UsersServiceImpl().selectByUserTel(userTel).getUserName();
				SuccessView.printCart(userName, cart);
			} else {
				SuccessView.printCart(userTel, cart);
			}
		} catch (Exception e) {
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
				throw new NotFoundException("��ٱ��Ͽ� ��ǰ�� �����ϴ�.");
			}
			
			ProductDTO product = new ProductServiceImpl().productSelectByProdCode(prodCode);
			
			// ��ٱ��Ͽ��� ��ǰ ã��
			for(ProductDTO oldProd : cart.keySet()) {
				if(oldProd.getProdCode().equals(product.getProdCode())) {
					product = oldProd;
				}
			}
			
			Object deleteValue = cart.remove(product);
			if(deleteValue == null) {
				throw new Exception("��ٱ��Ͽ� " + prodCode + "��ǰ�� �����ϴ�.");
			}
			SuccessView.printMessage(prodCode + " ��ǰ�� ��ٱ��Ͽ��� �����߽��ϴ�.");
		} catch (Exception e) {
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
				throw new NotFoundException("��ٱ��Ͽ� ��ǰ�� �����ϴ�.");
			}
			
			cart.clear();
			
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
