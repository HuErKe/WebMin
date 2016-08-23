package api.com.chj.exception;

public class ResponseException extends IllegalArgumentException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long errCode;
	private String errMsg;
	
	public ResponseException(int errCode,String errMsg){
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("[errCode="+errCode+"][errMsg="+errMsg+"]");
		return sb.toString();
	}
	
	public long getErrCode() { 
		return errCode;
	}
	public void setErrCode(long errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}	
	
}
