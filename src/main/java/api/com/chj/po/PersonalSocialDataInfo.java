package api.com.chj.po;

import api.com.chj.core.StringUtil;

public class PersonalSocialDataInfo {

	private long id;
	private String qq = StringUtil.BLANCK_STRING;
	private String nodes = StringUtil.BLANCK_STRING;
	private String edges = StringUtil.BLANCK_STRING;
	

	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("id="+id+"\r\n");
		sb.append("qq="+qq+"\r\n");
		sb.append("nodes="+nodes+"\r\n");
		sb.append("edges="+edges+"\r\n");
		return sb.toString();
	}

	public PersonalSocialDataInfo clone(){
		PersonalSocialDataInfo newObj = new PersonalSocialDataInfo();
		newObj.setId(id);
		newObj.setQq(qq);
		newObj.setNodes(nodes);
		newObj.setEdges(edges);
		return newObj;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	public String getEdges() {
		return edges;
	}

	public void setEdges(String edges) {
		this.edges = edges;
	}
 
	
	
}
