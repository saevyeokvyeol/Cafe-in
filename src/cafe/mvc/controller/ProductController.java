package cafe.mvc.controller;

import java.sql.SQLException;
import java.util.List;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Stock;
import cafe.mvc.model.service.ProductService;
import cafe.mvc.model.service.ProductServiceImpl;
import cafe.mvc.view.FailView;
import cafe.mvc.view.SuccessView;

public class ProductController {
	static ProductService productService = new ProductServiceImpl();

	/**
	 * ��ǰ���
	 */
	public static void productInsert(Product product) {
		try {
			productService.productInsert(product);
			SuccessView.printMessage("��ǰ ��� �Ϸ�");
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}


	/**
	 * �޴�����(����Ʈ & ��������)
	 */
	public static void productUpdate(Product product) {
		try {
			productService.productUpdate(product);
			SuccessView.printMessage("���� ���� �Ϸ�");
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}

	
	/**
	 * ����Ʈ ��� ����
	 */
	public static void dessertStockUpdate(Stock stock) {
		try {
			productService.dessertStockUpdate(stock);
			SuccessView.printMessage("����Ʈ ��� ���� �Ϸ�");
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ��ǰ �ڵ�� ��ǰ �˻�
	 */

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
	 * ī�װ��� ��ǰ ��ȸ
	 */
	public static void selectByGroup(String groupCode) {

		try {
			List<Product> list = productService.selectByGroup(groupCode);
			SuccessView.printByCategory(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ü ��ǰ ��ȸ
	 */
	public static void productSelectAll() {
		try {
			List<Product> list = productService.productSelectAll();
			SuccessView.printSelectAll(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ��ǰ ���� ����
	 */
	public static void productStateUpdate(String prodCode, int prodState) {
		try {
			productService.productStateUpdate(prodCode, prodState);
			SuccessView.printMessage("��ǰ ���� ���� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}
}
