package util;

public class FormatUtil {
	
	private FormatUtil() {}
	
	/**
	 * // 去掉右侧末尾的空格，避免数据库中多余空格的影响
	 * @param value
	 * @return
	 */
	public static String rtrim(String value){
		   if(value==null) return null;
		   return value.replaceAll("\\s+$",""); 
	}
}