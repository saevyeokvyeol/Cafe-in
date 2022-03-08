package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
	 * */
	
	@Override
	public int orderInsert(Orders orders) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.orderInsert");
		// insert into orders values(orders_seq.nextval, ?, 0, ?, ?, ?, sysdate, ?);
		// ��ȭ��ȣ, ���� ���, ������ ��� �׼�, �� ���� �ݾ�, ����ũ�ƿ� ����
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); // �ڵ� Ŀ�� ����
			
			ps = con.prepareStatement(sql);
			ps.setString(1, orders.getUserTel());
			ps.setString(2, orders.getPayMethod());
			ps.setInt(3, orders.getPayPoint());
			ps.setInt(4, this.getToTalPrice(orders));
			ps.setInt(5, orders.getTakeOut());
			
			result = ps.executeUpdate();
			
			if(result == 0) { // ���� ������ ���� ��ϵ��� �ʾҴٸ�
				con.rollback();
				throw new SQLException("�ֹ��� �Ϸ���� �ʾҽ��ϴ�.");
			} else {
				int re[] = orderLineInsert(con, orders); // orderLineInsert�� ���� �ֹ� �� insert
				for(int i : re) {
					System.out.print(i + " | ");
					if(i != 1) { // ��� array���� 1���� ��ϵ� �������� ����� �ƴ϶��
						con.rollback();
						throw new SQLException("�ֹ��� �Ϸ���� �ʾҽ��ϴ�.");
					}
				}
			}
			
			decrementStockUpdate(con, orders.getOrdelLineList());
			
			con.commit(); // ��� ���� ���������� �Ϸ�Ǹ� Ŀ��
		} finally {
			con.commit(); // �߰��� ����ٸ� rollback�� ���·� Ŀ��
			DbUtil.close(con, ps);
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
			for(OrderLine orderLine : orders.getOrdelLineList()) {
				Product product = productDao.selectByProdCode(orderLine.getProdCode()); // �ڵ�� ��ǰ ���� ������� 
				ps.setInt(1, orders.getOrderNum());
				ps.setString(2, product.getProdCode());
				ps.setInt(3, orderLine.getQty());
				ps.setInt(4, (product.getProdPrice() * orderLine.getQty()));
				
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
	public int[] decrementStockUpdate(Connection con, List<OrderLine> orderLineList) throws SQLException {
		PreparedStatement ps = null;
		String sql = proFile.getProperty("order.decremStockUpdate");
		int[] result = null;
		
		try {
			ps = con.prepareStatement(sql);
			for(OrderLine orderLine : orderLineList) {
				Product product = productDao.selectByProdCode(orderLine.getProdCode()); // �ڵ�� ��ǰ ���� ������� 
				ps.setInt(1, orderLine.getQty());
				ps.setString(2, orderLine.getProdCode());
				
				ps.addBatch(); // ��ġ�� �߰�
				ps.clearParameters(); // �Ķ���� �����
			}
			
			result = ps.executeBatch();
		} finally {
			// TODO: handle finally clause
		}
		
		return result;
	}
	
	/**
	 * ��ǰ �� ���� �ݾ� ���ϱ�
	 * */
	public int getToTalPrice(Orders orders) throws SQLException{
		List<OrderLine> orderLineList = orders.getOrdelLineList();
		int total = 0;
		
		for(OrderLine orderline : orderLineList) {
			Product product = productDao.selectByProdCode(orderline.getProdCode());
			if(product == null) {
				throw new SQLException("��ǰ ��ȣ�� ������ �߻��� ������ �����߽��ϴ�.");
			} else if(product.getStock().getProdStock() < orderline.getQty()) {
				throw new SQLException("����� ������ ������ �����߽��ϴ�.");
			}
			
			total += (orderline.getQty() * product.getProdPrice());
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
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(sql);
			System.out.println("������ �����ڵ�� ? \n 1.������� | 2.�ֹ� ���� |  3.��ǰ �غ��� | 4. ��ǰ �غ� �Ϸ� | 5. �Ⱦ� �Ϸ� | 6. �ֹ� ���");
			int stateCode = orders.getStateCode();
			System.out.println("������ �ֹ���ȣ�� ?");
			int orderNum = orders.getOrderNum();
			ps.setInt(1, stateCode);
			ps.setInt(2, orderNum);
			
			result = ps.executeUpdate();
			if(result==0) {
				con.rollback();
				throw new SQLException("�ֹ������ڵ庯�� ����..");
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
		//Orders orders = null;
		List<Orders> orderList = new ArrayList<>();
		String sql = "select*from orders join order_line using(order_num) where user_tel =?";
		//int�ֹ������ڵ�, String�̸�, String��ǰ��,int ����, int�ǸŰ���, int����*�ֹ����� 
		try {
			con = DbUtil.getConnection();
			ps= con.prepareStatement(sql);
			ps.setString(1, UserTel);
			rs= ps.executeQuery();
			//selectByUserTelOrderLine(con,orders.getOrderNum());
			
			while(rs.next()) {
				// int orderNum, String userTel, int stateCode, String payMethod, int payPoint, int totalPrice, String orderDate, int takeOut
				Orders orders = new Orders( rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8) );
				//orderList.add(orders);
				/////////////////
				List<OrderLine> orderLineList = selectByUserTelOrderLine(con,orders.getOrderNum());
				orders.setOrdelLineList(orderLineList);
				
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
				Orders orders = new Orders( rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8) );
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
	public Statistics dailySalesStatistic(String date) throws SQLException {
		// con, ps, rs, ���ϰ� ����, sql�� �ܾ����
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Statistics statistics = null;
		String sql = proFile.getProperty("statistic.dailyStatistic");
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			
			rs = ps.executeQuery();
			
			int dailyOrderTimes = 0;
			int dailySalesPrice = 0;
			int dailySalesQty = 0;
			int orderNum = 0;
			while(rs.next()) {
				
				if(orderNum != rs.getInt(1)) { // �ֹ� ��ȣ�� �ٸ��� �� �ֹ� Ƚ��++
					dailyOrderTimes++;
					orderNum = rs.getInt(1);
				}
				
				dailySalesPrice += rs.getInt(5);
				dailySalesQty += rs.getInt(4);
			}
			
			statistics = new Statistics(date, dailyOrderTimes, dailySalesPrice, dailySalesQty); 
		} finally {
			DbUtil.close(con, ps, rs);
		}
		
		
		return statistics;
	}

}
