package api.com.chj.vo;

import api.com.chj.core.StringUtil;

public class WebloanSimple {

	private String wiskLevel = "未知";
	private int times;
	private String stage = StringUtil.BLANCK_STRING;
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("wiskLevel="+wiskLevel+"\r\n");
		sb.append("times="+times+"\r\n");
		sb.append("stage="+stage+"\r\n"); 
		return sb.toString();
	}
	
	public String getWiskLevel() {
		return wiskLevel;
	}
	public void setWiskLevel(String wiskLevel) {
		this.wiskLevel = wiskLevel;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	
}
