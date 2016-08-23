package api.com.chj.vo;

import api.com.chj.core.StringUtil;

public class RequestInfo {
	
//	private String query_id 			= StringUtil.BLANCK_STRING;
//	private String query_type 			=  StringUtil.BLANCK_STRING;
	private String api_id				=  StringUtil.BLANCK_STRING;
//	private String business_set			=  StringUtil.BLANCK_STRING; // required 20160703 Chen Huajun mod begin 

	private RequestQueryCondition query_condition;
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
//		sb.append("query_id="+query_id+"\r\n") ;
//		sb.append("query_type="+query_type+"\r\n") ;
		sb.append("api_id="+api_id+"\r\n") ;
//		sb.append("business_set="+business_set+"\r\n") ;
//		sb.append("urls="+urls+"\r\n") ;
		
		sb.append("query_condition="+query_condition+"\r\n") ; 
		return sb.toString();
	}

	public String getApi_id() {
		return api_id;
	}

	public void setApi_id(String api_id) {
		this.api_id = api_id;
	}

	public RequestQueryCondition getQuery_condition() {
		return query_condition;
	}

	public void setQuery_condition(RequestQueryCondition query_condition) {
		this.query_condition = query_condition;
	}
	 
	
}
