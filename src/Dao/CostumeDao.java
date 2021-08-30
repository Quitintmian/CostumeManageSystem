package Dao;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Costume;
import bean.CostumeInfo;
import util.FormatUtil;

/**
 * 服装Dao
 * @author computer
 *
 */
public class CostumeDao {
	
	/**
	 * 添加服装
	 * @param conn
	 * @param costume
	 * @return
	 * @throws Exception
	 */
	public int add(Connection conn,Costume costume) throws Exception{
		String sql = "insert into t_costume(costumeName,brand,source,costumeType,price,describe) values(?,?,?,?,?,?)";					
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, costume.getCostumeName());
		ps.setString(2, costume.getBrand());
		ps.setString(3, costume.getSource());
		ps.setString(4, costume.getCostumeType());
		ps.setDouble(5, costume.getPrice());
		ps.setString(6, costume.getDescribe());
		return ps.executeUpdate();
	}
	
	public int addInfos(Connection conn,CostumeInfo[] CostumeInfos)throws Exception {
		int count = 0;
		String sql = "insert into t_costumeInfo(costumeId,color,size,storage) values(?,?,?,0)";
		for (int i = 0;i < CostumeInfos.length;i++) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, CostumeInfos[i].getCostumeId());
			ps.setString(2, CostumeInfos[i].getColor());
			ps.setString(3, CostumeInfos[i].getSize());
			count += ps.executeUpdate();
		}
		return count;
	}
	
	public int deleteInfos(Connection conn,int CostumeId,String color)throws Exception{
		String sql = "select * from t_costumeInfo where costumeId=? and color=?"; //查询是否存在
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, CostumeId);
		ps.setString(2, color);
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			return 0;
		}
		String sql2 = "delete from t_costumeInfo where costumeId=? and color=?";
		ps = conn.prepareStatement(sql2);
		ps.setInt(1, CostumeId);
		ps.setString(2, color);
		return ps.executeUpdate();
	}
		
	public boolean isExist(Connection conn,int costumeId) throws Exception{
		String sql = "select * from t_costume where costumeId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, costumeId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return true;
		}else {
			return false;
		}
	}
	
	public ResultSet query(Connection conn,Costume costume,double[] prices,String order)throws Exception{
		StringBuffer sb = new StringBuffer("select * from t_costume");
		if (costume.getCostumeName()!=null) {
			sb.append(" and costumeName like '%"+costume.getCostumeName()+"%'");
		}
		if (costume.getBrand()!=null) {
			sb.append(" and brand like '%"+costume.getBrand()+"%'");
		}
		if (costume.getSource()!=null) {
			sb.append(" and source like '%"+costume.getSource()+"%'");
		}
		if (costume.getCostumeType()!=null) {
			sb.append(" and costumeType like '%"+costume.getCostumeType()+"%'");
		}
		if ( prices!=null && prices[0] >= 0) { //若不选择这个条件，prices 为null
			sb.append(" and price between "+prices[0]+" and "+prices[1]);
		}
		if (order!=null) {
			sb.append(" order by price "+order); // order 为DESC 或 ASC
		}
		
		PreparedStatement ps = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return ps.executeQuery();
	}
	
	public ResultSet query(Connection conn,Costume costume,String order)throws Exception{ //函数重载
		StringBuffer sb = new StringBuffer("select * from t_costume");
		if (costume.getCostumeName()!=null) {
			sb.append(" and costumeName like '%"+costume.getCostumeName()+"%'");
		}
		if (costume.getCostumeType()!=null) {
			sb.append(" and costumeType like '%"+costume.getCostumeType()+"%'");
		}
		if (order!=null) {
			sb.append(" order by price "+order); // order 为DESC 或 ASC
		}
		
		PreparedStatement ps = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return ps.executeQuery();
	}
	
	public Costume query(Connection conn,int costumeId) throws Exception{ //函数重载
		Costume costume = null;
		String sql = "select * from t_costume where costumeId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, costumeId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			costume = new Costume();
			costume.setCostumeId(costumeId);
			costume.setCostumeName(FormatUtil.rtrim(rs.getString("costumeName")));
			costume.setBrand(FormatUtil.rtrim(rs.getString("brand")));
			costume.setSource(FormatUtil.rtrim(rs.getString("source")));
			costume.setCostumeType(FormatUtil.rtrim(rs.getString("costumeType")));
			costume.setPrice(rs.getDouble("price"));
			costume.setDescribe(FormatUtil.rtrim(rs.getString("describe")));
		}
		return costume;
	}
	
	public ResultSet quetyInfos(Connection conn,int costumeId)throws Exception{
		String sql = "select * from t_costumeInfo ci,t_costume c where ci.costumeId=c.costumeId and ci.costumeId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, costumeId);
		return ps.executeQuery();
	}
	
	public ResultSet queryPS(Connection conn,int costumeId ,String color ,String size)throws Exception{
		String sql = "select * from t_costumeInfo ci,t_costume c where ci.costumeId=c.costumeId and ci.costumeId=? and color=? and size=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, costumeId);
		ps.setString(2, color);
		ps.setString(3, size);
		return ps.executeQuery();
	}
	
	public ResultSet queryColor(Connection conn,int costumeId)throws Exception{
		String sql = "select distinct color from t_costumeInfo where costumeId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, costumeId);
		return ps.executeQuery();
	}
	
	public ResultSet querySize(Connection conn,int costumeId,String color)throws Exception{
		String sql = "select * from t_costumeInfo where costumeId=? and color=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, costumeId);
		ps.setString(2, color);
		return ps.executeQuery();
	}
	
	public ResultSet queryStorage(Connection conn,int costumeId,String color,String size)throws Exception{
		String sql = "select * from t_costumeInfo where costumeId=? and color=? and size=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, costumeId);
		ps.setString(2, color);
		ps.setString(3, size);
		return ps.executeQuery();
	}
	
	public int update(Connection conn,Costume costume)throws Exception{
		String sql = "update t_costume set costumeName=?,brand=?,source=?,costumeType=?,price=?,describe=? where costumeId=?";
		PreparedStatement ps =conn.prepareStatement(sql);
		ps.setString(1, costume.getCostumeName());
		ps.setString(2, costume.getBrand());
		ps.setString(3, costume.getSource());
		ps.setString(4, costume.getCostumeType());
		ps.setDouble(5, costume.getPrice());
		ps.setString(6, costume.getDescribe());
		ps.setInt(7, costume.getCostumeId());
		return ps.executeUpdate();
	}
	
	public int reduceStorage(Connection conn,int costumeId,int qualtites,String color,String size)throws Exception{
		String sql = "update t_costumeInfo set storage=storage-? where costumeId=? and color=? and size=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, qualtites);
		ps.setInt(2, costumeId);
		ps.setString(3, color);
		ps.setString(4, size);
		return ps.executeUpdate();
	}
	
	public int increaseStorage(Connection conn,int costumeId,int qualtites,String color,String size)throws Exception{
		String sql = "update t_costumeInfo set storage=storage+? where costumeId=? and color=? and size=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, qualtites);
		ps.setInt(2, costumeId);
		ps.setString(3, color);
		ps.setString(4, size);
		return ps.executeUpdate();
	}
	
	public int delete(Connection conn,int costumeId)throws Exception{
		String sql = "delete from t_costume where costumeId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, costumeId);
		return ps.executeUpdate();
	}

}