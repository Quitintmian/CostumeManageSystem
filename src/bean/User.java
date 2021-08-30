package bean;

/**
 * ��¼�û�ʵ�壨���ͻ��͹���Ա��
 * @author computer
 *
 */
public class User {
	
	private int id; // ���
	private String userName; // �û���
	private String password; // ����
	private int userTypeFlag; // ��ʶ
	
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public User(String userName, String password, int userTypeFlag) {
		super();
		this.userName = userName;
		this.password = password;
		this.userTypeFlag = userTypeFlag;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserTypeFlag() {
		return userTypeFlag;
	}
	public void setUserTypeFlag(int userTypeFlag) {
		this.userTypeFlag = userTypeFlag;
	}

}