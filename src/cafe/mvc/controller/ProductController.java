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
	
	//���� �޴����
	public static void drinkInsert(Product product) {
		try {
			productService.drinkInsert(product);
			SuccessView.printMessage("���� ��� �Ϸ�");
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}
	
	//����Ʈ �޴����
	public static void dessertInsert(Product product) {
		try {
			productService.dessertInsert(product);
			SuccessView.printMessage("����Ʈ ��� �Ϸ�");
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}
	
	//�޴�����(����Ʈ & ��������)
	public static void productUpdate(Product product) {
		try {
		  productService.productUpdate(product);
		  SuccessView.printMessage("���� ���� �Ϸ�");
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	//��ǰ �޴�����
	public static void productDelete(String prodCode) {
		try {
		  productService.productDelete(prodCode);
		// if() ��ǰ�ڵ� ã�Ƽ� stock 0���� ��ǰã�Ƽ� �Ʒ� �޼ҵ� ȣ��
//		  productService.stockDelete(prodCode); //����Ʈ ��ǰ����(����)
		  SuccessView.printMessage("��ǰ ���� �Ϸ�");
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	//����Ʈ ��� ����
	public static void dessertStockUpdate(Stock stock) {
		try {
		  productService.dessertStockUpdate(stock);
		  SuccessView.printMessage("����Ʈ ��� ���� �Ϸ�");
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	//ī�װ��� ��ǰ ��ȸ
	public static List<Product> selectByGroup(String groupCode) {
		try {
			List<Product> list = productService.selectByGroup(groupCode);
			return list; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//��ü ��ǰ ��ȸ
	public static void selectByGroup(Product product) {
		try {
			List<Product>list = productService.selectAll(null);
			System.out.println(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
