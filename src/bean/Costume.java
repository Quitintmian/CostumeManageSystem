package bean;

/**
 * 服装实体类
 * @author computer
 *
 */
public class Costume {

	private int costumeId;//服装编号
	private String costumeName;//服装名称
	private String brand;//品牌
	private String source;//产地
	private String costumeType;//服装类型
	private double price;//单价
	private String describe;//服装描述
	
	public Costume(String costumeName, String costumeType) {
		super();
		this.costumeName = costumeName;
		this.costumeType = costumeType;
	}

	public Costume(String costumeName, String brand, String source, String costumeType) {
		super();
		this.costumeName = costumeName;
		this.brand = brand;
		this.source = source;
		this.costumeType = costumeType;
	}

	public Costume(String costumeName, String brand, String source, String costumeType, double price, String describe) {
		super();
		this.costumeName = costumeName;
		this.brand = brand;
		this.source = source;
		this.costumeType = costumeType;
		this.price = price;
		this.describe = describe;
	}

	// 全参数构造
	public Costume(int costumeId, String costumeName, String brand, String source, String costumeType, double price,
			String describe) {
		super();
		this.costumeId = costumeId;
		this.costumeName = costumeName;
		this.brand = brand;
		this.source = source;
		this.costumeType = costumeType;
		this.price = price;
		this.describe = describe;
	}

	public Costume() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCostumeId() {
		return costumeId;
	}

	public void setCostumeId(int costumeId) {
		this.costumeId = costumeId;
	}

	public String getCostumeName() {
		return costumeName;
	}

	public void setCostumeName(String costumeName) {
		this.costumeName = costumeName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCostumeType() {
		return costumeType;
	}

	public void setCostumeType(String costumeType) {
		this.costumeType = costumeType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}