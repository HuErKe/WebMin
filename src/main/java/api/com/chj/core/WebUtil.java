package api.com.chj.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class WebUtil {
	private static final Logger logger = Logger.getLogger(WebUtil.class);
	
	public static Object getSessionAttributeFromRequest(String attrName,HttpServletRequest request){
		try {
			if(StringUtil.emptyString(attrName)){
				return null;
			}
			HttpSession session  = request.getSession();
			return session.getAttribute(attrName);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(StackTraceUtil.getStackTrace(e));
			return null;
		} 
	}
	
	public static boolean isAjaxRequest(HttpServletRequest request){ 
		boolean result = false;
		try { 
			String headerX = request.getHeader("X-Requested-With");			
			result = headerX != null  && headerX.equalsIgnoreCase("XMLHttpRequest");			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public static Map<String,String> getClientInfo(HttpServletRequest req,Map<String,String> map) {		
		String ip = req.getHeader("x-forwarded-for");  
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) { 
			 	ip = req.getHeader("Proxy-Client-IP"); 
			 } 
			 if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) { 
				 ip = req.getHeader("WL-Proxy-Client-IP"); 
			 } 
			 if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) { 
				 ip = req.getHeader("X-Real-IP"); 
			 } 
			 if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) { 
				 ip = req.getRemoteAddr(); 
		}
			 
		logger.info("clientIp:"+ ip);
		map.put(HuiYuServerConstants.KEY_CLIENT_IP, ip);
		map.put(HuiYuServerConstants.KEY_CLIENT_REQUEST_URL, req.getRequestURL().toString());		
		
		return map;
	}
}
