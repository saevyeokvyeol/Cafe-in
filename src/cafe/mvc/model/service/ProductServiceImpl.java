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
	 * ��ǰ���: product ���̺� ���ڵ� insert ����ϱ� ����, �����ڵ� �ߺ�üũ - selectByProdCode
	 */
	@Override
	public void productInsert(ProductDTO product) throws SQLException, AddException, DuplicatedException {
		int result = productDao.productInsert(product);
		if (result == 0)
			throw new SQLException("��� ����");
	}

	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����)
	 */
	@Override
	public void productUpdate(ProductDTO productDTO) throws SQLException, ModifyException, NotFoundException {
		int result = productDao.productUpdate(productDTO);
		if (result == 0)
			throw new SQLException("���� ����");
	}

	/**
	 * ����Ʈ ��� ���� : stock ���̺� ���ڵ� update, product ���̺� ���ڵ� update ���� stock ���̺� ��� ������
	 * 0 ���Ϸ� �������� ���ϵ��� �ϰ� stock ���̺� ��� ������ 0�� �� �ڵ����� ����Ʈ ǰ�� ���ΰ� yes�� �ǵ���...?
	 */
	@Override
	public void dessertStockUpdate(StockDTO stockDTO) throws SQLException, ModifyException, NotFoundException {
		int result = productDao.dessertStockUpdate(stockDTO);
		if (result == 0)
			throw new SQLException("���� ����");

	}


	/**
	 * ��ǰ ���� ����
	 */
	@Override
	public void productStateUpdate(String prodCode, int prodState)
			throws SQLException, ModifyException, NotFoundException {
		int result = productDao.productStateUpdate(prodCode, prodState);
		if (result == 0)
			throw new SQLException("���� ����");
	}

	/**
	 * ��ü��ǰ�˻�
	 */
	@Override
	public List<ProductDTO> productSelectAll() throws SQLException, NotFoundException {
		List<ProductDTO> list = productDao.productSelectAll();
		if (list.size() == 0)
			throw new NotFoundException("���� ��ǰ�� �����ϴ�.");
		return list;
	}

	/**
	 * ī�װ��� ��ǰ ���� : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 */
	// List<Product>
	@Override
	public List<ProductDTO> productSelectByGroup(String groupCode) throws SQLException, NotFoundException {
		List<ProductDTO> productList = productDao.productSelectByGroup(groupCode);
		return productList;
	}

	/**
	 * ��ǰ �ڵ�� ��ǰ �˻�
	 */
	public ProductDTO productSelectByProdCode(String prodCode) throws SQLException, NotFoundException {
		ProductDTO productDTO = productDao.productSelectByProdCode(prodCode);
		if (productDTO == null) {
			throw new NotFoundException(prodCode + " ��ǰ�� ã�� �� �����ϴ�.");
		}

		return productDTO;
	}
}
