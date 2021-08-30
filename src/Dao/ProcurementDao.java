package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import bean.Procurement;

/**
 * 采购dao
 * @author computer
 *
 */
public class ProcurementDao {
	
	private CostumeDao costumeDao = new CostumeDao();
	
	public int add(Connection conn,Procurement procurement)throws Exception{
		String sql = "insert into t_procurement(adminId,costumeId,supplierId,color,size,qualtity,total,createdTime) values(?,?,?,?,?,?,?,?)";   
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, procurement.getAdminId());
		ps.setInt(2, procurement.getCostumeId());
		ps.setInt(3, procurement.getSupplierId());
		ps.setString(4, procurement.getColor());
		ps.setString(5, procurement.getSize());
		ps.setInt(6, procurement.getQualtity());
		ps.setDouble(7, procurement.getTotal());
		// 日期转换
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
		String ft = dateFormat.format(procurement.getCreatedTime());
		ps.setString(8, ft);
		return ps.executeUpdate();	
	}
	
	public int procurement(Connection conn,Procurement procurement)throws Exception{
		double price = 0;
		ResultSet rs = costumeDao.queryPS(conn, procurement.getCostumeId(), procurement.getColor(), procurement.getSize());
		if (rs.next()) {
			price = rs.getDouble("price")*0.7;
		}else {
			return 0;
		}
		double total = price*procurement.getQualtity();
		int count1 = costumeDao.increaseStorage(conn, procurement.getCostumeId(), procurement.getQualtity(),procurement.getColor(),procurement.getSize());
		if (count1 == 0) {
			return 0;
		}
		Procurement newProcurement = new Procurement();
		newProcurement.setAdminId(procurement.getAdminId());
		newProcurement.setCostumeId(procurement.getCostumeId());
		newProcurement.setSupplierId(procurement.getSupplierId());
		newProcurement.setColor(procurement.getColor());
		newProcurement.setSize(procurement.getSize());
		newProcurement.setQualtity(procurement.getQualtity());
		newProcurement.setTotal(total);
		newProcurement.setCreatedTime(new Date());
		
		int count2 = add(conn, newProcurement);
		if (count2 == 0) {
			return 0;
		}
		return 1;
	}
	
	public ResultSet query(Connection conn,Procurement procurement,String[] sdate,String[] edate,String s_order)throws Exception{
		StringBuffer sb = new StringBuffer("select * from t_procurement");
		if (procurement.getProcurementId()!=0) {
			sb.append(" and procurementId="+procurement.getProcurementId());
		}
		if (procurement.getAdminId()!=0) {
			sb.append(" and adminId="+procurement.getAdminId());
		}
		if (procurement.getCostumeId()!=0) {
			sb.append(" and costumeId="+procurement.getCostumeId());
		}
		// 起始日期不为空
		if (sdate!=null&&!sdate[0].isEmpty()) {
			sb.append(" and createdTime>='"+sdate[0]+"-"+sdate[1]+"-"+sdate[2]+"'");
		}
		// 截至日期不为空
		if (edate!=null&&!edate[0].isEmpty()) {
			sb.append(" and createdTime<='"+edate[0]+"-"+edate[1]+"-"+edate[2]+"'");
		}

		if (s_order!=null) {
			sb.append(" order by total "+s_order); //s_order 为DESC 或 ASC
		}

		PreparedStatement ps = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return ps.executeQuery();
	}
	
	/**
	 * 计算指定日期范围内的采购总金额总和
	 * @param conn
	 * @param sdate
	 * @param edate
	 * @return
	 * @throws Exception
	 */
	public double calculateSum(Connection conn,String sdate,String edate)throws Exception{
		StringBuffer sb = new StringBuffer("select SUM(total) from t_procurement");
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

	public int update(Connection conn,Procurement procurement)throws Exception{
		String  sql = "update t_procurement set color=?,size=?,qualtity=?,total=? where procurementId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, procurement.getColor());
		ps.setString(2, procurement.getSize());
		ps.setInt(3, procurement.getQualtity());
		ps.setDouble(4, procurement.getTotal());
		ps.setInt(5, procurement.getProcurementId());
		return ps.executeUpdate();
	}
	
	public int delete(Connection conn,int procurementId)throws Exception{
		String sql = "delete from t_procurement where procurementId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, procurementId);
		return ps.executeUpdate();
	}

}