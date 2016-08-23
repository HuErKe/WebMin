package api.com.chj.service.third;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.com.chj.core.CollectionUtil;
import api.com.chj.core.DateUtil;
import api.com.chj.core.third.ThirdSpecificUtil;
import api.com.chj.mapper.ThirdApiLogMapper;
import api.com.chj.po.third.ThirdApiLog;
import api.huiyu.client.core.util.StringUtil;

@Service
public class ThirdApiLogServiceImpl implements ThirdApiLogService{

	private ThirdApiLogMapper thirdApiLogMapper;
	private static final Logger logger = Logger.getLogger(ThirdApiLogServiceImpl.class);
	
//	private static final int QUERY_ID_MINUS_DATE_LEN = 5;
	private static final String QUERY_ID_MINUS_DATE_STR = "00000"; 
	
	@Override
	public List<ThirdApiLog> getThirdApiLogs(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> realParams = new HashMap<String,Object>();
		String strTem = null;
		strTem = (String)params.get("beginDate");
		String beginDate = StringUtil.emptyString(strTem) ? DateUtil.getTodayNoSep() : strTem;
		strTem = (String)params.get("endDate");
		String endDate = StringUtil.emptyString(strTem) ? DateUtil.getTodayNoSep() : strTem;
		
		strTem = (String)params.get(ThirdSpecificUtil.KEY_THIRD_DATASOURCE);
		if(StringUtil.emptyString(strTem)){
			throw new IllegalArgumentException("third_data_source can not be null");
		}
		
		String baseUrl = null;
		String baseDS = null;
		if(ThirdSpecificUtil.ThirdDataSource.THIRD_DATASOURCE_GEO.equals(strTem)){
			baseUrl = "http://yz.geotmt.com:80";
			baseDS = "API_GEO";
		}
		else{
			logger.info("其他数据源暂不支持");
			return null;
		} 	
		
		realParams.put("queryIdBegin", beginDate + QUERY_ID_MINUS_DATE_STR);
		realParams.put("queryIdEnd", endDate + QUERY_ID_MINUS_DATE_STR);
		realParams.put("baseUrl", baseUrl);
		realParams.put("baseDS", baseDS);
		
		List<ThirdApiLog> result = 
			this.thirdApiLogMapper.getThirdApiLogs(realParams);
		return result;
	}	
	
	/**
	 * 增加批量处理功能
	 * @date 20160710
	 */
	@Transactional
	@Override
	public void saveThirdApiLog(List<ThirdApiLog> ltal) throws Exception {
		if(CollectionUtil.isEmpty(ltal)){
			return ;
		}
		logger.info("ltal=\r\n"+ltal);
		this.thirdApiLogMapper.batchSaveThirdApiLogInList(ltal);
	}
	
	@Transactional
	@Override
	public boolean saveThirdApiLog(ThirdApiLog tal) throws Exception { // 20160710 Chen Huajun 增加lsBs参数
		// TODO Auto-generated method stub
		logger.info("保存日志");
		System.out.println("thirdApiLog:\r\n"+tal);
		this.thirdApiLogMapper.saveThirdApiLog(tal);
		return true;
	}

	@Resource
	public void setThirdApiLogMapper(ThirdApiLogMapper thirdApiLogMapper) {
		this.thirdApiLogMapper = thirdApiLogMapper;
	}

}
