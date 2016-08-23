package api.com.chj.vo;

import api.com.chj.core.StringUtil;

public class QuerySerial {

	private String query_id = StringUtil.BLANCK_STRING;
	private String query_time = StringUtil.BLANCK_STRING;
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("query_id="+query_id+"\r\n");
		sb.append("query_time="+query_time+"\r\n"); 
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
	
	
}
