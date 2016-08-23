package api.com.chj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import api.com.chj.core.HuiYuServerConstants;
import api.com.chj.core.StackTraceUtil;
import api.com.chj.core.StringUtil;
import api.com.chj.core.WebUtil;
import api.com.chj.exception.ResponseErrorCodeUtil;
import api.com.chj.exception.ResponseException;
import api.com.chj.po.LoginLog;
import api.com.chj.po.User;
import api.com.chj.service.UserService;

@RequestMapping("v1"+"/cwc")
@Controller
public class CWCLoggingController {

	private static final Logger logger = Logger.getLogger(CWCLoggingController.class);
	 
	private HttpSession session;
	 
	private UserService userService;  // 20160712
	
	private void getResponseOfSuccess(Map<String,Object> result){		
		result.put("result_code",200); 
		result.put("result_desc","成功");
		result.put("success", true);
		result.put("result", "");		 
	} 
	
	@RequestMapping("/logins")
	@ResponseBody
	public Object getLocation(HttpServletRequest request,
			@RequestBody User user) throws Exception{
		logger.info("请求信息:\r\n"+user); 
		Map<String,Object> result = new HashMap<String,Object>();	
		String uName = user.getUser_login_name();
		String pwd = user.getUser_password();
		if(StringUtil.emptyString(uName) || StringUtil.emptyString(pwd)){
			throw new ResponseException(ResponseErrorCodeUtil.RESPONSE_CODE_FIELD_EMPTY,"用户名或者密码为空");
		}
		
		Map<String,String> params = new HashMap<String,String>();
		WebUtil.getClientInfo(request, params);
		
		User dbUser = null;
//		try {
		dbUser = this.userService.getUserByUserLoginName(uName);
		if(null == dbUser){
			throw new ResponseException(ResponseErrorCodeUtil.RESPONSE_CODE_VERYFYKEY_INVALID,"账户或密码错误");				
		}
		if(!pwd.equals(dbUser.getUser_password())){
			throw new ResponseException(ResponseErrorCodeUtil.RESPONSE_CODE_VERYFYKEY_INVALID,"账户或密码错误");					
		}
		logger.info("user:\r\n"+user+"\r\ndbUser="+dbUser);
		session = request.getSession();
//			session.setAttribute("accountId", uName);
//		session.setMaxInactiveInterval(10*60);
		session.setAttribute(uName, dbUser);
		LoginLog llLog = new LoginLog();
		llLog.setLogin_client_ip(params.get(HuiYuServerConstants.KEY_CLIENT_IP));
//			llLog.setLogin_id(login_id);
		llLog.setLogin_type(1);
//			llLog.setProduct_id(dbUser.getProduct_set());
		llLog.setUser_id(dbUser.getUser_id());
	
		this.userService.saveLoginLog(llLog);
			
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.info(StackTraceUtil.getStackTrace(e));
//		}  
	 
 
		getResponseOfSuccess(result);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("reqUrl", (String)session.getAttribute("reqUrl")); 
		jsonObj.put("loginPas", true); 
		result.put("result", jsonObj);
		logger.info("服务端返回");
		return result;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
 

	 
	
	 
	
 
}
