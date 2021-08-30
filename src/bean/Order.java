package bean;

import java.util.Date;

/**
 * 订单实体类
 * @author computer
 *
 */
public class Order {
	private int orderId; //订单编号
	private int memberId; //会员编号
	private int costumeId; //服装编号
	private String color; //颜色
	private String size; // 尺码
	private String payMethod; //支付方式
	private int qualtity; //购买数量
	private double total; //支付总金额
	private Date createdTime; //创建时间
	
	public Order(int memberId) {
		super();
		this.memberId = memberId;
	}

	public Order(int orderId, int memberId, int costumeId) {
		super();
		this.orderId = orderId;
		this.memberId = memberId;
		this.costumeId = costumeId;
	}

	public Order(int memberId, int costumeId, String color, String size, String payMethod, int qualtity) {
		super();
		this.memberId = memberId;
		this.costumeId = costumeId;
		this.color = color;
		this.size = size;
		this.payMethod = payMethod;
		this.qualtity = qualtity;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getCostumeId() {
		return costumeId;
	}

	public void setCostumeId(int costumeId) {
		this.costumeId = costumeId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public int getQualtity() {
		return qualtity;
	}

	public void setQualtity(int qualtity) {
		this.qualtity = qualtity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}