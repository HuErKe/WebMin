package api.com.chj.vo;

import api.com.chj.core.StringUtil;

public class ResponseResult { 
	
	private String query_name 			= StringUtil.BLANCK_STRING;
	private String query_cid 			= StringUtil.BLANCK_STRING;
	private String query_mobile 		= StringUtil.BLANCK_STRING;
	private String query_qq 			= StringUtil.BLANCK_STRING;
	private String query_email 			= StringUtil.BLANCK_STRING;
	private String address 				= StringUtil.BLANCK_STRING; // 20160629 Chen Huajun mod 
	private String home_address 		= StringUtil.BLANCK_STRING;
	private String work_address 		= StringUtil.BLANCK_STRING;
	private String contact_name 		= StringUtil.BLANCK_STRING;
	private String contact_mobile 		= StringUtil.BLANCK_STRING;
	private String contact_relationship = StringUtil.BLANCK_STRING;
	private String car_num 				= StringUtil.BLANCK_STRING;
	private String company_name 		= StringUtil.BLANCK_STRING;	 
	
	public String toString(){
		StringBuffer sb = new StringBuffer("\r\n");
		sb.append("query_name="+query_name+"\r\n");
		sb.append("query_cid="+query_cid+"\r\n");
		sb.append("query_mobile="+query_mobile+"\r\n");
		sb.append("query_qq="+query_qq+"\r\n");
		sb.append("query_email="+query_email+"\r\n");
		sb.append("address="+address+"\r\n");		
		sb.append("home_address="+home_address+"\r\n");
		sb.append("work_address="+work_address+"\r\n");
		sb.append("contact_name="+contact_name+"\r\n");
		sb.append("contact_mobile="+contact_mobile+"\r\n");
		sb.append("contact_relationship="+contact_relationship+"\r\n");
		sb.append("car_num="+car_num+"\r\n");
		sb.append("company_name="+company_name+"\r\n");
		return sb.toString();
	}
	
	public String getQuery_name() {
		return query_name;
	}
	public void setQuery_name(String query_name) {
		this.query_name = query_name;
	}
	public String getQuery_cid() {
		return query_cid;
	}
	public void setQuery_cid(String query_cid) {
		this.query_cid = query_cid;
	}
	public String getQuery_mobile() {
		return query_mobile;
	}
	public void setQuery_mobile(String query_mobile) {
		this.query_mobile = query_mobile;
	}
	public String getQuery_qq() {
		return query_qq;
	}
	public void setQuery_qq(String query_qq) {
		this.query_qq = query_qq;
	}
	public String getQuery_email() {
		return query_email;
	}
	public void setQuery_email(String query_email) {
		this.query_email = query_email;
	}
	public String getHome_address() {
		return home_address;
	}
	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}
	public String getWork_address() {
		return work_address;
	}
	public void setWork_address(String work_address) {
		this.work_address = work_address;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getContact_mobile() {
		return contact_mobile;
	}
	public void setContact_mobile(String contact_mobile) {
		this.contact_mobile = contact_mobile;
	}
	public String getContact_relationship() {
		return contact_relationship;
	}
	public void setContact_relationship(String contact_relationship) {
		this.contact_relationship = contact_relationship;
	}
	public String getCar_num() {
		return car_num;
	}
	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	} 
}
