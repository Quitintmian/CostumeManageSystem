package util;

public class FormatUtil {
	
	private FormatUtil() {}
	
	/**
	 * // ȥ���Ҳ�ĩβ�Ŀո񣬱������ݿ��ж���ո��Ӱ��
	 * @param value
	 * @return
	 */
	public static String rtrim(String value){
		   if(value==null) return null;
		   return value.replaceAll("\\s+$",""); 
	}
}