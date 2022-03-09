package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dao.OrdersDAO;
import cafe.mvc.model.dao.OrdersDAOImpl;
import cafe.mvc.model.dto.OrdersDTO;
import cafe.mvc.model.dto.StatisticsDTO;

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
	public void orderInsert(OrdersDTO ordersDTO) throws SQLException, AddException {
		int result = ordersDao.orderInsert(ordersDTO);
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
	public void orderStateUpdate(OrdersDTO ordersDTO) throws SQLException, ModifyException, NotFoundException {

		int result = ordersDao.orderStateUpdate(ordersDTO);
		if(result==0)throw new SQLException("������ �����Ͽ����ϴϴ�.");

	}

	/**
	 * ���� ���� ���� �ֹ� �˻�: �Ⱦ� �Ϸ�, �ֹ� ��� ���°� �ƴ� ��� �ֹ� �˻�
	 * */
	@Override
	public List<OrdersDTO> selectOngoingOrder() throws SQLException, NotFoundException {
		List<OrdersDTO> list=ordersDao.selectOnoingOrder();
		if(list.isEmpty()) throw new SQLException("���� �������� �ֹ��� �����ϴ�..");
		return list;
	}

	/**
	 * ȸ���� ���� �ֹ� ���� ��ȸ
	 * : �α����� ȸ���� ������ ���� ȸ���� ���� �ֹ� ���� �˻�
	 * */
	@Override
	public List<OrdersDTO> selectByUserTel(String UserTel) throws SQLException, NotFoundException {
		List<OrdersDTO> list=ordersDao.selectByUserTel(UserTel);
		
		if( list.isEmpty() ) throw new SQLException("���� �ֹ� ������ �����ϴ�..");
		return list;
	}

	/**
	 * �ϰ� ���� ��� ��ȸ
	 * */
	@Override

	public Map<String, Integer> dailySalesStatistic(String date) throws SQLException, NotFoundException {
		Map<String, Integer> map = ordersDao.dailySalesStatistic(date);
		if (map == null) {
			throw new NotFoundException("�ϰ� ������ �˻��� �� �����ϴ�.");
		}
		return map;
	}
	
	/**
	 * �޴��� �Ǹ� ���: ������� �ȸ� �޴� 
	 * */
	@Override
	public List<StatisticsDTO> productSalesStatistic() throws SQLException, NotFoundException {
		List<StatisticsDTO> list = ordersDao.productSalesStatistic();
		if (list == null) {
			throw new NotFoundException("�Ǹ� ��踦 �˻��� �� �����ϴ�.");
		}
		return list;
	}

}
