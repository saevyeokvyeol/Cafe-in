package cafe.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.model.dto.Product;

public interface ProductDAO {
	/**
	 * ���� ���: product ���̺� ���ڵ� insert
	 * */
	int drinkInsert(Product product) throws SQLException;
	
	/**
	 * ����Ʈ ���: product ���̺�, stock ���̺� ���ڵ� insert
	 * */
	int dessertInsert(Product product) throws SQLException;
	
	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����)
	 * */
	int productUpdate(Product product) throws SQLException;
	
	/**
	 * ����Ʈ ��� ����
	 * : stock ���̺� ���ڵ� update, product ���̺� ���ڵ� update
	 *   ���� stock ���̺� ��� ������ 0 ���Ϸ� �������� ���ϵ��� �ϰ�
	 *   stock ���̺� ��� ������ 0�� �� �ڵ����� ����Ʈ ǰ�� ���ΰ� yes�� �ǵ���...?
	 * */
	int dessertStockUpdate(Product product) throws SQLException;
	
	/**
	 * ���� ����: product ���̺� ���ڵ� delete
	 * */
	int drinkDelete(Product product) throws SQLException;
	
	/**
	 * ����Ʈ ����: product ���̺�, stock ���̺� ���ڵ� delete
	 * */
	int dessertDelete(Product product) throws SQLException;
	
	/**
	 * ��ü ��ǰ ����
	 * : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 * */
	List<Product> selectAll() throws SQLException;
		
	/**
	 * ī�װ��� ��ǰ ����
	 * : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 * */
	List<Product> selectByGroup(String groupCode) throws SQLException;
	
	/**
	 * ��ǰ �ڵ�� ��ǰ �˻�
	 * */
	Product selectByProdCode(String ProdCode) throws SQLException;
	
	/**
	 * �� �ʿ��� �޼ҵ� �������?
	 * */
}
