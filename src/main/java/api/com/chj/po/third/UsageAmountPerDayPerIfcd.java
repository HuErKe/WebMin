package api.com.chj.po.third;

import api.com.chj.core.StringUtil;

/**
 * 
 * 每个接口每天使用明细 
 * @author Chen Huajun 
 *
 */
public class UsageAmountPerDayPerIfcd {
	
	private String ifCode 		= StringUtil.BLANCK_STRING; // 查询接口
	private String date			= StringUtil.BLANCK_STRING; // 查询时间
	private String queryId		= StringUtil.BLANCK_STRING;	// 查询编号
	private String ifRsCode		= StringUtil.BLANCK_STRING;	// 返回值或错误码
	private String ifRsDesc		= StringUtil.BLANCK_STRING;	// 返回描述或者错误描述
	private boolean hit			= false;					// 是否命中 
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("ifCode="+ifCode+"\r\n") ;
		sb.append("date="+date+"\r\n") ;
		sb.append("queryId="+queryId+"\r\n") ;
		sb.append("ifRsCode="+ifRsCode+"\r\n") ;
		sb.append("ifRsDesc="+ifRsDesc+"\r\n") ; 
		sb.append("hit="+hit+"\r\n") ; 
		return sb.toString();
	}


	public String getIfCode() {
		return ifCode;
	}


	public void setIfCode(String ifCode) {
		this.ifCode = ifCode;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getQueryId() {
		return queryId;
	}


	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}


	public String getIfRsCode() {
		return ifRsCode;
	}


	public void setIfRsCode(String ifRsCode) {
		this.ifRsCode = ifRsCode;
	}


	public String getIfRsDesc() {
		return ifRsDesc;
	}


	public void setIfRsDesc(String ifRsDesc) {
		this.ifRsDesc = ifRsDesc;
	}


	public boolean isHit() {
		return hit;
	}


	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	
}
