package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Stock;

public interface ProductService {
	/**
	 * ���� ���: product ���̺� ���ڵ� insert
	 * */
	void drinkInsert(Product product) throws SQLException, AddException, DuplicatedException;
	
	/**
	 * ����Ʈ ���: product ���̺�, stock ���̺� ���ڵ� insert
	 * */
	void dessertInsert(Product product) throws SQLException, AddException, DuplicatedException;
	
	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����)
	 * */
   void productUpdate(Product product) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * ����Ʈ ��� ����
	 * : stock ���̺� ���ڵ� update, product ���̺� ���ڵ� update
	 *   ���� stock ���̺� ��� ������ 0 ���Ϸ� �������� ���ϵ��� �ϰ�
	 *   stock ���̺� ��� ������ 0�� �� �ڵ����� ����Ʈ ǰ�� ���ΰ� yes�� �ǵ���...?
	 * */
	void dessertStockUpdate(Stock stock) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * ���� ����: product ���̺� ���ڵ� delete
	 * */
	void productDelete(String prodCode) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * ����Ʈ ��� ����: product ���̺�, stock ���̺� ���ڵ� delete
	 * */
	void stockDelete(String prodCode) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * ī�װ��� ��ǰ ����
	 * : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 * */
	List<Product> selectByGroup(String groupCode) throws SQLException, NotFoundException;
	
	/**
	 * ��ü��ǰ �޴�����(Ŀ��/Ƽ/������/����Ʈ ������ ����)
	 * */
	List<Product> selectAll(String ProdCode) throws SQLException, NotFoundException;

	
}
