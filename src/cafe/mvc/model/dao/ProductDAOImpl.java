package cafe.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.model.dto.Product;

public class ProductDAOImpl implements ProductDAO {

	/**
	 * ���� ���: product ���̺� ���ڵ� insert
	 * */
	@Override
	public int drinkInsert(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * ����Ʈ ���: product ���̺�, stock ���̺� ���ڵ� insert
	 * */
	@Override
	public int dessertInsert(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����)
	 * */
	@Override
	public int productUpdate(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ����Ʈ ��� ����
	 * : stock ���̺� ���ڵ� update, product ���̺� ���ڵ� update
	 *   ���� stock ���̺� ��� ������ 0 ���Ϸ� �������� ���ϵ��� �ϰ�
	 *   stock ���̺� ��� ������ 0�� �� �ڵ����� ����Ʈ ǰ�� ���ΰ� yes�� �ǵ���...?
	 * */
	@Override
	public int dessertStockUpdate(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ���� ����: product ���̺� ���ڵ� delete
	 * */
	@Override
	public int drinkDelete(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ����Ʈ ����: product ���̺�, stock ���̺� ���ڵ� delete
	 * */
	@Override
	public int dessertDelete(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * ��ü ��ǰ ����
	 * : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 * */
	@Override
	public List<Product> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * ī�װ��� ��ǰ ����
	 * : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 * */
	@Override
	public List<Product> selectByGroup(String groupCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * ��ǰ �ڵ�� ��ǰ �˻�
	 * */

	@Override
	public Product selectByProdCode(String ProdCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
