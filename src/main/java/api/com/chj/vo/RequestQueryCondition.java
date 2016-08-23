package api.com.chj.vo;

import api.com.chj.core.StringUtil;

public class RequestQueryCondition { 
	
//	private String query_name 			= StringUtil.BLANCK_STRING;	// optional,query_type 为 address_query时b必输,  required
//	private String query_cid 			= StringUtil.BLANCK_STRING;	// required
//	private String query_mobile 		= StringUtil.BLANCK_STRING; // required
	private String query_qq 			= StringUtil.BLANCK_STRING;
//	private String query_email 			= StringUtil.BLANCK_STRING;
//	private String address 				= StringUtil.BLANCK_STRING; // required  
//	private String home_address 		= StringUtil.BLANCK_STRING;
//	private String work_address 		= StringUtil.BLANCK_STRING;
//	private String contact_name 		= StringUtil.BLANCK_STRING;
//	private String contact_mobile 		= StringUtil.BLANCK_STRING;
//	private String contact_relationship = StringUtil.BLANCK_STRING;
//	private String car_num 				= StringUtil.BLANCK_STRING;
//	private String company_name 		= StringUtil.BLANCK_STRING;	 
	
	public String toString(){
		StringBuffer sb = new StringBuffer("\r\n");
//		sb.append("query_name="+query_name+"\r\n");
//		sb.append("query_cid="+query_cid+"\r\n");
//		sb.append("query_mobile="+query_mobile+"\r\n");
		sb.append("query_qq="+query_qq+"\r\n");
//		sb.append("query_email="+query_email+"\r\n");
//		sb.append("address="+address+"\r\n");		
//		sb.append("home_address="+home_address+"\r\n");
//		sb.append("work_address="+work_address+"\r\n");
//		sb.append("contact_name="+contact_name+"\r\n");
//		sb.append("contact_mobile="+contact_mobile+"\r\n");
//		sb.append("contact_relationship="+contact_relationship+"\r\n");
//		sb.append("car_num="+car_num+"\r\n");
//		sb.append("company_name="+company_name+"\r\n");
		return sb.toString();
	}

	public String getQuery_qq() {
		return query_qq;
	}

	public void setQuery_qq(String query_qq) {
		this.query_qq = query_qq;
	}
	 
}
