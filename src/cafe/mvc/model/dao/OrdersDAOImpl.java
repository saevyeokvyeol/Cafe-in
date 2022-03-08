package cafe.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import cafe.mvc.model.dto.Orders;
import cafe.mvc.model.dto.Statistics;
import cafe.mvc.util.DbUtil;

public class OrdersDAOImpl implements OrdersDAO {

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
	
		return 0;
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
		Scanner sc = new Scanner(System.in);
		
		try {
			
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(sql);
			System.out.println("������ �����ڵ�� ? \n 1.������� | 2.�ֹ� ���� |  3.��ǰ �غ��� | 4. ��ǰ �غ� �Ϸ� | 5. �Ⱦ� �Ϸ� | 6. �ֹ� ���");
			int a = sc.nextInt();
			System.out.println("������ �ֹ���ȣ�� ?");
			int b = sc.nextInt();
			ps.setInt(1, a);
			ps.setInt(2, b);
			
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
	 * ���� ���� ���� �ֹ� �˻�: �Ⱦ� �Ϸ�, �ֹ� ��� ���°� �ƴ� ��� �ֹ� �˻�
	 * : �޼ҵ�� ������Դϴ�... �ٵ� ���̵�� ��Ź�����!
	 * */
	@Override
	public List<Orders> selectOnoingOrder() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> orderList = null;
		String sql = "";
		
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			ps= con.prepareStatement(sql);
			
			orderList = (List<Orders>) ps.executeQuery();
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		
		return orderList;
	}

	/**
	 * ȸ���� ���� �ֹ� ���� ��ȸ
	 * : �α����� ȸ���� ������ ���� ȸ���� ���� �ֹ� ���� �˻�
	 *   �ֹ� �� �޼ҵ带 ���� ����� �� ������ �Բ� �����ֱ�
	 * */
	@Override
	public List<Orders> selectByUserTel(String UserTel) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = null;
		String sql = "select u.user_tel,u.user_name, ol.qty, p.prod_name, p.prod_price,ol.price_qty from users u join orders o on u.user_tel = o.user_tel join order_line ol using(order_num)join product p on ol.prod_code = p.prod_code where u.user_tel=?;";
		Orders orders= null;

		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, UserTel);
			rs = ps.executeQuery();
			//List = new ArrayList<Orders>();
			//��ȭ��ȣ,�̸�,�ֹ�����,��ǰ��,�ǸŰ���,����*�ֹ�����
			while(rs.next()) {
				orders = new Orders( rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getInt(6) );
				list.add(orders);
			}
		
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		
		return list;
	}


	/**
	 * �ϰ� ���� ���
	 * : ��踦 ��� ����ñ��...(���� DTO�� ���� ���� ��������...?)
	 * */
	@Override
	public Statistics dailySalesStatistic() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
