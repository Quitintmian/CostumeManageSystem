package util;

import java.sql.*;
/**
 * ���ݿ⹤����
 * @author computer
 *
 */
public class DbUtil {
	
	private final static String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=db_costume";
	private final static String dbUserName = "sa";
	private final static String dbPassword = "123456";
	private final static String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//�����ʱ�Զ�ִ��static��̬�����
	static{
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private DbUtil() {}
	
	/**
	 * �������ݿ�
	 * @return ���Ӷ���
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
			return DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
	}
	
	/**
	 * close database connection
	 * @param conn ���Ӷ���
	 * @param ps ���ݿ��������
	 * @param rs �����
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
