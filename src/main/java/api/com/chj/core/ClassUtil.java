package api.com.chj.core;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class ClassUtil {

	private static final Logger logger = Logger.getLogger(ClassUtil.class);
	
	public static String getStringValueOfFieldName(Object owner,String filedName){		
		return (String)getValueFromGetterByFieldName(owner,filedName);
	}
	
	/**
	 * 20160708 Chen Huajun add 
	 * @param owner
	 * @param getterName
	 * @return
	 */

	
	public static Object getValueFromGetter(Object owner,String getterName){
		try {
			Class<?> clazz = owner.getClass();			 
			Method m = clazz.getDeclaredMethod(getterName);
			Object obj = m.invoke(owner);  
			return  obj; 
		} catch (Exception e) {
			// TODO: handle exception 
			logger.info(StackTraceUtil.getStackTrace(e));
			return null;
		}
	}
	
	public static Object getValueFromGetterByFieldName(Object owner,String filedName){
		try { 
			String getter = "get"+ filedName.substring(0,1).toUpperCase()+filedName.substring(1); 			
			return  getValueFromGetter(owner,getter); 
		} catch (Exception e) {
			// TODO: handle exception 
			logger.info(StackTraceUtil.getStackTrace(e));
			return null;
		}
	}
}
