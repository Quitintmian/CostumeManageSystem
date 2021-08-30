package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Member;
import bean.User;

public class MemberDao {
	
	private UserDao userDao = new UserDao();

	public int add(Connection conn,Member member) throws Exception{
		String sql = "insert into t_member values(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, member.getId());
		ps.setString(2, member.getMemberName());
		ps.setString(3, member.getMemberSex());
		ps.setString(4, member.getMemberPhone());
		ps.setDouble(5, member.getTotalConsumption());
		ps.setDouble(6, member.getBalance());
		return ps.executeUpdate();
	}
	
	public Member querySelf(Connection conn,int id)throws Exception{
		String sql = "select * from t_member where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		Member member =new Member(id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			member.setMemberName(rs.getString("memberName"));
			member.setMemberSex(rs.getString("memberSex"));
			member.setMemberPhone(rs.getString("memberPhone"));
			member.setTotalConsumption(rs.getDouble("totalConsumption"));
			member.setBalance(rs.getDouble("balance"));
			return member;
		}else {
			return null;
		}
	}
	
	public ResultSet query(Connection conn,Member member)throws Exception{
		StringBuffer sb = new StringBuffer("select * from t_userLogin u,t_member m where u.id=m.id");//多表连接查询
		if (member.getMemberName()!=null) {
			sb.append(" and m.memberName like '%"+member.getMemberName()+"%'"); //注意开头的空格
		}
		if (member.getMemberSex()!=null&&member.getMemberSex()!="请选择...") {
			sb.append(" and m.memberSex like '%"+member.getMemberSex()+"%'");
		}
		if (member.getMemberPhone()!=null) {
			sb.append(" and m.memberPhone like '%"+member.getMemberPhone()+"%'");
		}
		
		PreparedStatement ps = conn.prepareStatement(sb.toString());
		return ps.executeQuery();
	}
	
	
	public int updateBalance(Connection conn,Member member,double amout)throws Exception{
		String sql = "update t_member set balance=? where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, member.getBalance()+amout);
		ps.setInt(2, member.getId());
		return ps.executeUpdate();
	}
	
	public int reduceBalance(Connection conn,int memberId,double total)throws Exception{
		String sql = "update t_member set balance=balance-? where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, total);
		ps.setInt(2, memberId);
		return ps.executeUpdate();
	}
	
	public int update(Connection conn,Member member,User user)throws Exception{
		String sql = "update t_member set memberName=?,memberSex=?,memberPhone=?,totalConsumption=?,balance=? where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, member.getMemberName());
		ps.setString(2, member.getMemberSex());
		ps.setString(3, member.getMemberPhone());
		ps.setDouble(4, member.getTotalConsumption());
		ps.setDouble(5, member.getBalance());
		ps.setInt(6, member.getId());
		if (ps.executeUpdate() == 0 || userDao.update(conn, user) == 0) {
			return 0;
		}
		return 1;
	}
	
	public int update(Connection conn,Member member)throws Exception{
		String sql = "update t_member set memberName=?,memberSex=?,memberPhone=? where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, member.getMemberName());
		ps.setString(2, member.getMemberSex());
		ps.setString(3, member.getMemberPhone());
		ps.setInt(4, member.getId());
		return ps.executeUpdate();
	}
	
	public int updateTotalConsumption(Connection conn,int memberId,double total)throws Exception{
		String sql = "update t_member set totalConsumption=totalConsumption+? where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, total);
		ps.setInt(2, memberId);
		return ps.executeUpdate();
	}

}