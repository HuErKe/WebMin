package api.com.chj.po;

import api.com.chj.core.StringUtil;

public class User {

	private int user_id  ;
	private String user_login_name = StringUtil.EMPTY_STRING;
	private String user_password = StringUtil.EMPTY_STRING;
	private String user_name_disp = StringUtil.EMPTY_STRING;
	private int organization_id ;
	private String organization_name = StringUtil.EMPTY_STRING;
	private String user_create_date = StringUtil.EMPTY_STRING; // DateTime
	private String user_active_date = StringUtil.EMPTY_STRING; // DateTime
	private int user_status  ;
	private String memo = StringUtil.EMPTY_STRING;
	private int account_id ;
	private String product_set = StringUtil.EMPTY_STRING;
	private String business_set = StringUtil.EMPTY_STRING;
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("user_id="+user_id+"\r\n");
		sb.append("user_login_name="+user_login_name+"\r\n");
		sb.append("user_password="+user_password+"\r\n");
		sb.append("user_name_disp="+user_name_disp+"\r\n");
		sb.append("organization_id="+organization_id+"\r\n");
		sb.append("organization_name="+organization_name+"\r\n");
		sb.append("user_create_date="+user_create_date+"\r\n"); // DateTime
		sb.append("user_active_date="+user_active_date+"\r\n"); // DateTime
		sb.append("user_status="+user_status+"\r\n");
		sb.append("memo="+memo+"\r\n");
		sb.append("account_id="+account_id+"\r\n");
		sb.append("product_set="+product_set+"\r\n");
		sb.append("business_set="+business_set+"\r\n");
		return sb.toString();
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_login_name() {
		return user_login_name;
	}
	public void setUser_login_name(String user_login_name) {
		this.user_login_name = user_login_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name_disp() {
		return user_name_disp;
	}
	public void setUser_name_disp(String user_name_disp) {
		this.user_name_disp = user_name_disp;
	}
	public int getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(int organization_id) {
		this.organization_id = organization_id;
	}
	public String getOrganization_name() {
		return organization_name;
	}
	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	public String getUser_create_date() {
		return user_create_date;
	}
	public void setUser_create_date(String user_create_date) {
		this.user_create_date = user_create_date;
	}
	public String getUser_active_date() {
		return user_active_date;
	}
	public void setUser_active_date(String user_active_date) {
		this.user_active_date = user_active_date;
	}
	public int getUser_status() {
		return user_status;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getProduct_set() {
		return product_set;
	}
	public void setProduct_set(String product_set) {
		this.product_set = product_set;
	}
	public String getBusiness_set() {
		return business_set;
	}
	public void setBusiness_set(String business_set) {
		this.business_set = business_set;
	}

	
}
