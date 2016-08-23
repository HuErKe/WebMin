/**
 * 
 */
package api.com.chj.core;

import java.security.MessageDigest;

/**
 * <p>Title: EncodeUtil</p>
 * <p>Description: TODO</p>
 * <p>Company: TODO</p>
 * @author Chen Huajun
 * @date 2016��4��1�� ����11:57:40
 * 
 */
public class EncodeUtil {

public final static char[] strHex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

	public final static boolean md5Validate(String strExpected,String strActural) throws Exception{		
		
		if(strActural == null || strExpected == null){ //
			return false;
		}
		
		return strExpected.equals(encodeStrByMD5(strActural));
	}

	/**
	 * 用MD5算法加密输入字符串
	 */
	public final static String encodeStrByMD5(String sIn) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(sIn.getBytes("utf-8"));  
		byte[] bytes = md.digest(); 
		StringBuffer sb = new StringBuffer("");
		for(int i=0;i<bytes.length;i++){
			sb.append(strHex[bytes[i] >>> 4 & 0x000f]);  
			sb.append(strHex[bytes[i]  & 0x000f]);      
		}
		return sb.toString(); 
	}
}
