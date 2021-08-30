package bean;

/**
 * 会员实体类
 * @author computer
 *
 */
public class Member {
	
	private int id; //会员编号,与登陆表中的id相同，是它的外键
	private String memberName; //会员姓名
	private String memberSex; //会员性别
	private String memberPhone; //会员电话
	private double totalConsumption;  //消费总额
	private double balance; //账户余额
	
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