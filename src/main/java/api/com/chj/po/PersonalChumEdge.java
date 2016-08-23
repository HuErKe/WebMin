package api.com.chj.po;

public class PersonalChumEdge {


	private int source;
	private int target;
	private double weight;
	
	public String toString(){
		StringBuffer sb = new StringBuffer(""); 
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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
}
