package bean;

/**
 * 服装库存信息表（包括服装的尺码和颜色和库存）
 * @author computer
 *
 */
public class CostumeInfo {
	private int costumeId;//服装id，用来连接服装表，是外键
	private String color;//颜色
	private String size;//尺码
	private int storage;//库存，每件服装不同尺码，不同颜色都有不同的库存
	public CostumeInfo() {
		super();
		// TODO Auto-generated constructor stub
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
	public int getStorage() {
		return storage;
	}
	public void setStorage(int storage) {
		this.storage = storage;
	}
	
}