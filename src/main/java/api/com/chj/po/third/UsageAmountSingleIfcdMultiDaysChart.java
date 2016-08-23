package api.com.chj.po.third;

import api.huiyu.client.core.util.StringUtil;

/**
 * 每个接口在某个时间段内使用量, 即累计每天使用量
 * @author Chen Huajun
 *
 */
public class UsageAmountSingleIfcdMultiDaysChart {

	private String ifCods		= StringUtil.BLANCK_STRING;
	private int queryAmount		= 0;
	private int hitAmount		= 0;
	private int unhitAmount		= 0;
	private double hitRate		= 0.0;
	private double priceUnit	= 1.0; // 
	private double priceBalance	= 0.0;
	
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("ifCods="+ifCods+"\r\n") ;  
		sb.append("queryAmount="+queryAmount+"\r\n") ;  
		sb.append("hitAmount="+hitAmount+"\r\n") ;  
		sb.append("unhitAmount="+unhitAmount+"\r\n") ;  
		sb.append("hitRate="+hitRate+"\r\n") ;  
		sb.append("priceUnit="+priceUnit+"\r\n") ;  
		sb.append("priceBalance="+priceBalance+"\r\n") ;  
//		sb.append(ifCods+"\t\t") ;  
//		sb.append(queryAmount+"\t\t") ;  
//		sb.append(hitAmount+"\t\t") ;  
//		sb.append(unhitAmount+"\t\t") ;  
//		sb.append(String.format("%.2f",hitRate)+"\t\t") ;  
//		sb.append(String.format("%.2f",priceUnit)+"\t\t") ;  
//		sb.append(String.format("%.2f",priceBalance)+"\r\n") ;  
		return sb.toString();
	}

	public String getIfCods() {
		return ifCods;
	}

	public void setIfCods(String ifCods) {
		this.ifCods = ifCods;
	}

	public int getQueryAmount() {
		return queryAmount;
	}

	public void setQueryAmount(int queryAmount) {
		this.queryAmount = queryAmount;
	}

	public int getHitAmount() {
		return hitAmount;
	}

	public void setHitAmount(int hitAmount) {
		this.hitAmount = hitAmount;
	}

	public int getUnhitAmount() {
		return unhitAmount;
	}

	public void setUnhitAmount(int unhitAmount) {
		this.unhitAmount = unhitAmount;
	}

	public double getHitRate() {
		return hitRate;
	}

	public void setHitRate(double hitRate) {
		this.hitRate = hitRate;
	}

	public double getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(double priceUnit) {
		this.priceUnit = priceUnit;
	}

	public double getPriceBalance() {
		return priceBalance;
	}

	public void setPriceBalance(double priceBalance) {
		this.priceBalance = priceBalance;
	}
	
	

}
