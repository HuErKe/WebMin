package api.com.chj.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import api.com.chj.core.StackTraceUtil;

@Controller
public class ExceptionDealController {

	private static final Logger logger = Logger.getLogger(ExceptionDealController.class);
	
	@RequestMapping("/error")
	@ResponseBody
	public Object dealException(){
		Map<String,Object> result = new HashMap<String,Object>();
		try { 
			result.put("result_code", ResponseErrorCodeUtil.RESPONSE_CODE_ERROR_URL_PATH);
			result.put("result_desc", "api接口路径错误"); 
			result.put("result", "");
			
		} catch (Exception e) {
			// TODO: handle exception			
			logger.info(StackTraceUtil.getStackTrace(e));
		}
		
		return result;
	}
}
