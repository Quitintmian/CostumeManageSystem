package bean;

/**
 * ��Աʵ����
 * @author computer
 *
 */
public class Member {
	
	private int id; //��Ա���,���½���е�id��ͬ�����������
	private String memberName; //��Ա����
	private String memberSex; //��Ա�Ա�
	private String memberPhone; //��Ա�绰
	private double totalConsumption;  //�����ܶ�
	private double balance; //�˻����
	
	public Member(int id) {
		super();
		this.id = id;
	}
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberSex() {
		return memberSex;
	}
	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public double getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(double totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}