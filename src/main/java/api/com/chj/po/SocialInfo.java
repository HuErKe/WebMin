package api.com.chj.po;

import api.com.chj.core.StringUtil;

public class SocialInfo {

	private long id;
	private long qq;
	private String social_nodes 		= StringUtil.BLANCK_STRING;
	private String social_edges			= StringUtil.BLANCK_STRING;
	private String chum_nodes			= StringUtil.BLANCK_STRING;
	private String chum_edges			= StringUtil.BLANCK_STRING;
	private String datetime				= StringUtil.BLANCK_STRING;
	
	public String toString(){
		StringBuffer sb = new StringBuffer(""); 
		sb.append("id="+id+"\r\n");
		sb.append("qq="+qq+"\r\n");
		sb.append("social_nodes="+social_nodes+"\r\n");		
		sb.append("social_edges="+social_edges+"\r\n");
		sb.append("chum_nodes="+chum_nodes+"\r\n");
		sb.append("chum_edges="+chum_edges+"\r\n");	
		sb.append("datetime="+datetime+"\r\n");			
		return sb.toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getQq() {
		return qq;
	}

	public void setQq(long qq) {
		this.qq = qq;
	}

	public String getSocial_edges() {
		return social_edges;
	}

	public void setSocial_edges(String social_edges) {
		this.social_edges = social_edges;
	}

	public String getChum_nodes() {
		return chum_nodes;
	}

	public void setChum_nodes(String chum_nodes) {
		this.chum_nodes = chum_nodes;
	}

	public String getChum_edges() {
		return chum_edges;
	}

	public void setChum_edges(String chum_edges) {
		this.chum_edges = chum_edges;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getSocial_nodes() {
		return social_nodes;
	}

	public void setSocial_nodes(String social_nodes) {
		this.social_nodes = social_nodes;
	}
	
	
}
