package api.com.chj.vo;

import api.com.chj.core.StringUtil;
import api.huiyu.client.HuiYuRequestQueryCondition;

/**
 * 请求参数
 * LV1
 */
public class ClientRequestInfo {

	private String user_name 							= StringUtil.BLANCK_STRING;
	private String time_stamp							= StringUtil.BLANCK_STRING;
	private String verify_key							= StringUtil.BLANCK_STRING;
	private String api_version							= StringUtil.BLANCK_STRING;
	private HuiYuRequestQueryCondition	query_condition = new HuiYuRequestQueryCondition();
	private String business_set							= StringUtil.BLANCK_STRING;
	private String product_name							= StringUtil.BLANCK_STRING; 
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("user_name="+user_name+"\r\n");
		sb.append("time_stamp="+time_stamp+"\r\n");
		sb.append("verify_key="+verify_key+"\r\n");
		sb.append("api_version="+api_version+"\r\n");	
		sb.append("query_condition="+query_condition+"\r\n"); 
		sb.append("business_set="+business_set+"\r\n");
		sb.append("product_name="+product_name+"\r\n"); 
		return sb.toString();
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	public String getVerify_key() {
		return verify_key;
	}
	public void setVerify_key(String verify_key) {
		this.verify_key = verify_key;
	}	
	public String getApi_version() {
		return api_version;
	}
	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}

	public HuiYuRequestQueryCondition getQuery_condition() {
		return query_condition;
	}
	public void setQuery_condition(HuiYuRequestQueryCondition query_condition) {
		this.query_condition = query_condition;
	}
	public String getBusiness_set() {
		return business_set;
	}
	public void setBusiness_set(String business_set) {
		this.business_set = business_set;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	} 
	
	
}
