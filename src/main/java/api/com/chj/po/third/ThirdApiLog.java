package api.com.chj.po.third;

import api.com.chj.core.StringUtil;

public class ThirdApiLog {
	private String query_id = StringUtil.BLANCK_STRING;
	private String query_time = StringUtil.BLANCK_STRING;
	private String api_id = StringUtil.BLANCK_STRING;
	private String req_url = StringUtil.BLANCK_STRING;
	private String req_method = StringUtil.BLANCK_STRING;
	private String request_text = StringUtil.BLANCK_STRING;
	private String response_text = StringUtil.BLANCK_STRING;
	
	public ThirdApiLog clone(){
		ThirdApiLog dest = new ThirdApiLog();
		dest.setQuery_id(this.query_id);
		dest.setQuery_time(this.query_time);
		dest.setApi_id(this.api_id);
		dest.setReq_url(this.req_url);
		dest.setReq_method(this.req_method);
		dest.setRequest_text(this.request_text);
		dest.setResponse_text(this.response_text);
		return dest;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("query_id="+query_id+"\r\n") ;
		sb.append("query_time="+query_time+"\r\n") ;
		sb.append("api_id="+api_id+"\r\n") ;
		sb.append("req_url="+req_url+"\r\n") ;
		sb.append("req_method="+req_method+"\r\n") ;
		sb.append("request_text="+request_text+"\r\n") ;
		sb.append("response_text="+response_text+"\r\n") ;
		return sb.toString();
	}
	
	public String getQuery_id() {
		return query_id;
	}
	public void setQuery_id(String query_id) {
		this.query_id = query_id;
	}
	public String getQuery_time() {
		return query_time;
	}
	public void setQuery_time(String query_time) {
		this.query_time = query_time;
	}
	public String getApi_id() {
		return api_id;
	}
	public void setApi_id(String api_id) {
		this.api_id = api_id;
	}
	public String getReq_url() {
		return req_url;
	}
	public void setReq_url(String req_url) {
		this.req_url = req_url;
	}
	public String getReq_method() {
		return req_method;
	}
	public void setReq_method(String req_method) {
		this.req_method = req_method;
	}
	public String getRequest_text() {
		return request_text;
	}
	public void setRequest_text(String request_text) {
		this.request_text = request_text;
	}
	public String getResponse_text() {
		return response_text;
	}
	public void setResponse_text(String response_text) {
		this.response_text = response_text;
	}
	
	
}
