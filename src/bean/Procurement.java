package bean;

import java.util.Date;

/**
 * 采购信息实体
 * @author computer
 *
 */
public class Procurement {
	private int procurementId; // 采购编号
	private int adminId; //管理员编号
	private int costumeId; // 服装编号，采购时每件服装设为单价的0.7倍
	private int supplierId; //供应商编号
	private String color; //颜色
	private String size; // 尺码
	private int qualtity; //数量
	private double total; //总金额
	private Date createdTime; // 生成日期
	
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