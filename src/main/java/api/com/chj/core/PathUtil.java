package api.com.chj.core;

import java.io.File;

import org.apache.log4j.Logger;

/**
 * 20160811  
 * @author Chen Hujaun
 *
 */
public class PathUtil {

	private static final Logger logger = Logger.getLogger(PathUtil.class);

	/**
	 * [0]代表文件名,[1]代表后缀
	 * 20160821 CHJ
	 * @param fileFullName
	 * @return
	 */
	public static String[] getFileNameOfFile(String fileFullName){

		String[] result = null;
		try { 
			int index = fileFullName.lastIndexOf(File.separator);
			if(index <=0 || index + 1 >= fileFullName.length()){
				return result;
			}
			String tem = fileFullName.substring(index + 1);
			int sufIndex = tem.indexOf('.');
			if(sufIndex <= 0 || sufIndex >= tem.length()){
				return result;
			} 
			result = new String[]{tem.substring(0, sufIndex),tem.substring(sufIndex+1)};
			
		} catch (Exception e) {
			logger.info(StackTraceUtil.getStackTrace(e));
		}
		
		return result;
	}
	/**
	 *  [0]代表文件名,[1]代表后缀
	 * 20160821 CHJ
	 * @param file
	 * @return
	 */
	public static String[] getFileNameOfFile(File file){	
		return getFileNameOfFile(file.getAbsolutePath()); 
	}
	
	/**
	 * 获取一个类的运行目录
	 * 20160811
	 * @param clazz
	 * @return
	 */
	public static String getRunTimePathOfClass(Class<?> clazz){		
		return clazz.getResource("").getPath()
				.substring(1).replace("/", File.separator);
	}
}
