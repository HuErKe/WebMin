package api.com.chj.service.third.geo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import api.com.chj.core.CollectionUtil;
import api.com.chj.core.StringUtil;
import api.com.chj.core.third.ThirdSpecificUtil;
import api.com.chj.po.third.UsageAmountPerDayPerIfcd;
import api.com.chj.po.third.UsageAmountSingleIfcdMultiDaysChart;
import api.com.chj.po.third.UsageAmountSingleIfcdSingleDayChart;
import api.com.chj.po.third.geo.CureGeoApiReturnInfo;

public class UsageAmountHelperGeo{


	private static final Logger logger  = Logger.getLogger(UsageAmountHelperGeo.class);
	
	private List<UsageAmountPerDayPerIfcd> lsPerDayPerIfcd = new ArrayList<UsageAmountPerDayPerIfcd>();
	private Map<String,List<UsageAmountPerDayPerIfcd>> mpEachIfcdMultiDays = new HashMap<String,List<UsageAmountPerDayPerIfcd>>();
	private Map<String,List<UsageAmountPerDayPerIfcd>> mpEachIfcdEachDay = new HashMap<String,List<UsageAmountPerDayPerIfcd>>();
	
	private List<UsageAmountSingleIfcdSingleDayChart> lsEachIfcdEachDay = new ArrayList<UsageAmountSingleIfcdSingleDayChart>();
	private List<UsageAmountSingleIfcdMultiDaysChart> lsEachIfcdMultiDays = new ArrayList<UsageAmountSingleIfcdMultiDaysChart>();
	
	public UsageAmountHelperGeo(){
		
	}
	
	public UsageAmountHelperGeo(List<CureGeoApiReturnInfo> lspr){
		if(CollectionUtil.isEmpty(lspr)){
			 return ;
		}
 
		CureGeoApiReturnInfo itSrc = null;
		UsageAmountPerDayPerIfcd itDes = null;
		List<UsageAmountPerDayPerIfcd> mapLsItem = null;
		String key = null;
		for(int i=0;i<lspr.size();i++){
			itSrc = lspr.get(i);
			itDes = new UsageAmountPerDayPerIfcd();
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
			
			// lsPerDayPerIfcd
			lsPerDayPerIfcd.add(itDes);
			
			// mpEachIfcdMultiDays
			key = itDes.getIfCode();
			mapLsItem = mpEachIfcdMultiDays.get(key);
			if(CollectionUtil.isEmpty(mapLsItem)){
				mapLsItem = new ArrayList<UsageAmountPerDayPerIfcd>();
				mpEachIfcdMultiDays.put(itDes.getIfCode(), mapLsItem);
			}
			mapLsItem.add(itDes);
			
			// mpEachIfcdEachDay
			key = itDes.getDate()+itDes.getIfCode();
			mapLsItem = mpEachIfcdEachDay.get(key);
			if(CollectionUtil.isEmpty(mapLsItem)){
				mapLsItem = new ArrayList<UsageAmountPerDayPerIfcd>();
				mpEachIfcdEachDay.put(key, mapLsItem);
			}
			mapLsItem.add(itDes);			
		} 
		
		if(!CollectionUtil.isEmpty(lsPerDayPerIfcd)){
			Collections.sort(lsPerDayPerIfcd, 
					new Comparator<UsageAmountPerDayPerIfcd>() {

				@Override
				public int compare(UsageAmountPerDayPerIfcd o1, UsageAmountPerDayPerIfcd o2) {
					// TODO Auto-generated method stub
					return (o1.getQueryId()+o1.getIfCode()).compareTo(o2.getQueryId()+o2.getIfCode());
				}
				
			}); 
		}
		
		////////////

		Iterator<Map.Entry<String, List<UsageAmountPerDayPerIfcd>>> it = null;
		Map.Entry<String, List<UsageAmountPerDayPerIfcd>> entry = null;
		List<UsageAmountPerDayPerIfcd> itSrc2 = null;
		if(!mpEachIfcdEachDay.isEmpty()){			 
			it= mpEachIfcdEachDay.entrySet().iterator();			
			UsageAmountSingleIfcdSingleDayChart itemDest = null;
			int usage = 0;
			String dtifcd = null;
			while(it.hasNext()){
				entry = it.next();
				itemDest = new UsageAmountSingleIfcdSingleDayChart();
				lsEachIfcdEachDay.add(itemDest);
				dtifcd = entry.getKey();
				itemDest.setDate(dtifcd.substring(0,8));
				itemDest.setIfCods(dtifcd.substring(8));
				itSrc2 = entry.getValue();
				itemDest.setQueryAmount(itSrc2.size());
				usage = 0;
				for(int i=0;i<itSrc2.size();i++){
					if(itSrc2.get(i).isHit()){
						usage++;
					}
				}
				itemDest.setHitAmount(usage);
				itemDest.setUnhitAmount(itSrc2.size() - usage);
				itemDest.setHitRate(usage * 1.0 / itSrc2.size());
				itemDest.setPriceUnit(ThirdSpecificUtil.PRICE_UNIT_GEO);
				itemDest.setPriceBalance(ThirdSpecificUtil.PRICE_UNIT_GEO * itSrc2.size());
			}
			if(!CollectionUtil.isEmpty(lsEachIfcdEachDay)){
				Collections.sort(lsEachIfcdEachDay, 
						new Comparator<UsageAmountSingleIfcdSingleDayChart>() {

					@Override
					public int compare(UsageAmountSingleIfcdSingleDayChart o1, UsageAmountSingleIfcdSingleDayChart o2) {
						// TODO Auto-generated method stub
						return (o1.getDate()+o1.getIfCods()).compareTo(o2.getDate()+o2.getIfCods());
					}
					
				}); 
			}
		}
		
		if(!mpEachIfcdMultiDays.isEmpty()){		
			UsageAmountSingleIfcdMultiDaysChart itemDest = null;		
			int usage = 0;
			it= mpEachIfcdMultiDays.entrySet().iterator();
			while(it.hasNext()){
				entry = it.next();
				itemDest = new UsageAmountSingleIfcdMultiDaysChart();
				lsEachIfcdMultiDays.add(itemDest);
				itemDest.setIfCods(entry.getKey());
				itSrc2 = entry.getValue();
				itemDest.setQueryAmount(itSrc2.size());
				usage = 0;
				for(int i=0;i<itSrc2.size();i++){
					if(itSrc2.get(i).isHit()){
						usage++;
					}
				}
				itemDest.setHitAmount(usage);
				itemDest.setUnhitAmount(itSrc2.size() - usage);
				itemDest.setHitRate(usage * 1.0 / itSrc2.size());
				itemDest.setPriceUnit(ThirdSpecificUtil.PRICE_UNIT_GEO);
				itemDest.setPriceBalance(ThirdSpecificUtil.PRICE_UNIT_GEO * itSrc2.size());
			} 
		}
		else{
			logger.info("mpEachIfcdMultiDays is empty");
		}
	}
	
	/**
	 * 20160811
	 * @throws Exception
	 */
	public UsageAmountHelperGeo showUsageOfEachIfcdMultiDaysChart() throws Exception { 
		List<UsageAmountSingleIfcdMultiDaysChart>  usageList =  this.lsEachIfcdMultiDays;
		if(CollectionUtil.isEmpty(usageList)){ 
			return this;
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
		
		return this;
	}
	
	public UsageAmountHelperGeo showUsageOfEachIfcdEachDayChart()
			throws Exception { 
		
		List<UsageAmountSingleIfcdSingleDayChart> echdayList = this.lsEachIfcdEachDay;
		
		if(CollectionUtil.isEmpty(echdayList)){
			return this;
		}  
		
		StringBuilder sb = new StringBuilder("\r\ndate\t\tifCods\t\tqueryAmount\thitAmount\tunhitAmount\thitRate\t\tpriceUnit\tpriceBalance\r\n");
		UsageAmountSingleIfcdSingleDayChart item = null;
		for(int i=0;i<echdayList.size();i++){
			item = echdayList.get(i);
			sb.append(item.getDate()+"\t") ;
			sb.append(item.getIfCods()+"\t\t") ;  
			sb.append(item.getQueryAmount()+"\t\t") ;  
			sb.append(item.getHitAmount()+"\t\t") ;  
			sb.append(item.getUnhitAmount()+"\t\t") ;  
			sb.append(String.format("%.2f",item.getHitRate())+"\t\t") ;  
			sb.append(String.format("%.2f",item.getPriceUnit())+"\t\t");  
			sb.append(String.format("%.2f",item.getPriceBalance())+"\r\n") ; 			
		} 

		logger.info(sb.toString());
		
		return this;
	}
	
	public UsageAmountHelperGeo showUsageOfEachIfcdEachItemChart() throws Exception { 
		
		List<UsageAmountPerDayPerIfcd> ls = this.lsPerDayPerIfcd;		
		if(CollectionUtil.isEmpty(ls)){
			return this;
		} 
		
		StringBuilder sb = new StringBuilder("\t\nifCode\t\tdate\t\tqueryId\t\t\thit\t\trsCode\t\trsDes\r\n"); 
		UsageAmountPerDayPerIfcd item = null;
		for(int i=0;i<ls.size();i++){
			item= ls.get(i);
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
		logger.info(sb.toString());
		
		return this;
	}
	

	public List<UsageAmountPerDayPerIfcd> getLsPerDayPerIfcd() {
		return lsPerDayPerIfcd;
	}

	public Map<String, List<UsageAmountPerDayPerIfcd>> getMpEachIfcdMultiDays() {
		return mpEachIfcdMultiDays;
	}

	public Map<String, List<UsageAmountPerDayPerIfcd>> getMpEachIfcdEachDay() {
		return mpEachIfcdEachDay;
	}

	public List<UsageAmountSingleIfcdSingleDayChart> getLsEachIfcdEachDay() {
		return lsEachIfcdEachDay;
	}

	public List<UsageAmountSingleIfcdMultiDaysChart> getLsEachIfcdMultiDays() {
		return lsEachIfcdMultiDays;
	}
}
