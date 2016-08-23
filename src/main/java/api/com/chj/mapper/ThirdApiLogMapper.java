package api.com.chj.mapper;

import java.util.List;
import java.util.Map;

import api.com.chj.po.third.ThirdApiLog;

public interface ThirdApiLogMapper {

	List<ThirdApiLog> getThirdApiLogs(Map<String,Object> params);
	
	void saveThirdApiLog(ThirdApiLog tal);
	
	void batchSaveThirdApiLogInList(List<ThirdApiLog> ltal); // 20160710
}
