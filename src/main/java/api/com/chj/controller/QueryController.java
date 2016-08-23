package api.com.chj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import api.com.chj.core.StackTraceUtil;
import api.com.chj.core.StringUtil;
import api.com.chj.core.WebUtil;
import api.com.chj.exception.ResponseErrorCodeUtil;
import api.com.chj.exception.ResponseException;
import api.com.chj.po.User;
import api.com.chj.service.HuiYuDataService;
import api.com.chj.service.ThirdScriptService;
import api.com.chj.service.UserService;
import api.com.chj.vo.ClientRequestInfo;
import api.com.chj.vo.RequestQueryCondition;
import api.huiyu.client.HuiYuRequestQueryCondition;

@RequestMapping("v1"+"/cwc")
@Controller
public class QueryController {

	private static final Logger logger = Logger.getLogger(CWCLoggingController.class);

	private ThirdScriptService thirdScriptService;
	private HuiYuDataService huiYuDataService; 
	private UserService userService;
	
	private void getResponseOfSuccess(Map<String,Object> result){		
		result.put("result_code",200); 
		result.put("result_desc","成功");
		result.put("success", true);
		result.put("result", "");		 
	} 
	
	private JSONObject getEnctryInfo(ClientRequestInfo cri) throws Exception{
		JSONObject jsonObj = new JSONObject();
		HuiYuRequestQueryCondition qc = cri.getQuery_condition();
		jsonObj.put("qName", StringUtil.getStarReplaceStr(qc.getQuery_name(),0,1));
		jsonObj.put("qCid", StringUtil.getStarReplaceStr(qc.getQuery_cid(),5,7));		
		jsonObj.put("qTel", StringUtil.getStarReplaceStr(qc.getQuery_mobile(),3,5));		
		jsonObj.put("qQq", StringUtil.getStarReplaceStr(qc.getQuery_qq(),2,4));				
		jsonObj.put("qMail", StringUtil.getStarReplaceStr(qc.getQuery_email(),2,4));		
		return jsonObj;
	}
	
	@RequestMapping("/queries")
	@ResponseBody
	public Object getLocation(HttpServletRequest request,
			@RequestBody ClientRequestInfo cri) throws Exception{
		logger.info("请求信息:\r\n"+cri); 
		Map<String,Object> result = new HashMap<String,Object>();
		getResponseOfSuccess(result);
		JSONObject jsonObj = new JSONObject();
		JSONObject jt = getEnctryInfo(cri);
		jsonObj.put("summaryInfo", jt); 
		
		 
		User user = null;
		try {
//			user = this.userService.getUserByUserLoginName("ydw");
//			user = (User)WebUtil.getSessionAttributeFromRequest("accountId", request);
			user = (User)WebUtil.getSessionAttributeFromRequest(cri.getUser_name(), request);
			logger.info("user in session:\r\n"+user);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(StackTraceUtil.getStackTrace(e));
		}
		
		if(null == user){
			throw new ResponseException(ResponseErrorCodeUtil.RESPONSE_CODE_SESSION_TIME_OUT,"会话失效"); 
		}
		
		JSONObject report = null;
//		try { 
			report = this.huiYuDataService.getData(cri, user); 
			jsonObj.put("report", report); 
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.info(StackTraceUtil.getStackTrace(e));
//		} 

		result.put("result", jsonObj);
		logger.info(JSON.toJSONString(result));
		
		
		return result;
	}

	@Resource
	public void setThirdScriptService(ThirdScriptService thirdScriptService) {
		this.thirdScriptService = thirdScriptService;
	}

	@Resource
	public void setHuiYuDataService(HuiYuDataService huiYuDataService) {
		this.huiYuDataService = huiYuDataService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
