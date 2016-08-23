package api.com.chj.po;

import api.com.chj.core.StringUtil;

public class LoginLog {

	private int login_id;
	private int user_id;
	private int login_type;
	private String login_time = StringUtil.BLANCK_STRING;
	private String login_client_ip = StringUtil.BLANCK_STRING;
	private String product_id = StringUtil.BLANCK_STRING;
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");  
		sb.append("login_id="+login_id+"\r\n");
		sb.append("user_id="+user_id+"\r\n");
		sb.append("login_type="+login_type+"\r\n");
		sb.append("login_time="+login_time+"\r\n");
		sb.append("login_client_ip="+login_client_ip+"\r\n");
		sb.append("product_id="+product_id+"\r\n");
		return sb.toString();
	}

	public int getLogin_id() {
		return login_id;
	}

	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getLogin_type() {
		return login_type;
	}

	public void setLogin_type(int login_type) {
		this.login_type = login_type;
	}

	public String getLogin_time() {
		return login_time;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	public String getLogin_client_ip() {
		return login_client_ip;
	}

	public void setLogin_client_ip(String login_client_ip) {
		this.login_client_ip = login_client_ip;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
	
}
