package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.OrderLine;
import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Product;
import cafe.mvc.model.dto.Statistics;
import cafe.mvc.util.DbUtil;

public class OrdersDAOImpl implements OrdersDAO {
	// DbUtil���� proFile ��������
	private Properties proFile = DbUtil.getProFile();
	ProductDAO productDao = new ProductDAOImpl();

	/**
	 * �ֹ��ϱ�
	 * 1. session�� ����� ��ٱ��� map�� ������ ������ ���ǰ� ���� �ľ�
	 * 2. orders ���̺� insert
	 * 3. ȸ���̸� �������� ����� ��� ������ ����(user ���̺� update)
	 * 4. order_line ���̺� insert
	 * 5. ����Ʈ �ֹ��� ���� ��� stock ����(update)
	 * 
	 * �ڵ尡 �ʹ� ������� �޼ҵ带 ������ ������
	 * 
	 * @ �ڵ� Ŀ�� ������ ��!!
	 * 
	 * ���� ��� ���ڶ� �� ��� �� �� ���Ҵٰ� �˷��ֱ�
	 * */
	@Override
	public int orderInsert(Orders orders) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.orderInsert");
		// insert into orders values(orders_seq.nextval, ?, 0, ?, ?, ?, sysdate, ?);
		// 1. ��ȭ��ȣ, 2. ���� ���, 3. ������ ��� �׼�, 4. �� ���� �ݾ�, 5. ����ũ�ƿ� ����
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); // �ڵ� Ŀ�� ����
			
			String userTel = orders.getUserTel();
			String payMethod = orders.getPayMethod();
			int payPoint = orders.getPayPoint();
			int totalPrice = this.getToTalPrice(orders);
			
			ps = con.prepareStatement(sql);
			
			if(!userTel.equals("guest")) {
				ps.setString(1, userTel);
			} else {
				ps.setString(1, null);
			}
			
			if(payMethod.equals("ī��")) {
				ps.setString(2, "card");
			} else if(payMethod.equals("����")) {
				ps.setString(2, "cash");
			} else {
				throw new SQLException("���� ����� �߸��Ǿ� �ֹ��� �Ϸ���� �ʾҽ��ϴ�.");
			}
			ps.setInt(3, payPoint);
			ps.setInt(4, totalPrice);
			ps.setInt(5, orders.getTakeOut());
			ps.setInt(6, orders.getTakeOut());
			
			result = ps.executeUpdate();
			
			
			
			if(result == 0) { // ���� ������ ���� ��ϵ��� �ʾҴٸ�
				con.rollback();
				throw new SQLException("�ֹ��� �Ϸ���� �ʾҽ��ϴ�.");
			}
			
			// ������ ��� �� ������ ����
			if(!userTel.equals("guest")) {
				if(payPoint > 0) {
					int dePointResult = dereaseUserPoint(con, payPoint, userTel);
					if(dePointResult != 1) {
						throw new SQLException("������ ��� ������ �߻��Ͽ� �ֹ��� �Ϸ���� �ʾҽ��ϴ�.");
					}
				} else if(payPoint < 0) {
					throw new SQLException("������ ��� �׼��� �߸��Ǿ� �ֹ��� �Ϸ���� �ʾҽ��ϴ�.");
				}
			} else {
				if(payPoint != 0) {
					throw new SQLException("��ȸ���� �������� ����� �� �����ϴ�.");
				}
			}
			
			// ������ �߰�
			if(!userTel.equals("guest")) {
				int inPointResult = increaseUserPoint(con, totalPrice, userTel);
				if(inPointResult != 1) {
					throw new SQLException("������ ���� ������ �߻��Ͽ� �ֹ��� �Ϸ���� �ʾҽ��ϴ�.");
				}
				
			}
			
			 // orderLineInsert�� ���� �ֹ� �� insert 
			int lineResult[] = orderLineInsert(con, orders);
			for(int i : lineResult) {
				if(i != 1) { // ��� array���� 1���� ��ϵ� �������� ����� �ƴ϶��
					con.rollback();
					throw new SQLException("�ֹ��� �Ϸ���� �ʾҽ��ϴ�.");
				}
			}
			
			for(OrderLine orderLine : orders.getOrderLineList()) {
				if(orderLine.getProdCode().substring(0, 1).equals("D")) {
					int stockResult = decreaseStockUpdate(con, orderLine);
					if(stockResult == 0) {
						throw new SQLException("�ֹ��� �Ϸ���� �ʾҽ��ϴ�.");
					}
				}
			}
			
			con.commit(); // ��� ���� ���������� �Ϸ�Ǹ� Ŀ��
		} finally {
			con.commit(); // �߰��� ����ٸ� rollback�� ���·� Ŀ��
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	/**
	 * ������ ����� ��� ������ ����
	 * */
	public int dereaseUserPoint(Connection con, int payPoint, String userTel) throws SQLException{
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.dereaseUserPoint");
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, payPoint);
			ps.setString(2, userTel);
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps);
		}
		
		return result;
	}
	
	/**
	 * �� �ֹ� �ݾ��� 10% ����
	 * @throws SQLException 
	 * */
	public int increaseUserPoint(Connection con, int totalPrice, String userTel) throws SQLException {
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.increaseUserPoint");
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, (totalPrice / 10));
			ps.setString(2, userTel);
			
			result = ps.executeUpdate();
			
		} finally {
			DbUtil.close(null, ps);
		}
		
		return result;
	}
	
	/**
	 * �ֹ� �� ����ϱ�
	 * */
	public int[] orderLineInsert(Connection con, Orders orders) throws SQLException{
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.orderLineInsert");
		// insert into order_line values(orderline_seq.nextval, ?, ?, ?, ?)
		// ���� �ѹ�, ��ǰ�ڵ�, ����, ����*����
		int[] result = null;
		
		try {
			ps = con.prepareStatement(sql);
			for(OrderLine orderLine : orders.getOrderLineList()) {
				Product product = productDao.productSelectByProdCode(orderLine.getProdCode()); // �ڵ�� ��ǰ ���� ������� 
				ps.setString(1, product.getProdCode());
				ps.setInt(2, orderLine.getQty());
				ps.setInt(3, (product.getProdPrice() * orderLine.getQty()));
				
				ps.addBatch(); // ��ġ�� �߰�
				ps.clearParameters(); // �Ķ���� �����
			}
			
			result = ps.executeBatch(); // �ϰ�ó��
		} finally {
			DbUtil.close(null, ps);
		}
		return result;
	}
	
	/**
	 * ����Ʈ�� ��� ��� ����
	 * */
	public int decreaseStockUpdate(Connection con, OrderLine orderLine) throws SQLException {
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.decrementStockUpdate");
		int result = 0;
		
		try {
			Product product = productDao.productSelectByProdCode(orderLine.getProdCode());
			if(product.getProdCode().substring(0, 1).equals("D") && product.getStock().getProdStock() < orderLine.getQty()) {
				throw new SQLException("����� ������ ������ �����߽��ϴ�.");
			}
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderLine.getQty());
			ps.setString(2, orderLine.getProdCode());
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps);
		}
		
		return result;
	}
	
	/**
	 * ��ǰ �� ���� �ݾ� ���ϱ�
	 * */
	public int getToTalPrice(Orders orders) throws SQLException{
		List<OrderLine> orderLineList = orders.getOrderLineList();
		int total = 0;
		
		for(OrderLine orderline : orderLineList) {
			Product product = productDao.productSelectByProdCode(orderline.getProdCode());
			if(product == null) {
				throw new SQLException("��ǰ ��ȣ�� ������ �߻��� ������ �����߽��ϴ�.");
			}
			
			total = (orderline.getQty() * product.getProdPrice());
		}
		
		return total;
	}

	/**
	 * �ֹ� ���� �ڵ� ����
	 * orders�� �ֹ� ���� �ڵ� update
	 * : �ֹ� ��ȣ�� ���� �ڵ带 �޾Ƽ� orders�� ������ �μ��� ����
	 * */
	@Override
	public int orderStateUpdate(Orders orders) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update orders set state_code =? where order_num=?";
		int result=0;
	
		try {
			con = DbUtil.getConnection();
			
			ps=con.prepareStatement(sql);
			int stateCode = orders.getStateCode();
			int orderNum = orders.getOrderNum();
			ps.setInt(1, stateCode);
			ps.setInt(2, orderNum);
			
			result = ps.executeUpdate();
			if(result==0) {
				con.rollback();
				throw new SQLException("���� �ڵ� ���濡 �����߽��ϴ�.");
			}
			
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * ȸ���� ���� �ֹ� ���� ��ȸ
	 * : �α����� ȸ���� ������ ���� ȸ���� ���� �ֹ� ���� �˻�
	 *   �ֹ� �� �޼ҵ带 ���� ����� �� ������ �Բ� �����ֱ�
	 * */
	@Override
	public List<Orders> selectByUserTel(String UserTel)  throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Orders> orderList = new ArrayList<>();
		String sql = "select*from orders join order_line using(order_num) where user_tel =?";
		//int�ֹ������ڵ�, String�̸�, String��ǰ��,int ����, int�ǸŰ���, int����*�ֹ����� 
		try {
			con = DbUtil.getConnection();
			ps= con.prepareStatement(sql);
			ps.setString(1, UserTel);
			rs = ps.executeQuery();
			//selectByUserTelOrderLine(con,orders.getOrderNum());
			
			while(rs.next()) {
				Orders orders = new Orders( rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8));

				List<OrderLine> orderLineList = selectByUserTelOrderLine(con, orders.getOrderNum());
				orders.setOrderLineList(orderLineList);
				
				orderList.add(orders);
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		
		return orderList;
	}
	
	/**
	 * �ֹ���
	 * */
	public List<OrderLine> selectByUserTelOrderLine(Connection con, int orderNum)throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderLine> list = new ArrayList<OrderLine>();
		
		String sql = "select*from order_line where order_num=?";
		

		try {
			con=DbUtil.getConnection();
			ps= con.prepareStatement(sql);
			ps.setInt(1, orderNum);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				OrderLine orderLine = new OrderLine(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				list.add(orderLine);
			}
		} finally {
			DbUtil.close(null, ps, rs);
		}
		
		return list;
	}

	/**
	 * ���� ���� ���� �ֹ� �˻�: �Ⱦ� �Ϸ�, �ֹ� ��� ���°� �ƴ� ��� �ֹ� �˻�
	 * : �޼ҵ�� ������Դϴ�... �ٵ� ���̵�� ��Ź�����!
	 * */
	@Override
	public List<Orders> selectOnoingOrder() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = new ArrayList<>();
		
		String sql = "select*from orders where state_code not in(4,5)";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Orders orders = new Orders( rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8));

				List<OrderLine> orderLineList = selectByUserTelOrderLine(con, orders.getOrderNum());
				orders.setOrderLineList(orderLineList);
				
				list.add(orders);
			}
			
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	/**
	 * �ϰ� ���� ���
	 * : ��踦 ��� ����ñ��...(���� DTO�� ���� ���� ��������...?)
	 * */
	@Override
	public Map<String, Integer> dailySalesStatistic(String date) throws SQLException {
		// con, ps, rs, ���ϰ� ����, sql�� �ܾ����
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Map<String, Integer> statisticMap = new HashMap<String, Integer>();
		String sql = proFile.getProperty("statistic.dailyStatistic");
		// select order_num, prod_code, qty, price_qty from orders join order_line using(order_num) where to_char(order_date, 'yymmdd') = ? order by order_num
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			
			rs = ps.executeQuery();
			
			int orderTimes = 0;
			int salesPrice = 0;
			int dessertSalesQty = 0; // ����Ʈ
			int drinkSalesQty = 0; // ����
			int orderNum = 0;
			while(rs.next()) {
				
				if(orderNum != rs.getInt(1)) { // �ֹ� ��ȣ�� �ٸ��� �� �ֹ� Ƚ��++
					orderTimes++;
					orderNum = rs.getInt(1);
				}
				
				salesPrice += rs.getInt(4);
				if(rs.getString(2).substring(0, 1).equals("D")) {
					dessertSalesQty += rs.getInt(3);
				} else {
					drinkSalesQty += rs.getInt(3);
				}
			}

			statisticMap.put("date", Integer.parseInt(date));
			statisticMap.put("orderTimes", orderTimes);
			statisticMap.put("salesPrice", salesPrice);
			statisticMap.put("dessertSalesQty", dessertSalesQty);
			statisticMap.put("drinkSalesQty", drinkSalesQty);
			
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		return statisticMap;
	}
	
	/**
	 * �޴��� �Ǹ� ���: ������� �ȸ� �޴� 
	 * */
	@Override
	public List<Statistics> productSalesStatistic() throws SQLException, NotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Statistics> list = new ArrayList<Statistics>();
		String sql = proFile.getProperty("order.productSalesStatistic");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Statistics statistics = new Statistics(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), (rs.getInt(3)*rs.getInt(4)));
				list.add(statistics);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

}
