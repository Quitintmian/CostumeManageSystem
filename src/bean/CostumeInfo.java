package bean;

/**
 * ��װ�����Ϣ��������װ�ĳ������ɫ�Ϳ�棩
 * @author computer
 *
 */
public class CostumeInfo {
	private int costumeId;//��װid���������ӷ�װ�������
	private String color;//��ɫ
	private String size;//����
	private int storage;//��棬ÿ����װ��ͬ���룬��ͬ��ɫ���в�ͬ�Ŀ��
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