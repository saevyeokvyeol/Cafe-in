package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Statistics;

public interface OrdersService {
	/**
	 * �ֹ��ϱ�
	 * 1. session�� ����� ��ٱ��� map�� ������ ������ ���ǰ� ���� �ľ�
	 * 2. orders ���̺� insert
	 * 3. ȸ���̸� �������� ����� ��� ������ ����(user ���̺� update)
	 * 4. order_line ���̺� insert
	 * 5. ����Ʈ �ֹ��� ���� ��� stock ����(update)
	 * */
	void orderInsert(Orders orders) throws SQLException, AddException, ModifyException, NotFoundException;
	
	/**
	 * �ֹ� ���� �ڵ� ����
	 * orders�� �ֹ� ���� �ڵ� update
	 * : �ֹ� ��ȣ�� ���� �ڵ带 �޾Ƽ� orders�� ������ �μ��� ����
	 * */
	void orderStateUpdate(Orders orders) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * ���� ���� ���� �ֹ� �˻�: �Ⱦ� �Ϸ�, �ֹ� ��� ���°� �ƴ� ��� �ֹ� �˻�
	 * : �޼ҵ�� ������Դϴ�... �ٵ� ���̵�� ��Ź�����!
	 * */
	List<Orders> selectOngoingOrder() throws SQLException, NotFoundException;
	
	/**
	 * ȸ���� ���� �ֹ� ���� ��ȸ
	 * : �α����� ȸ���� ������ ���� ȸ���� ���� �ֹ� ���� �˻�
	 * */
	List<Orders> selectByUserTel(String UserTel) throws SQLException, NotFoundException;
	
	/**
	 * �ϰ� ���� ��� ��ȸ
	 * */
	Map<String, Integer> dailySalesStatistic(String date) throws SQLException, NotFoundException;
	
	/**
	 * ��ǰ�� �Ǹ� ���
	 * */
	List<Statistics> productSalesStatistic() throws SQLException, NotFoundException;
	
	/**
	 * �� �ʿ��� �޼ҵ� �������?
	 * */
}
