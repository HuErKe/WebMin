package api.com.chj.po;

import api.com.chj.core.StringUtil;

public class PersonalChumNode {
	
	private String name = StringUtil.BLANCK_STRING; 
//	private int radius; // 20160714 
	private double radius;
	private int index ;
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");  
		sb.append("name="+name+"\r\n"); 
		sb.append("radius="+radius+"\r\n");
		sb.append("index="+index+"\r\n");
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
