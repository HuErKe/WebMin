package api.com.chj.vo;

import java.util.ArrayList;
import java.util.List;

import api.com.chj.po.PersonalChumEdge;
import api.com.chj.po.PersonalChumNode;
import api.com.chj.po.PersonalSocialDataEdge;
import api.com.chj.po.PersonalSocialDataNode;

public class Qq2RelatedQq {

	private String qq;
//	private JSONArray nodes;
//	private JSONArray edges;
	private List<PersonalSocialDataNode> socialNodes = new ArrayList<PersonalSocialDataNode>();
	private List<PersonalSocialDataEdge> socialEdges = new ArrayList<PersonalSocialDataEdge>();
	private List<PersonalChumNode> chumNodes = new ArrayList<PersonalChumNode>();
	private List<PersonalChumEdge> chumEdges = new ArrayList<PersonalChumEdge>();
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("qq="+qq+"\r\n");
		sb.append("socialNodes=\r\n"+socialNodes+"\r\n");
		sb.append("socialEdges=\r\n"+socialEdges+"\r\n"); 
		sb.append("chumNodes=\r\n"+chumNodes+"\r\n");
		sb.append("chumEdges="+chumEdges+"\r\n"); 
		return sb.toString();
	}
	
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public List<PersonalSocialDataNode> getSocialNodes() {
		return socialNodes;
	}
	public void setSocialNodes(List<PersonalSocialDataNode> socialNodes) {
		this.socialNodes = socialNodes;
	}
	public List<PersonalSocialDataEdge> getSocialEdges() {
		return socialEdges;
	}
	public void setSocialEdges(List<PersonalSocialDataEdge> socialEdges) {
		this.socialEdges = socialEdges;
	}

	public List<PersonalChumNode> getChumNodes() {
		return chumNodes;
	}

	public void setChumNodes(List<PersonalChumNode> chumNodes) {
		this.chumNodes = chumNodes;
	}

	public List<PersonalChumEdge> getChumEdges() {
		return chumEdges;
	}

	public void setChumEdges(List<PersonalChumEdge> chumEdges) {
		this.chumEdges = chumEdges;
	}
	
	
}
