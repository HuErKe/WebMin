package api.com.chj.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {

	public static final String TIME_FULL_FIELD = "yyyy-MM-dd HH:mm:ss";
	private static Logger logger = Logger.getLogger(DateUtil.class);
	
	enum TimeUnit {
		DAY,
		HOUR,
		MINUTE,
		SECOND
	}
	
	private static final String TIME_NO_SEP_YEAR_TO_DAY = "yyyyMMdd";
	public static String getTodayNoSep(){
		Date dt = new Date();
		return new SimpleDateFormat(TIME_NO_SEP_YEAR_TO_DAY).format(dt);
	}
	
	public static long fullDateToTimeStamp(String strDate) throws Exception{
		SimpleDateFormat format =   new SimpleDateFormat(TIME_FULL_FIELD); 
		return format.parse(strDate).getTime(); 
	}
	
	public static String getDateFromStamp(String stamp) throws Exception{
		String result = null;
		try {
			result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(stamp)));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(StackTraceUtil.getStackTrace(e));
		} 
		return result;
	}
}
