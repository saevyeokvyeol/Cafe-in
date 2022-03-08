package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dao.ProductDAOImpl;
import cafe.mvc.model.dto.Product;

public class ProductServiceImpl implements ProductService {
	

	ProductDAOImpl productDAO = new ProductDAOImpl();
	
	/**
	 * ���� ���: product ���̺� ���ڵ� insert
	 * */
	@Override
	public void drinkInsert(Product product) throws SQLException, AddException, DuplicatedException {
		// TODO Auto-generated method stub

	}

	/**
	 * ����Ʈ ���: product ���̺�, stock ���̺� ���ڵ� insert
	 * */
	@Override
	public void dessertInsert(Product product) throws SQLException, AddException, DuplicatedException {
		// TODO Auto-generated method stub

	}

	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����)
	 * */
	@Override
	public void productUpdate(Product product) throws SQLException, ModifyException, NotFoundException {
		// TODO Auto-generated method stub

	}
	/**
	 * ����Ʈ ��� ����
	 * : stock ���̺� ���ڵ� update, product ���̺� ���ڵ� update
	 *   ���� stock ���̺� ��� ������ 0 ���Ϸ� �������� ���ϵ��� �ϰ�
	 *   stock ���̺� ��� ������ 0�� �� �ڵ����� ����Ʈ ǰ�� ���ΰ� yes�� �ǵ���...?
	 * */
	@Override
	public void dessertStockUpdate(Product product) throws SQLException, ModifyException, NotFoundException {
		// TODO Auto-generated method stub

	}
	/**
	 * ���� ����: product ���̺� ���ڵ� delete
	 * */
	@Override
	public void drinkDelete(Product product) throws SQLException, ModifyException, NotFoundException {
		// TODO Auto-generated method stub

	}
	/**
	 * ����Ʈ ����: product ���̺�, stock ���̺� ���ڵ� delete
	 * */
	@Override
	public void dessertDelete(Product product) throws SQLException, ModifyException, NotFoundException {
		// TODO Auto-generated method stub

	}	
	/**
	 * ��ü��ǰ�˻�
	 * */
	@Override
	public List<Product> selectAll(String ProdCode) throws SQLException, NotFoundException {
		// TODO Auto-generated method stub
		List<Product> productList = productDAO.selectAll();
		return productList;
	}

	/**
	 * ī�װ��� ��ǰ ����
	 * : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 * */
	// List<Product>
	@Override
	public List<Product> selectByGroup(String groupCode) throws SQLException, NotFoundException {
		List<Product> productList = productDAO.selectByGroup(groupCode);
		return productList;
	}

}
