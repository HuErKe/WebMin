package api.com.chj.exception;

public class ResponseErrorCodeUtil {

	/**
	 * 用户名或密码错误
	 */
	public static int RESPONSE_CODE_VERYFYKEY_INVALID = 201;
	/**
	 * 输入不能为空
	 */
	public static int RESPONSE_CODE_FIELD_EMPTY = 202;
	
	/**
	 * 会话时限已过
	 */
	public static int RESPONSE_CODE_SESSION_TIME_OUT = 1003;
	
	public static int RESPONSE_CODE_FIELD_FORMAT_INVALID = 1001;
	
	/**
	 * 服务端内部错误
	 */
	public static int RESPONSE_CODE_INNER_ERROR = 1002;
	
	public static int RESPONSE_CODE_ERROR_URL_PATH = 9999;
	
	public static int RESPONSE_CODE_OTHER_ERROR = 301;
}
