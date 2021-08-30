package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 用于数据库备份与恢复的Dao
 * @author computer
 *
 */
public class SecureDao {
	
	private final static String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=master"; //使用master数据库进行备份与恢复
	private final static String dbUserName = "sa";
	private final static String dbPassword = "123456";
	private final static String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//类加载时自动执行static静态代码块
	static{
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
			return DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
	}
	
	public static void close(Connection conn) {
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	//备份
	public void backup(){
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "backup database db_costume to disk='C:\\EclipseWorkSpace\\CostumeManageSystem\\save\\db_costume.bak' with init";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(conn);
		}
	}
	
	public void restore(){
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "restore database db_costume from disk='C:\\EclipseWorkSpace\\CostumeManageSystem\\save\\db_costume.bak' with replace";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(conn);
		}
	}
}