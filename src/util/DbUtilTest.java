package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtilTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			System.out.println("已连接到SQL Server！");
			System.out.println(conn);
			String sql = "select * from t_userLogin";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.print("id:"+rs.getInt("id")+"\t\t\t");
				System.out.print("userName:"+FormatUtil.rtrim(rs.getString("userName"))+"\t\t\t");
				System.out.print("userName:"+FormatUtil.rtrim(rs.getString("password"))+"\t\t\t");
				System.out.print("userTypeFlag:"+rs.getInt("userTypeFlag")+"\n");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}