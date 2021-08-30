package bean;

/**
 * 供应商实体
 * @author computer
 *
 */
public class Supplier {
	private int supplierId; //供应商编号
	private String supplierName;//供应商名称
	private String supplierPhone;//供应商电话
	private String address;//地址
	private String contactName;//联系人姓名
	private String contactPhone;//联系人电话
	private String comment;//备注
	
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Supplier(String supplierName, String address, String contactName) {
		super();
		this.supplierName = supplierName;
		this.address = address;
		this.contactName = contactName;
	}

	public Supplier(String supplierName, String supplierPhone, String address, String contactName, String contactPhone,
			String comment) {
		super();
		this.supplierName = supplierName;
		this.supplierPhone = supplierPhone;
		this.address = address;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.comment = comment;
	}

	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierPhone() {
		return supplierPhone;
	}
	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}