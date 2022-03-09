package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.model.dto.StockDTO;

public interface ProductService {
	/**
	 * ��ǰ ���: product ���̺� ���ڵ� insert
	 */
	void productInsert(ProductDTO product) throws SQLException, AddException, DuplicatedException;

	/**
	 * ��ǰ ����: product ���̺� ���ڵ� update(�Ǹ� ����, �� ����, ǰ�� ����)
	 */
	void productUpdate(ProductDTO productDTO) throws SQLException, ModifyException, NotFoundException;

	/**
	 * ����Ʈ ��� ���� : stock ���̺� ���ڵ� update, product ���̺� ���ڵ� update ���� stock ���̺� ��� ������
	 * 0 ���Ϸ� �������� ���ϵ��� �ϰ� stock ���̺� ��� ������ 0�� �� �ڵ����� ����Ʈ ǰ�� ���ΰ� yes�� �ǵ���...?
	 */
	void dessertStockUpdate(StockDTO stockDTO) throws SQLException, ModifyException, NotFoundException;

	
	/**
	 * ��ǰ���º���
	 */
	void productStateUpdate(String prodCode, int prodState) throws SQLException, ModifyException, NotFoundException;

	/**
	 * ī�װ��� ��ǰ ���� : ��ǰ�з��ڵ带 ���� �� ī�װ��� �´� ��ǰ�� ��ȸ
	 */

	List<ProductDTO> productSelectByGroup(String groupCode) throws SQLException, NotFoundException;

	/**
	 * ��ü��ǰ �޴�����(Ŀ��/Ƽ/������/����Ʈ ������ ����)
	 */
	List<ProductDTO> productSelectAll() throws SQLException, NotFoundException;


	/**
	 * �˻��� ��ǰ �޴�����
	 */
	ProductDTO productSelectByProdCode(String prodCode) throws SQLException, NotFoundException;


}
