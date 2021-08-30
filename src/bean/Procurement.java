package bean;

import java.util.Date;

/**
 * �ɹ���Ϣʵ��
 * @author computer
 *
 */
public class Procurement {
	private int procurementId; // �ɹ����
	private int adminId; //����Ա���
	private int costumeId; // ��װ��ţ��ɹ�ʱÿ����װ��Ϊ���۵�0.7��
	private int supplierId; //��Ӧ�̱��
	private String color; //��ɫ
	private String size; // ����
	private int qualtity; //����
	private double total; //�ܽ��
	private Date createdTime; // ��������
	
	public Procurement() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Procurement(int procurementId, int adminId, int costumeId) {
		super();
		this.procurementId = procurementId;
		this.adminId = adminId;
		this.costumeId = costumeId;
	}
	
	public Procurement(int adminId, int costumeId, int supplierId, String color, String size, int qualtity) {
		super();
		this.adminId = adminId;
		this.costumeId = costumeId;
		this.supplierId = supplierId;
		this.color = color;
		this.size = size;
		this.qualtity = qualtity;
	}


	public int getProcurementId() {
		return procurementId;
	}
	public void setProcurementId(int procurementId) {
		this.procurementId = procurementId;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getCostumeId() {
		return costumeId;
	}
	public void setCostumeId(int costumeId) {
		this.costumeId = costumeId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
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