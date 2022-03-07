package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dao.OrdersDAO;
import cafe.mvc.model.dao.OrdersDAOImpl;
import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Statistics;

public class OrdersServiceImpl implements OrdersService {

	OrdersDAO ordersDao = new OrdersDAOImpl();	
	
	/**
	 * �ֹ��ϱ�
	 * 1. session�� ����� ��ٱ��� map�� ������ ������ ���ǰ� ���� �ľ�
	 * 2. orders ���̺� insert
	 * 3. ȸ���̸� �������� ����� ��� ������ ����(user ���̺� update)
	 * 4. order_line ���̺� insert
	 * 5. ����Ʈ �ֹ��� ���� ��� stock ����(update)
	 * */
	@Override
	public void orderInsert(Orders orders) throws SQLException, AddException {
		int result = ordersDao.orderInsert(orders);
		if(result == 0) {
			throw new AddException("�ֹ��� �Ϸ���� �ʾҽ��ϴ�.");
		}
	}

	/**
	 * �ֹ� ���� �ڵ� ����
	 * orders�� �ֹ� ���� �ڵ� update
	 * : �ֹ� ��ȣ�� ���� �ڵ带 �޾Ƽ� orders�� ������ �μ��� ����
	 * */
	@Override
	public void orderStateUpdate(Orders orders) throws SQLException, ModifyException, NotFoundException {
		
	}

	/**
	 * ���� ���� ���� �ֹ� �˻�: �Ⱦ� �Ϸ�, �ֹ� ��� ���°� �ƴ� ��� �ֹ� �˻�
	 * : �޼ҵ�� ������Դϴ�... �ٵ� ���̵�� ��Ź�����!
	 * */
	@Override
	public List<Orders> selectOngoingOrder() throws SQLException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ȸ���� ���� �ֹ� ���� ��ȸ
	 * : �α����� ȸ���� ������ ���� ȸ���� ���� �ֹ� ���� �˻�
	 * */
	@Override
	public List<Orders> selectByUserTel(String UserTel) throws SQLException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * �ϰ� ���� ��� ��ȸ
	 * */
	@Override
	public Statistics dailySalesStatistic(String date) throws SQLException, NotFoundException {
		Statistics statistic = ordersDao.dailySalesStatistic(date);
//		if (statistic == null) {
//			throw new NotFoundException("�ϰ� ������ �˻��� �� �����ϴ�.");
//		}
		return statistic;
	}

}
