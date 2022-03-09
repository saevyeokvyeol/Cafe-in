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
	 * ���� �޴� ���
	 */
	public static void drinkInsert(ProductDTO productDTO) {
		try {
			productService.drinkInsert(productDTO);
			SuccessView.printMessage("���� ��� �Ϸ�");
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ����Ʈ �޴����
	 */
	public static void dessertInsert(ProductDTO productDTO) {
		try {
			productService.dessertInsert(productDTO);
			SuccessView.printMessage("����Ʈ ��� �Ϸ�");
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * �޴�����(����Ʈ & ��������)
	 */
	public static void productUpdate(ProductDTO productDTO) {
		try {
			productService.productUpdate(productDTO);
			SuccessView.printMessage("���� ���� �Ϸ�");
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * ��ǰ �޴� ����
	 */
	public static void productDelete(String prodCode) {
		try {
			productService.productDelete(prodCode);
			if (productService.selectByProdCode(prodCode).getStock().getProdStock() == 0) {
				productService.stockDelete(prodCode);
			}
			SuccessView.printMessage("��ǰ ���� �Ϸ�");
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * ����Ʈ ��� ����
	 */
	public static void dessertStockUpdate(StockDTO stockDTO) {
		try {
			productService.dessertStockUpdate(stockDTO);
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
			ProductDTO productDTO = productService.selectByProdCode(prodCode);
			SuccessView.printSelectProduct(productDTO);
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
			List<ProductDTO> list = productService.selectByGroup(groupCode);
			SuccessView.printByCategory(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ü ��ǰ ��ȸ
	 */
	public static void selectAll() {
		try {
			List<ProductDTO> list = productService.selectAll();
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
