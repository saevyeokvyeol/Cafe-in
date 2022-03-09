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
	 * ��ǰ ���: product ���̺� ���ڵ� insert
	 */
	void productInsert(Product product) throws SQLException, AddException, DuplicatedException;

	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����)
	 */
	void productUpdate(Product product) throws SQLException, ModifyException, NotFoundException;

	/**
	 * ����Ʈ ��� ���� : stock ���̺� ���ڵ� update, product ���̺� ���ڵ� update ���� stock ���̺� ��� ������
	 * 0 ���Ϸ� �������� ���ϵ��� �ϰ� stock ���̺� ��� ������ 0�� �� �ڵ����� ����Ʈ ǰ�� ���ΰ� yes�� �ǵ���...?
	 */
	void dessertStockUpdate(Stock stock) throws SQLException, ModifyException, NotFoundException;

	
	/**
	 * ��ǰ���º���
	 */
	void productStateUpdate(String prodCode, int prodState) throws SQLException, ModifyException, NotFoundException;

	/**
	 * ī�װ��� ��ǰ ���� : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 */
	List<Product> productSelectByGroup(String groupCode) throws SQLException, NotFoundException;

	/**
	 * ��ü��ǰ �޴�����(Ŀ��/Ƽ/������/����Ʈ ������ ����)
	 */

	List<Product> productSelectAll() throws SQLException, NotFoundException;

	/**
	 * �˻��� ��ǰ �޴�����
	 */
	Product productSelectByProdCode(String prodCode) throws SQLException, NotFoundException;

}
