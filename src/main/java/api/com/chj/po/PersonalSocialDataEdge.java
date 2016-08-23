package api.com.chj.po;

import api.com.chj.core.StringUtil;

public class PersonalSocialDataEdge {
//	private long id;
	private int source;
	private int target;
	private int weight;
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
//		sb.append("id="+id+"\r\n");
		sb.append("source="+source+"\r\n");
		sb.append("target="+target+"\r\n");
		sb.append("weight="+weight+"\r\n");
		return sb.toString();
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
