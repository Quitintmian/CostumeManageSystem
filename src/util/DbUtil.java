package util;

import java.sql.*;
/**
 * 数据库工具类
 * @author computer
 *
 */
public class DbUtil {
	
	private final static String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=db_costume";
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
	
	private DbUtil() {}
	
	/**
	 * 连接数据库
	 * @return 连接对象
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
			return DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
	}
	
	/**
	 * close database connection
	 * @param conn 连接对象
	 * @param ps 数据库操作对象
	 * @param rs 结果集
	 */
	public static void close(Connection conn) {
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
}
