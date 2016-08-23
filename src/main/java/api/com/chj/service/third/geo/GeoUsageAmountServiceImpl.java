package api.com.chj.service.third.geo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import api.com.chj.core.CollectionUtil;
import api.com.chj.core.StringUtil;
import api.com.chj.core.third.ThirdSpecificUtil;
import api.com.chj.po.third.UsageAmountPerDayPerIfcd;
import api.com.chj.po.third.UsageAmountSingleIfcdMultiDaysChart;
import api.com.chj.po.third.UsageAmountSingleIfcdSingleDayChart;
import api.com.chj.po.third.geo.CureGeoApiReturnInfo;
import api.com.chj.service.third.UsageAmountService;

@Service
public class GeoUsageAmountServiceImpl implements UsageAmountService<CureGeoApiReturnInfo> {

	private static final Logger logger = Logger.getLogger(GeoUsageAmountServiceImpl.class);


	@Override
	public void showUsageOfEachIfcdMultiDaysChart(Map<String,List<UsageAmountPerDayPerIfcd>> usageDetailMap) throws Exception { 
		
//		Map<String,List<UsageAmountPerDayPerIfcd>> usagePerdayMap = 
//				this.getUsageOfEachIfcdMultiDays(lspr);
		
		
		List<UsageAmountSingleIfcdMultiDaysChart> usageList = 
				this.getUsageAmountChart(usageDetailMap);
			 
		if(CollectionUtil.isEmpty(usageList)){
			return ;
		} 
		
		StringBuilder sb = new StringBuilder("\r\nifCods\t\tqueryAmount\thitAmount\tunhitAmount\thitRate\t\tpriceUnit\tpriceBalance\r\n");
		UsageAmountSingleIfcdMultiDaysChart item = null;
		for(int i=0;i<usageList.size();i++){
			item = usageList.get(i);
			sb.append(item.getIfCods()+"\t\t") ;  
			sb.append(item.getQueryAmount()+"\t\t") ;  
			sb.append(item.getHitAmount()+"\t\t") ;  
			sb.append(item.getUnhitAmount()+"\t\t") ;  
			sb.append(String.format("%.2f",item.getHitRate())+"\t\t") ;  
			sb.append(String.format("%.2f",item.getPriceUnit())+"\t\t");  
			sb.append(String.format("%.2f",item.getPriceBalance())+"\r\n") ; 			
		}
		

		logger.info(sb.toString());
	}
	
	@Override
	public void showUsageAmountPerDayPerIfcd(Map<String, List<UsageAmountPerDayPerIfcd>> usageMap) throws Exception { 
		if(null == usageMap || usageMap.isEmpty()){
			return;
		}
		StringBuilder sb = new StringBuilder("\t\nifCode\t\tdate\t\tqueryId\t\t\thit\t\trsCode\t\trsDes\r\n");
		Iterator<Map.Entry<String, List<UsageAmountPerDayPerIfcd>>> it = usageMap.entrySet().iterator();
		Map.Entry<String, List<UsageAmountPerDayPerIfcd>> entry = null; 
		List<UsageAmountPerDayPerIfcd> itSrc = null; 
		UsageAmountPerDayPerIfcd item = null;
		while(it.hasNext()){
			entry = it.next();  
			itSrc = entry.getValue();
			for(int i=0;i<itSrc.size();i++){
				item= itSrc.get(i);
				sb.append(item.getIfCode()+"\t\t");
				sb.append(item.getDate()+"\t");
				sb.append(item.getQueryId()+"\t\t");
				if(item.isHit()){
					sb.append("命中\t\t");					
				}
				else{
					sb.append("未命中\t\t");
				} 
				if(!StringUtil.emptyString(item.getIfRsDesc())){
					sb.append(item.getIfRsCode()+"\t\t");
				}
				else{
					sb.append(item.getIfRsCode()+"\t");					
				}
				sb.append(item.getIfRsDesc()+"\r\n");
			}
		}
		logger.info(sb.toString());
	}
 
	private List<UsageAmountSingleIfcdMultiDaysChart> getUsageAmountChart(Map<String, List<UsageAmountPerDayPerIfcd>> usageMap) throws Exception {
		// TODO Auto-generated method stub
		if(null == usageMap || usageMap.isEmpty()){
			return null;
		}
		
		List<UsageAmountSingleIfcdMultiDaysChart> result = new ArrayList<UsageAmountSingleIfcdMultiDaysChart>();
		
		Iterator<Map.Entry<String, List<UsageAmountPerDayPerIfcd>>> it = usageMap.entrySet().iterator();
		Map.Entry<String, List<UsageAmountPerDayPerIfcd>> entry = null;
		UsageAmountSingleIfcdMultiDaysChart itemDest = null;
		List<UsageAmountPerDayPerIfcd> itSrc = null;
		int usage = 0;
		while(it.hasNext()){
			entry = it.next();
			itemDest = new UsageAmountSingleIfcdMultiDaysChart();
			result.add(itemDest);
			itemDest.setIfCods(entry.getKey());
			itSrc = entry.getValue();
			itemDest.setQueryAmount(itSrc.size());
			usage = 0;
			for(int i=0;i<itSrc.size();i++){
				if(itSrc.get(i).isHit()){
					usage++;
				}
			}
			itemDest.setHitAmount(usage);
			itemDest.setUnhitAmount(itSrc.size() - usage);
			itemDest.setHitRate(usage * 1.0 / itSrc.size());
			itemDest.setPriceUnit(ThirdSpecificUtil.PRICE_UNIT_GEO);
			itemDest.setPriceBalance(ThirdSpecificUtil.PRICE_UNIT_GEO * itSrc.size());
		}
		return result;
	}
	
	private Map<String, List<UsageAmountPerDayPerIfcd>> getUsageOfEachIfcdEachDay(List<CureGeoApiReturnInfo> lspr)
			throws Exception {
		// TODO Auto-generated method stub
		if(CollectionUtil.isEmpty(lspr)){
			return null;
		}
		 Map<String,List<UsageAmountPerDayPerIfcd>> result = new  HashMap<String,List<UsageAmountPerDayPerIfcd>>();
		CureGeoApiReturnInfo itSrc = null;
		UsageAmountPerDayPerIfcd itDes = null;
		List<UsageAmountPerDayPerIfcd> mapItem = null;
		for(int i=0;i<lspr.size();i++){
			itSrc = lspr.get(i);
			itDes = new UsageAmountPerDayPerIfcd();
			mapItem = result.get(itDes.getDate()+itSrc.getIft_code());
			if(CollectionUtil.isEmpty(mapItem)){
				mapItem = new ArrayList<UsageAmountPerDayPerIfcd>();
				result.put(itDes.getDate()+itSrc.getIft_code(), mapItem);
			}
			mapItem.add(itDes);
			itDes.setIfCode(itSrc.getIft_code());
			itDes.setDate(itSrc.getQuery_id().substring(0, 8));
			itDes.setQueryId(itSrc.getQuery_id());
			if(StringUtil.emptyString(itSrc.getEcl_code())){
				itDes.setIfRsCode(itSrc.getRs_code());
				itDes.setIfRsDesc(itSrc.getRs_desc());	
				itDes.setHit(true);
			}
			else{
				itDes.setIfRsCode(itSrc.getEcl_code());				
			}
		}
		return result;
	}


	
	@Override
	public Map<String,List<UsageAmountPerDayPerIfcd>> getUsageOfEachIfcdMultiDays(List<CureGeoApiReturnInfo> lspr) throws Exception {
		if(CollectionUtil.isEmpty(lspr)){
			return null;
		}
		 Map<String,List<UsageAmountPerDayPerIfcd>> result = new  HashMap<String,List<UsageAmountPerDayPerIfcd>>();
		CureGeoApiReturnInfo itSrc = null;
		UsageAmountPerDayPerIfcd itDes = null;
		List<UsageAmountPerDayPerIfcd> mapItem = null;
		for(int i=0;i<lspr.size();i++){
			itSrc = lspr.get(i);
			itDes = new UsageAmountPerDayPerIfcd();
			mapItem = result.get(itSrc.getIft_code());
			if(CollectionUtil.isEmpty(mapItem)){
				mapItem = new ArrayList<UsageAmountPerDayPerIfcd>();
				result.put(itSrc.getIft_code(), mapItem);
			}
			mapItem.add(itDes);
			itDes.setIfCode(itSrc.getIft_code());
			itDes.setDate(itSrc.getQuery_id().substring(0, 8));
			itDes.setQueryId(itSrc.getQuery_id());
			if(StringUtil.emptyString(itSrc.getEcl_code())){
				itDes.setIfRsCode(itSrc.getRs_code());
				itDes.setIfRsDesc(itSrc.getRs_desc());	
				itDes.setHit(true);
			}
			else{
				itDes.setIfRsCode(itSrc.getEcl_code());				
			}
		}
		return result;
	}

	@Override
	public void showUsageOfEachIfcdEachDayChart(Map<String,List<UsageAmountPerDayPerIfcd>> usageDetailMap)
			throws Exception { 
		
		List<UsageAmountSingleIfcdSingleDayChart> echdayList = 
				this.getUsageAmountSingleIfcdSingleDayChart(usageDetailMap);
		
		if(CollectionUtil.isEmpty(echdayList)){
			return ;
		} 
		
		StringBuilder sb = new StringBuilder("\r\ndate\t\tifCods\t\tqueryAmount\thitAmount\tunhitAmount\thitRate\t\tpriceUnit\tpriceBalance\r\n");
		UsageAmountSingleIfcdSingleDayChart item = null;
		for(int i=0;i<echdayList.size();i++){
			item = echdayList.get(i);
			sb.append(item.getDate()+"\t\t") ;
			sb.append(item.getIfCods()+"\t\t") ;  
			sb.append(item.getQueryAmount()+"\t\t") ;  
			sb.append(item.getHitAmount()+"\t\t") ;  
			sb.append(item.getUnhitAmount()+"\t\t") ;  
			sb.append(String.format("%.2f",item.getHitRate())+"\t\t") ;  
			sb.append(String.format("%.2f",item.getPriceUnit())+"\t\t");  
			sb.append(String.format("%.2f",item.getPriceBalance())+"\r\n") ; 			
		}
		

		logger.info(sb.toString());
	}
 
	private List<UsageAmountSingleIfcdSingleDayChart> getUsageAmountSingleIfcdSingleDayChart(
			Map<String, List<UsageAmountPerDayPerIfcd>> eachDayMap) throws Exception { 
		if(null == eachDayMap || eachDayMap.isEmpty()){
			return null;
		}
		
		List<UsageAmountSingleIfcdSingleDayChart> result = new ArrayList<UsageAmountSingleIfcdSingleDayChart>();
		
		Iterator<Map.Entry<String, List<UsageAmountPerDayPerIfcd>>> it = eachDayMap.entrySet().iterator();
		Map.Entry<String, List<UsageAmountPerDayPerIfcd>> entry = null;
		UsageAmountSingleIfcdSingleDayChart itemDest = null;
		List<UsageAmountPerDayPerIfcd> itSrc = null;
		int usage = 0;
		String dtifcd = null;
		while(it.hasNext()){
			entry = it.next();
			itemDest = new UsageAmountSingleIfcdSingleDayChart();
			result.add(itemDest);
			dtifcd = entry.getKey();
			itemDest.setDate(dtifcd.substring(0,8));
			itemDest.setIfCods(dtifcd.substring(8));
			itSrc = entry.getValue();
			itemDest.setQueryAmount(itSrc.size());
			usage = 0;
			for(int i=0;i<itSrc.size();i++){
				if(itSrc.get(i).isHit()){
					usage++;
				}
			}
			itemDest.setHitAmount(usage);
			itemDest.setUnhitAmount(itSrc.size() - usage);
			itemDest.setHitRate(usage * 1.0 / itSrc.size());
			itemDest.setPriceUnit(ThirdSpecificUtil.PRICE_UNIT_GEO);
			itemDest.setPriceBalance(ThirdSpecificUtil.PRICE_UNIT_GEO * itSrc.size());
		}
		return result;
	}

}
