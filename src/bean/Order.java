package bean;

import java.util.Date;

/**
 * ����ʵ����
 * @author computer
 *
 */
public class Order {
	private int orderId; //�������
	private int memberId; //��Ա���
	private int costumeId; //��װ���
	private String color; //��ɫ
	private String size; // ����
	private String payMethod; //֧����ʽ
	private int qualtity; //��������
	private double total; //֧���ܽ��
	private Date createdTime; //����ʱ��
	
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