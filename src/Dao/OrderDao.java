package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import bean.Member;
import bean.Order;
/**
 * ����Dao��
 * @author computer
 *
 */
public class OrderDao {
	
	private CostumeDao costumeDao = new CostumeDao();
	private MemberDao memberDao = new MemberDao();
	
	public int add(Connection conn,Order order)throws Exception{
		String sql = "insert into t_order(memberId,costumeId,color,size,payMethod,qualtity,total,createdTime) values(?,?,?,?,?,?,?,?)";   
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, order.getMemberId());
		ps.setInt(2, order.getCostumeId());
		ps.setString(3, order.getColor());
		ps.setString(4, order.getSize());
		ps.setString(5, order.getPayMethod());
		ps.setInt(6, order.getQualtity());
		ps.setDouble(7, order.getTotal());
		// ����ת��
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
		String ft = dateFormat.format(order.getCreatedTime());
		ps.setString(8, ft);
		return ps.executeUpdate();	
	}
	
	public int purchase(Connection conn,Order order)throws Exception{
		// 1.��õ��ۣ������
		double price = 0;
		int storage = 0;
		ResultSet rs = costumeDao.queryPS(conn, order.getCostumeId(), order.getColor(), order.getSize());
		if (rs.next()) {
			price = rs.getDouble("price");
			storage = rs.getInt("storage");
		}else {
			return 0;
		}
		// 2.�����ܽ��
		double total;
		if (order.getPayMethod().equals("����ȯ(8��)")) {
			total = price*order.getQualtity()*0.8;
		}else {
			total = price*order.getQualtity();
		}
		// 3.��û�Ա���
		Member member = memberDao.querySelf(conn, order.getMemberId());
		double balance = member.getBalance();
		// 4.�жϿ��ͻ�Ա����Ƿ��㹻
		if (balance < total) {
			return 2;
		}
		if (storage < order.getQualtity()) {
			return 3;
		}
		// ִ�е��˴���ʾ֧�������Ѿ��ﵽҪ�󣬽�������ʼ����������
		// 5.���������ܶ��Ա���
		int success = memberDao.updateTotalConsumption(conn, order.getMemberId(), total);
		if (success == 0) {
			return 0;
		}
		int success2 = memberDao.reduceBalance(conn, order.getMemberId(), total);
		if (success2 == 0) {
			return 0;
		}
		// 6.���ٷ�װ��Ӧ�Ŀ����
		// 6.27 ����bug���������Ӧ��װ���г�����ɫ�Ŀ�棬���޸�
		int count1 = costumeDao.reduceStorage(conn, order.getCostumeId(), order.getQualtity(),order.getColor(),order.getSize());
		if (count1 == 0) {
			return 0;
		}
		// 7.���ɶ���(0���쳣��1���ɹ���2�����㣬3����治��)
		Order newOrder = new Order();
		newOrder.setMemberId(order.getMemberId());
		newOrder.setCostumeId(order.getCostumeId());
		newOrder.setColor(order.getColor());
		newOrder.setSize(order.getSize());
		newOrder.setPayMethod(order.getPayMethod());
		newOrder.setQualtity(order.getQualtity());
		newOrder.setTotal(total);
		newOrder.setCreatedTime(new Date());
		
		int count2 = add(conn, newOrder);
		if (count2 == 0) {
			return 0;
		}
		// ȫ���������ϣ����ɶ���ȫ�����
		return 1;
	}
	
	public int queryOrderAmount(Connection conn,int memberId)throws Exception{
		String sql = "select COUNT(*) from t_order where memberId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, memberId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}else {
			return 0;
		}
	}
	
	public ResultSet query(Connection conn,Order order,String[] sdate,String[] edate,String s_order)throws Exception{
		StringBuffer sb = new StringBuffer("select * from t_order");
		// ����������ѯ����
		if (order.getOrderId()!=0) {
			sb.append(" and orderId="+order.getOrderId());
		}
		if (order.getMemberId()!=0) {
			sb.append(" and memberId="+order.getMemberId());
		}
		if (order.getCostumeId()!=0) {
			sb.append(" and costumeId="+order.getCostumeId());
		}
		// ��ʼ���ڲ�Ϊ��
		if (sdate!=null&&!sdate[0].isEmpty()) {
			sb.append(" and createdTime>='"+sdate[0]+"-"+sdate[1]+"-"+sdate[2]+"'");
		}
		// �������ڲ�Ϊ��
		if (edate!=null&&!edate[0].isEmpty()) {
			sb.append(" and createdTime<='"+edate[0]+"-"+edate[1]+"-"+edate[2]+"'");
		}

		if (s_order!=null) {
			sb.append(" order by total "+s_order); //s_order ΪDESC �� ASC
		}

		PreparedStatement ps = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return ps.executeQuery();
	}
	
	public int delete(Connection conn,int orderId)throws Exception{
		String sql = "delete from t_order where orderId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, orderId);
		return ps.executeUpdate();
	}
	
	public int update(Connection conn,Order order)throws Exception{
		String  sql = "update t_order set color=?,size=?,payMethod=?,qualtity=?,total=? where orderId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, order.getColor());
		ps.setString(2, order.getSize());
		ps.setString(3, order.getPayMethod());
		ps.setInt(4, order.getQualtity());
		ps.setDouble(5, order.getTotal());
		ps.setInt(6, order.getOrderId());
		return ps.executeUpdate();
	}
	
	/**
	 * ����ָ�����ڶ�������ܺ�
	 * @param conn
	 * @param sdate
	 * @param edate
	 * @return  ������
	 * @throws Exception
	 */
	public double calculateSum(Connection conn,String sdate,String edate)throws Exception{
		StringBuffer sb = new StringBuffer("select SUM(total) from t_order");
		if (sdate!=null) {
			sb.append(" and createdTime>= '"+sdate+"'");
		}
		if (edate!=null) {
			sb.append(" and createdTime<= '"+edate+"'");
		}
		
		PreparedStatement ps = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return rs.getDouble(1);
		}else {
			return 0;
		}
	}
	
}