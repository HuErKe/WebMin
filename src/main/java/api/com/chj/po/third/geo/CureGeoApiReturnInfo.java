package api.com.chj.po.third.geo;

import api.com.chj.core.StringUtil;

public class CureGeoApiReturnInfo {
	private String query_id = StringUtil.BLANCK_STRING;
//	private String query_cer_id = StringUtil.BLANCK_STRING;
//	private String query_name = StringUtil.BLANCK_STRING;
//	private String query_mobile = StringUtil.BLANCK_STRING;
//	private String query_contact_mobile = StringUtil.BLANCK_STRING;
//	private String query_month = StringUtil.BLANCK_STRING;
//	private String query_work_address = StringUtil.BLANCK_STRING;
//	private String query_home_address = StringUtil.BLANCK_STRING;
	private String main_code = StringUtil.BLANCK_STRING;
	private String main_msg = StringUtil.BLANCK_STRING;
	private String province = StringUtil.BLANCK_STRING;
	private String city = StringUtil.BLANCK_STRING;
	private String isp = StringUtil.BLANCK_STRING;
	private String ift_code = StringUtil.BLANCK_STRING;
	private String ecl_code = StringUtil.BLANCK_STRING;
	private String rs_code = StringUtil.BLANCK_STRING;
	private String rs_desc = StringUtil.BLANCK_STRING;
	private String update_time = StringUtil.BLANCK_STRING;
	private int data_source = 0;
	private int valid_status = 0;
	private String ref_query_id = StringUtil.BLANCK_STRING;
	
	public CureGeoApiReturnInfo clone(){
		CureGeoApiReturnInfo newObj = new CureGeoApiReturnInfo();
		newObj.setQuery_id(this.query_id);
//		newObj.setQuery_cer_id(this.query_cer_id);
//		newObj.setQuery_name(this.query_name);
//		newObj.setQuery_mobile(this.query_mobile);
//		newObj.setQuery_contact_mobile(this.query_contact_mobile);
//		newObj.setQuery_month(this.query_month);
//		newObj.setQuery_work_address(this.query_work_address);
//		newObj.setQuery_home_address(this.query_home_address);
		newObj.setMain_code(this.main_code);
		newObj.setMain_msg(this.main_msg);
		newObj.setProvince(this.province);
		newObj.setCity(this.city);
		newObj.setIsp(this.isp);
		newObj.setIft_code(this.ift_code);
		newObj.setEcl_code(this.ecl_code);
		newObj.setRs_code(this.rs_code);
		newObj.setRs_desc(this.rs_desc);
		newObj.setUpdate_time(this.update_time);
		newObj.setData_source(this.data_source);
		newObj.setValid_status(this.valid_status);
		newObj.setRef_query_id(this.ref_query_id);
		return newObj;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("query_id="+query_id+"\r\n") ;
//		sb.append("query_cer_id="+query_cer_id+"\r\n") ;
//		sb.append("query_name="+query_name+"\r\n") ;
//		sb.append("query_mobile="+query_mobile+"\r\n") ;
//		sb.append("query_contact_mobile="+query_contact_mobile+"\r\n") ;
//		sb.append("query_month="+query_month+"\r\n") ;
//		sb.append("query_work_address="+query_work_address+"\r\n") ;
//		sb.append("query_home_address="+query_home_address+"\r\n") ;
		sb.append("main_code="+main_code+"\r\n") ;
		sb.append("main_msg="+main_msg+"\r\n") ;
		sb.append("province="+province+"\r\n") ;
		sb.append("city="+city+"\r\n") ;
		sb.append("isp="+isp+"\r\n") ;
		sb.append("ift_code="+ift_code+"\r\n") ;
		sb.append("ecl_code="+ecl_code+"\r\n") ;
		sb.append("rs_code="+rs_code+"\r\n") ;
		sb.append("rs_desc="+rs_desc+"\r\n") ;
		sb.append("update_time="+update_time+"\r\n") ;
		sb.append("data_source="+data_source+"\r\n") ;
		sb.append("valid_status="+valid_status+"\r\n") ;
		sb.append("ref_query_id="+ref_query_id+"\r\n") ;
		return sb.toString();
	}

	public String getMain_code() {
		return main_code;
	}

	public void setMain_code(String main_code) {
		this.main_code = main_code;
	}

	public String getMain_msg() {
		return main_msg;
	}

	public void setMain_msg(String main_msg) {
		this.main_msg = main_msg;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getIft_code() {
		return ift_code;
	}

	public void setIft_code(String ift_code) {
		this.ift_code = ift_code;
	}

	public String getEcl_code() {
		return ecl_code;
	}

	public void setEcl_code(String ecl_code) {
		this.ecl_code = ecl_code;
	}

	public String getRs_code() {
		return rs_code;
	}

	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
	}

	public String getRs_desc() {
		return rs_desc;
	}

	public void setRs_desc(String rs_desc) {
		this.rs_desc = rs_desc;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public int getData_source() {
		return data_source;
	}

	public void setData_source(int data_source) {
		this.data_source = data_source;
	}

	public int getValid_status() {
		return valid_status;
	}

	public void setValid_status(int valid_status) {
		this.valid_status = valid_status;
	}

	public String getRef_query_id() {
		return ref_query_id;
	}

	public void setRef_query_id(String ref_query_id) {
		this.ref_query_id = ref_query_id;
	}

	public String getQuery_id() {
		return query_id;
	}

	public void setQuery_id(String query_id) {
		this.query_id = query_id;
	}


	
}
