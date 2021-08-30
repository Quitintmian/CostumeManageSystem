package Dao;

/**
 * UserDao类
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;
import util.FormatUtil;

public class UserDao {
	
	public User login(Connection conn,User user) throws Exception{
		User resultUser = null; // 登陆失败返回null
		
		String sql = "select * from t_userLogin where userName=? and password=? and userTypeFlag=?";
		PreparedStatement ps = conn.prepareStatement(sql); // 预编译sql语句
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getPassword());
		ps.setInt(3, user.getUserTypeFlag());
		
		ResultSet rs = ps.executeQuery(); // 返回查询结果集
		if (rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setUserTypeFlag(rs.getInt("userTypeFlag"));
		}
		return resultUser;	
	}
	
	/**
	 * 
	 * @param conn
	 * @param user
	 * @return 注册成功返回注册id，失败返回0
	 * @throws Exception
	 */
	public int register(Connection conn,User user) throws Exception{ // 只有客户能注册
		String sql = "select * from t_userLogin where userName=? and userTypeFlag=0";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUserName());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) { // 结果集有数据，表示有重复的用户名，注册失败，返回false
			return 0;
		}
		String sql2 = "insert into t_userLogin(userName,password,userTypeFlag) values(?,?,0)";
		ps = conn.prepareStatement(sql2);
		ps.setString(1, user.getUserName());
		ps.setString(2,user.getPassword());
		ps.executeUpdate();
		
		String sql3 = "select * from t_userLogin where userName=? and userTypeFlag=0"; // 注册后需要获取id
		ps  =conn.prepareStatement(sql3);
		ps.setString(1, user.getUserName());
		rs = ps.executeQuery();
		rs.next();
		return rs.getInt("id");
	}
	
	public int delete(Connection conn,int id) throws Exception{// 级联删除
		String sql = "delete from t_userLogin where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		return ps.executeUpdate();
	}
	
	public boolean changePassword(Connection conn,int id,String oldPassword,String newPassword) throws Exception{
		String sql = "select * from t_userLogin where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()&&FormatUtil.rtrim(rs.getString("password")).equals(oldPassword)) {
			String sql2 = "update t_userLogin set password=? where id=?";
			ps = conn.prepareStatement(sql2);
			ps.setString(1, newPassword);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
		}else {
			return false;
		}		
	}
	
	public int update(Connection conn,User user)throws Exception{
		String sql = "update t_userLogin set userName=?,password=? where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getPassword());
		ps.setInt(3, user.getId());
		return ps.executeUpdate();
	}

}