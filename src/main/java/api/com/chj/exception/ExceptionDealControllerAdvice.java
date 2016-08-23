package api.com.chj.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import api.com.chj.controller.CWCLoggingController;
import api.com.chj.controller.QueryController;
import api.com.chj.core.StackTraceUtil;

@ControllerAdvice(assignableTypes = {
		CWCLoggingController.class // 20160602
		,QueryController.class
}) 
public class ExceptionDealControllerAdvice {

	private Logger log = Logger.getLogger(ExceptionDealControllerAdvice.class);
	
	private String getBusinessTraceStack(Exception e){
		StackTraceElement[] stes = e.getStackTrace();
		if(null == stes || stes.length <= 0){
			return null;
		}
		
		StringBuilder sb = null;
		StackTraceElement ele = null; 
		String clazzName = null; 
		String methodName = null;
		String fileName = null;
		int errLineNo = 0; 
		sb = new StringBuilder(e.getClass().getName()); 
		for(int i=0;i<stes.length;i++){
			ele = stes[i]; 
			fileName = ele.getFileName();
			clazzName = ele.getClassName();
			methodName = ele.getMethodName();
			errLineNo = ele.getLineNumber(); 
			sb.append("\r\n"+clazzName+"."+methodName+"("+fileName+":"+errLineNo+")");
			if(clazzName.lastIndexOf("Controller") >= 0){
				break;
			}
		} 
		return sb.toString();
	}
	
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public Map<String,Object> handleException(Exception ex){
		Map<String,Object> result = new HashMap<String,Object>(); 
		String logMsg = null; 
		
		try {			
			if(ex instanceof HttpMediaTypeNotSupportedException){
				result.put("result_code",ResponseErrorCodeUtil.RESPONSE_CODE_FIELD_FORMAT_INVALID); 
				result.put("result_desc","数据格式错误,需要指定为json格式");
				log.info(ex.getMessage());

				logMsg = "["+ResponseErrorCodeUtil.RESPONSE_CODE_FIELD_FORMAT_INVALID+"]["+"数据格式错误,需要指定为json格式"+"]"; 				
			}
			else if(ex instanceof InnerException){
				result.put("result_code",ResponseErrorCodeUtil.RESPONSE_CODE_INNER_ERROR); 
				result.put("result_desc","内部错误"
						);
				logMsg = ""+(InnerException)ex; 				
				log.info(logMsg);
			}
			else if(ex instanceof ResponseException){
				ResponseException exp = (ResponseException)ex;
				result.put("result_code",exp.getErrCode()); 
				result.put("result_desc",exp.getErrMsg());
				logMsg = ""+exp; 				
				log.info(logMsg);
			}
			else{    
				log.info("=======================================================================================================");
				result.put("result_code", ResponseErrorCodeUtil.RESPONSE_CODE_OTHER_ERROR);
				result.put("result_desc", "其他错误"); 
				log.info(this.getBusinessTraceStack(ex));					
				logMsg =  "other exception";
				log.info(logMsg);  
				log.info("=======================================================================================================");
			}	 
			 
			result.put("result", "");
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info(StackTraceUtil.getStackTrace(e));
		}
		
		return result;
	} 
}
