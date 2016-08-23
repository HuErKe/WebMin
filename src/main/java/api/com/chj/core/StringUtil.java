package api.com.chj.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class StringUtil {

	public static final Logger logger = Logger.getLogger(StringUtil.class);
	public static final String EMPTY_STRING = "";
	public static final String BLANCK_STRING = " ";
	

	/**
	 * 20160804 CHJ
	 * @param items
	 * @return
	 */
	public static String getSortedString(List items){ 
		Collections.sort(items);
		StringBuilder sb = new StringBuilder("");
		for(int i=0;i<items.size();i++){
			sb.append(";"+items.get(i));
		} 
		return sb.append(";").toString(); 
	}
	/**
	 * 20160804 CHJ
	 * @param strOriginal
	 * @param sep
	 * @param order
	 * @return
	 */	
	public static String getSortedString(String strOriginal,String sep,String order){
		if(StringUtil.emptyString(strOriginal)){
			return StringUtil.EMPTY_STRING;
		}
		List<String> items = StringUtil.getItemListFromStr(strOriginal, sep);		 
		return getSortedString(items);
	}
	
	public static String getJsonStringValid(String sOriginal){
		if(emptyString(sOriginal)){
			return "";
		}
		
//		String result = null; 
//		result = sOriginal.replaceAll("\\\\", "\\\\\\\\");
//		result = sOriginal.replaceAll("\'", "\\\'");
//		result = sOriginal.replaceAll("\"", "\\\"");		
//		return result;
		
		return sOriginal.replaceAll("\\\\", "\\\\\\\\").replaceAll("\'", "\\\'").replaceAll("\"", "\\\"");
		
	}
	
	/**
	 *  移除不可见字符
	 * @param sOriginal
	 * @return
	 */
	public static String getStringAfterInvisibleCharRemoved(String sOriginal){
		if(emptyString(sOriginal)){
			return "";
		}
		
		return sOriginal.replaceAll("[\u0000-\u001f]", "");
	}
	
	public static String upperString(String s){
		if(null == s) return ""; 
		return s.trim().toUpperCase();
	}
	
	public static boolean emptyString(String s){
		
		return (null == s || s.trim().equals(""));
	}
	
	/**
	 * 拆分以分号分隔的字符串,转换为集合
	 * @param sIn
	 * @param separator
	 * @return
	 */
	public static HashSet<String> getItemFromStr(String sIn,String separator){
		HashSet<String> set = new HashSet<String>();			
		try {			
			String [] items = sIn.split(separator);
			if(items.length <= 0) return set;
			String item = null; 
			for(int i=0;i<items.length;i++){
				item = items[i].trim(); 
//				System.out.println("item=["+item+"]");
				if(!emptyString(item)){
					set.add(item);				
				}
			}			
			return set;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
			return set;
		} 
	}
	
	public static List<String> getItemListFromStr(String sIn,String separator){
		List<String> list = new ArrayList<String>(); 			
		try {			
			String [] items = sIn.split(separator);
			if(items.length <= 0) return list;
			String item = null; 
			for(int i=0;i<items.length;i++){
				item = items[i].trim(); 
//				System.out.println("item=["+item+"]");
				if(!emptyString(item)){
					list.add(item);				
				}
			}			
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
			return list;
		} 
	}


	public static Set<String> getCategoryFromStringSet(Set<String> set,String category) throws Exception{
		if(CollectionUtil.isEmpty(set)){ 
			throw new IllegalArgumentException("Param of set is null");
		}
		
		if(StringUtil.emptyString(category)){
			throw new IllegalArgumentException("Param of category is null");
		}
	
		Set<String> result = new HashSet<String>();
		Iterator<String> it = set.iterator();
		String item = null;
		int begin = 0;
		int end = 3;
		while(it.hasNext()){
			item = it.next().trim().toUpperCase();
//			System.out.println("getCategoryFromStringSet,item="+item);
			if(item.substring(begin, end).equalsIgnoreCase(category)){
				result.add(item);
			}			
		}
		return result;
	}
	

	private static String getStringOfSubStrV1(int lenTotal,String strSub){
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < lenTotal; i++) {
			sb.append('*');
		}
		return sb.toString(); 
	}
	/**
	 * 20160719
	 * @param lenTotal
	 * @param strSub
	 * @return
	 */
	public static String getStringOfSubStr(int lenTotal,String strSub){		
		return getStringOfSubStrV1(lenTotal,strSub);
	}
	private static String getStarReplaceStrV1(String text,int startPos, int len){
		if(StringUtil.emptyString(text)){
			return StringUtil.EMPTY_STRING;
		}
		String actStr = text.trim();
		int actLen = actStr.length();
		if(startPos >= actLen){
			return StringUtil.EMPTY_STRING;			
		}
		if(startPos + len >= actLen){
			len = actLen - startPos;
		}
		String result = null;
//		String left = text.substring(0, startPos);
//		String mid = getStringOfSubStr(len,"*");
//		String right = text.substring(startPos + len);	
		result = text.substring(0, startPos) + getStringOfSubStr(len,"*")
				+ text.substring(startPos + len);
		return result; 
	}
	/**
	 * 20160719 
	 * @param text 源字符串
	 * @param startPos 开始替换位置
	 * @param len 替换字符串长度
	 * @return
	 */
	public static String getStarReplaceStr(String text,int startPos, int len){
		return getStarReplaceStrV1(text,startPos,len);
	}
}
