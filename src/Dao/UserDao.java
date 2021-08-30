package Dao;

/**
 * UserDao��
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;
import util.FormatUtil;

public class UserDao {
	
	public User login(Connection conn,User user) throws Exception{
		User resultUser = null; // ��½ʧ�ܷ���null
		
		String sql = "select * from t_userLogin where userName=? and password=? and userTypeFlag=?";
		PreparedStatement ps = conn.prepareStatement(sql); // Ԥ����sql���
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getPassword());
		ps.setInt(3, user.getUserTypeFlag());
		
		ResultSet rs = ps.executeQuery(); // ���ز�ѯ�����
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
	 * @return ע��ɹ�����ע��id��ʧ�ܷ���0
	 * @throws Exception
	 */
	public int register(Connection conn,User user) throws Exception{ // ֻ�пͻ���ע��
		String sql = "select * from t_userLogin where userName=? and userTypeFlag=0";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUserName());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) { // ����������ݣ���ʾ���ظ����û�����ע��ʧ�ܣ�����false
			return 0;
		}
		String sql2 = "insert into t_userLogin(userName,password,userTypeFlag) values(?,?,0)";
		ps = conn.prepareStatement(sql2);
		ps.setString(1, user.getUserName());
		ps.setString(2,user.getPassword());
		ps.executeUpdate();
		
		String sql3 = "select * from t_userLogin where userName=? and userTypeFlag=0"; // ע�����Ҫ��ȡid
		ps  =conn.prepareStatement(sql3);
		ps.setString(1, user.getUserName());
		rs = ps.executeQuery();
		rs.next();
		return rs.getInt("id");
	}
	
	public int delete(Connection conn,int id) throws Exception{// ����ɾ��
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