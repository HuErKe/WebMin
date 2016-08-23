package api.com.chj.core;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CollectionUtil {

	public static boolean isEmpty(Collection<?> c){
//		return (null != c && c.isEmpty()); // 20160616 mod 
		return (null == c || c.isEmpty());
	}
	
	public static boolean emptySet(Set<?> set){
		return (null != set && set.size() <= 0);
	}
	
	public static boolean emptyList(List<?> list){
		return (null != list && list.size() <= 0);		
	}
	
	public static boolean stringListEqual(List<String> l1,List<String> l2) {
		if(isEmpty(l1) || isEmpty(l2) || l1.size() != l2.size()){
			return false;
		}
		
		for(int i =0;i<l1.size();i++){
			if(!l1.get(i).equals(l2.get(i))){
				return false;
			}
		}
		
		return true;
	}
}
