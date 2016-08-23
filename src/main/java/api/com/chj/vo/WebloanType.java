package api.com.chj.vo;

public class WebloanType {

	private int p2pNum; //P2P网贷
	private int xiaodai; // 小贷公司
	private int xiaofeifenqi; // 消费/分期平台
	private int qichejinrong; // 汽车金融平台
	private int qita; // 其他
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("p2pNum="+p2pNum+"\r\n");
		sb.append("xiaodai="+xiaodai+"\r\n");
		sb.append("xiaofeifenqi="+xiaofeifenqi+"\r\n");
		sb.append("qichejinrong="+qichejinrong+"\r\n");	
		sb.append("qita="+qita+"\r\n");  
		return sb.toString();
	}
	
	public int getP2pNum() {
		return p2pNum;
	}
	public void setP2pNum(int p2pNum) {
		this.p2pNum = p2pNum;
	}
	public int getXiaodai() {
		return xiaodai;
	}
	public void setXiaodai(int xiaodai) {
		this.xiaodai = xiaodai;
	}
	public int getXiaofeifenqi() {
		return xiaofeifenqi;
	}
	public void setXiaofeifenqi(int xiaofeifenqi) {
		this.xiaofeifenqi = xiaofeifenqi;
	}
	public int getQichejinrong() {
		return qichejinrong;
	}
	public void setQichejinrong(int qichejinrong) {
		this.qichejinrong = qichejinrong;
	}
	public int getQita() {
		return qita;
	}
	public void setQita(int qita) {
		this.qita = qita;
	}
	
	
}
