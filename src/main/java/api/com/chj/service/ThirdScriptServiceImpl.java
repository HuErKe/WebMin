package api.com.chj.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import api.com.chj.core.StackTraceUtil;
import api.com.chj.core.StringUtil;

@Service
public class ThirdScriptServiceImpl implements ThirdScriptService {

	private static final Logger logger = Logger.getLogger(ThirdScriptServiceImpl.class); 
	private static String PYTHON_COMMAND = "python3.5 "; 
	private static String PYTHON_SCRIPT_NAME = "project_py"+File.pathSeparator+"customer_api.py";
	public void executePythonToGetSocialDataByQq(String qq) throws Exception{
		if(StringUtil.emptyString(qq)){
			logger.info("qq is empty");
			return ;
		}
		String filePath = null;
		// {python3.5 filename -a api_qq_social_chum -b qq -c 1}
		try {
			filePath = this.getClass().getResource("").getPath().substring(1);
			logger.info("filename="+PYTHON_COMMAND + filePath + PYTHON_SCRIPT_NAME);
			StringBuilder sb = new StringBuilder();
	        sb.append(PYTHON_COMMAND + filePath + PYTHON_SCRIPT_NAME); 
	        sb.append(" -a "); // 参数一, 任务类别
	        sb.append("api_verify_qq_name");
	        sb.append(" -b "); // 参数二, qq号
	        sb.append(qq); 
			String cmd = sb.toString();
			logger.info("cmd={"+cmd+"}");
	        this.executeScriptWithParaStr(cmd);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(StackTraceUtil.getStackTrace(e));
			return ;
		}
		 
	}
	
	/**
	 * 20160714 Chen Huajun add
	 * @param paramString
	 * @throws Exception
	 */
	private void executeScriptWithParaStr(String paramString) throws Exception{	
	    try{
	            Process pr = Runtime.getRuntime().exec(paramString);
	            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(),"utf-8"));
	            String line;
	            while ((line = in.readLine()) != null) {
	                System.out.println(line);
	                logger.info(line);
	            }
	            in.close();
	            pr.waitFor();
	            logger.info("script finish executing");
	    } catch (Exception e){
	        logger.info(StackTraceUtil.getStackTrace(e)); 
	    } 
	} 

}
