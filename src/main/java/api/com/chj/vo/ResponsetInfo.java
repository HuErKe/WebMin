package api.com.chj.vo;

import com.alibaba.fastjson.JSONObject;

import api.com.chj.core.StringUtil;

public class ResponsetInfo {
	
	private int result_code			= 0; // 0 for success
	private String result_desc		= StringUtil.BLANCK_STRING;
	private JSONObject result ;  
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("result_code="+result_code+"\r\n") ;
		sb.append("result_desc="+result_desc+"\r\n") ;
		sb.append("result="+result+"\r\n") ; 
		return sb.toString();
	}

	public int getResult_code() {
		return result_code;
	}

	public void setResult_code(int result_code) {
		this.result_code = result_code;
	}

	public String getResult_desc() {
		return result_desc;
	}

	public void setResult_desc(String result_desc) {
		this.result_desc = result_desc;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	} 	
}
