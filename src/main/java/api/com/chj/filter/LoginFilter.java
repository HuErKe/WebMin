package api.com.chj.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import api.com.chj.core.StringUtil;
import api.com.chj.po.User;

public class LoginFilter implements Filter {

	private static final Logger logger = Logger.getLogger(LoginFilter.class);
//	private static int count = 1;
	private String[] passUrls;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("LoginFilter.destory");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request = (HttpServletRequest)arg0;
		response = (HttpServletResponse)arg1;
		session = request.getSession();
		String requestUrl = request.getRequestURL().toString();
//		String requestUri = request.getRequestURI().toString();
//		logger.info("called LoginFilter.doFilter count="+(count++));
		logger.info("requestUrl=["+requestUrl+"]");
//		logger.info("requestUri=["+requestUri+"]");
		for(String item:passUrls){
//			logger.info("item=["+item+"]");
			if(item.equals("")) continue;
			if(requestUrl.indexOf(item) >= 0 ){
				logger.info("使用规则{"+item+"},正常流程");
				arg2.doFilter(arg0, arg1); 
				return ; 
			} 
		}
		logger.info("该url没有配置通过特权"); 
		String uname = request.getParameter("unm");
		if(StringUtil.emptyString(uname)){
			logger.info("session不含unm数据,转向登录界面"); 
			session.setAttribute("reqUrl", requestUrl);
			response.sendRedirect(request.getContextPath()+"/jspView/login.jsp");
		}
		else{
			User user = (User)session.getAttribute(uname);
			if(null != user){
				logger.info("session已含数据,正常流程");
				arg2.doFilter(arg0, arg1);
			}
			else{
				logger.info("session不含accountId数据,转向登录界面"); 
				session.setAttribute("reqUrl", requestUrl);
				response.sendRedirect(request.getContextPath()+"/jspView/login.jsp");				
			}
		} 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException { 
		logger.info("called LoginFilter.init()");
		this.passUrls = arg0.getInitParameter("passUrl").split(";");
	}

}
