package api.com.chj.po;

import api.com.chj.core.StringUtil;

public class PersonalSocialDataNode {

//	private int id;
	private String name = StringUtil.BLANCK_STRING;
	private String group = StringUtil.BLANCK_STRING;
	private int radius;
	private int index ;
	
	public String toString(){
		StringBuffer sb = new StringBuffer(""); 
//		sb.append("id="+id+"\r\n");
		sb.append("name="+name+"\r\n");
		sb.append("group="+group+"\r\n");
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
 
	
	
}
