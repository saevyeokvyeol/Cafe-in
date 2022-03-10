package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.model.dto.StockDTO;

public interface ProductDAO {
	/**
	 * ��ǰ ���: product ���̺� ���ڵ� insert
	 */
	int productInsert(ProductDTO product) throws SQLException;
	
	/**
	 * ����Ʈ ��� ���
	 * */
	int dessertStockInsert (StockDTO stock) throws SQLException;

	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����)
	 */
	int productUpdate(ProductDTO productDTO) throws SQLException;

	/**
	 * ����Ʈ ��� ���� : stock ���̺� ���ڵ� update, product ���̺� ���ڵ� update ���� stock ���̺� ��� ������
	 * 0 ���Ϸ� �������� ���ϵ��� �ϰ� stock ���̺� ��� ������ 0�� �� �ڵ����� ����Ʈ ǰ�� ���ΰ� yes�� �ǵ���...?
	 */
	int dessertStockUpdate(StockDTO stockDTO) throws SQLException;


	/**
	 * ��ǰ���º���
	 */
	int productStateUpdate(String prodCode, int prodState) throws SQLException;

	/**
	 * ��ü ��ǰ ���� : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 */
	List<ProductDTO> productSelectAll() throws SQLException;


	/**
	 * ī�װ��� ��ǰ ���� : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 */
	List<ProductDTO> productSelectByGroup(String groupCode) throws SQLException;

	/**
	 * ��ǰ �ڵ�� ��ǰ �˻�
	 */
	ProductDTO productSelectByProdCode(String prodCode) throws SQLException;

}
