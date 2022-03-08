package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dao.ProductDAO;
import cafe.mvc.model.dao.ProductDAOImpl;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Stock;


public class ProductServiceImpl implements ProductService {

	ProductDAO productDao = new ProductDAOImpl();
	
	/**
	 * ���� ���: product ���̺� ���ڵ� insert
	 * ����ϱ� ����, �����ڵ� �ߺ�üũ - selectByProdCode
	 * */
	@Override
	public void drinkInsert(Product product) throws SQLException, AddException, DuplicatedException {
		int result = productDao.drinkInsert(product);
		if(result==0)throw new SQLException("��� ����");
	}

	/**
	 * ����Ʈ ���: product ���̺�, stock ���̺� ���ڵ� insert
	 * */
	@Override
	public void dessertInsert(Product product) throws SQLException, AddException, DuplicatedException {
		int result = productDao.dessertInsert(product);
		if(result==0)throw new SQLException("��� ����");
	}

	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����)
	 * */
	@Override
	public void productUpdate(Product product) throws SQLException, ModifyException, NotFoundException {
		int result = productDao.productUpdate(product);
		if(result==0)throw new SQLException("���� ����");
	}
	/**
	 * ����Ʈ ��� ����
	 * : stock ���̺� ���ڵ� update, product ���̺� ���ڵ� update
	 *   ���� stock ���̺� ��� ������ 0 ���Ϸ� �������� ���ϵ��� �ϰ�
	 *   stock ���̺� ��� ������ 0�� �� �ڵ����� ����Ʈ ǰ�� ���ΰ� yes�� �ǵ���...?
	 * */
	@Override
	public void dessertStockUpdate(Stock stock) throws SQLException, ModifyException, NotFoundException {
		int result = productDao.dessertStockUpdate(stock);
		if(result==0)throw new SQLException("���� ����");

	}
	/**
	 * ��ǰ ����: product ���̺� ���ڵ� delete
	 * */
	@Override
	public void productDelete(String prodCode) throws SQLException {
		int result = productDao.productDelete(prodCode);
		if(result == 0) {
			throw new SQLException("���� ����");
		}
	}

	/**
	 * ��ü��ǰ�˻�
	 * */
	@Override
	public List<Product> selectAll(String ProdCode) throws SQLException, NotFoundException {
		// TODO Auto-generated method stub
		List<Product> productList = productDao.selectAll();
		return productList;
	}

    //����Ʈ���
	public void stockDelete(String prodCode) throws SQLException, ModifyException, NotFoundException {
		int result = productDao.stockDelete(prodCode);
		if(result==0)throw new SQLException("���� ����");
	}

	/**
	 * ī�װ��� ��ǰ ����
	 * : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 * */
	// List<Product>
	@Override
	public List<Product> selectByGroup(String groupCode) throws SQLException, NotFoundException {
		List<Product> productList = productDao.selectByGroup(groupCode);
		return productList;
	}
	
	/**
	 * ��ǰ �ڵ�� ��ǰ �˻�
	 * */
	public Product selectByProdCode(String prodCode) throws SQLException, NotFoundException {
		Product product = productDao.selectByProdCode(prodCode);
		
		if(product == null) {
			throw new NotFoundException(prodCode + " ��ǰ�� ã�� �� �����ϴ�.");
		}
		
		return product;
	}
}
