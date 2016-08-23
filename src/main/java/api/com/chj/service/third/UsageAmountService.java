package api.com.chj.service.third;

import java.util.List;
import java.util.Map;

import api.com.chj.po.third.UsageAmountPerDayPerIfcd;

public interface UsageAmountService<ParseResult> {


	/**
	 * 20160810
	 * 接口每天使用情况, map形式
	 * @return
	 * @throws Exception
	 */
//	List<UsageAmountSingleIfcdSingleDayChart> getUsageAmountSingleIfcdSingleDayChart(
//			Map<String,List<UsageAmountPerDayPerIfcd>> eachDayMap) throws Exception;
	/**
	 * 20160810
	 * @param echdayList
	 * @throws Exception
	 */
	void showUsageOfEachIfcdEachDayChart(Map<String,List<UsageAmountPerDayPerIfcd>> usageDetailMap) throws Exception;  
	/**
	 * 20160810
	 * 接口每天使用情况, map形式
	 * @param lspr
	 * @return
	 * @throws Exception
	 */
//	Map<String,List<UsageAmountPerDayPerIfcd>> getUsageOfEachIfcdEachDay(List<ParseResult> lspr) throws Exception;  
	

	/**
	 * 
	 * 200160807
	 * 展示接口时间段内使用情况
	 * @param useAllDaysList
	 * @throws Exception
	 */
	void showUsageOfEachIfcdMultiDaysChart(Map<String,List<UsageAmountPerDayPerIfcd>> usageDetailMap) throws Exception; // 20160808
	/**
	 * 200160807
	 * 接口时间段内使用情况, list
	 * @param useAllDayMap
	 * @return
	 * @throws Exception
	 */
//	List<UsageAmountSingleIfcdMultiDaysChart> getUsageAmountChart(Map<String,List<UsageAmountPerDayPerIfcd>> useAllDayMap) throws Exception; // 20160807
	
		
	/**
	 * 
	 * 200160807
	 * ST->2
	 * 展示接口使用明细
	 * @param usageDetailMap
	 * @throws Exception
	 */
	void showUsageAmountPerDayPerIfcd(Map<String,List<UsageAmountPerDayPerIfcd>> usageDetailMap) throws Exception; // 20160808
	

	/**
	 * 200160807
	 * ST->1
	 * 接口时间段内使用情况, map
	 * @param lspr
	 * @return
	 * @throws Exception
	 */
	Map<String,List<UsageAmountPerDayPerIfcd>> getUsageOfEachIfcdMultiDays(List<ParseResult> lspr) throws Exception; // 20160807 
}
