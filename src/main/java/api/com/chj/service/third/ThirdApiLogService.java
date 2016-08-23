package api.com.chj.service.third;

import java.util.List;
import java.util.Map;

import api.com.chj.po.third.ThirdApiLog;

public interface ThirdApiLogService {

	List<ThirdApiLog> getThirdApiLogs(Map<String,Object> params) throws Exception;
	
	void saveThirdApiLog(List<ThirdApiLog> ltal) throws Exception ;
	
	boolean saveThirdApiLog(ThirdApiLog tal) throws Exception;
}
