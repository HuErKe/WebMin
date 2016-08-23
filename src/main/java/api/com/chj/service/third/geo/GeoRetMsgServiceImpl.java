package api.com.chj.service.third.geo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import api.com.chj.core.CollectionUtil;
import api.com.chj.core.StackTraceUtil;
import api.com.chj.core.StringUtil;
import api.com.chj.core.third.ThirdSpecificUtil;
import api.com.chj.po.third.ThirdApiLog;
import api.com.chj.po.third.geo.CureGeoApiReturnInfo;

@Service
public class GeoRetMsgServiceImpl implements GeoRetMsgService<CureGeoApiReturnInfo> {

	
	private static Logger logger = Logger.getLogger(GeoRetMsgServiceImpl.class);
	
	private List<CureGeoApiReturnInfo> doParseLog(ThirdApiLog tal){

		String text = tal.getResponse_text();
		
		if(StringUtil.emptyString(text)){
			return null;
		}
		
		JSONObject jsonObj = null;
		try {
			jsonObj = JSON.parseObject(text);
			String code =  jsonObj.getString("code"); 
			
			if(!"200".equals(code)) {
				return null;
			}
			
//			QueryCondition qc = garinfo.getQuery_condition();
  
			String main_msg = jsonObj.getString("msg"); 
			JSONObject data = jsonObj.getJSONObject("data"); 
			//ISPNUM  
			JSONObject ispnum = data.getJSONObject("ISPNUM");  
			String province = ispnum.getString("province");
			String city = ispnum.getString("city");
			String isp = ispnum.getString("isp"); 
			// RSL 
			JSONArray rsl = data.getJSONArray("RSL");  
			String ift_code = null;
			String rs_code = null;
			String rs_desc = null;
			JSONObject tem = null;
			JSONObject rs = null;		
			List<CureGeoApiReturnInfo> lgari = new ArrayList<CureGeoApiReturnInfo>();
			CureGeoApiReturnInfo gari = null;
			for(int i=0;i<rsl.size();i++){
				try { 
					tem = rsl.getJSONObject(i);
					ift_code = tem.getString("IFT"); 
					rs = tem.getJSONObject("RS"); 
					rs_code = rs.getString("code");
					rs_desc = rs.getString("desc");
					gari = new CureGeoApiReturnInfo();
					gari.setQuery_id(tal.getQuery_id());
//					gari.setQuery_cer_id(qc.getQuery_cid());
//					gari.setQuery_name(qc.getQuery_name());
//					gari.setQuery_mobile(qc.getQuery_mobile());
//					gari.setQuery_contact_mobile(qc.getContact_mobile());
//					gari.setQuery_month(qc.getQuery_month());
//					gari.setQuery_work_address(qc.getWork_address());
//					gari.setQuery_home_address(qc.getHome_address());
					gari.setMain_code(code);
					gari.setMain_msg(main_msg);
					gari.setProvince(province);
					gari.setCity(city);
					gari.setIsp(isp);
					gari.setIft_code(ift_code); 
					gari.setRs_code(rs_code);
					gari.setRs_desc(rs_desc);
					gari.setValid_status(1);
					lgari.add(gari); 					
				} catch (Exception e) { 
					logger.info(StackTraceUtil.getStackTrace(e));
					continue;
				}
			}
			//ECL 
			JSONArray ecl = data.getJSONArray("ECL");
			String ecl_code = null;
			for(int i=0;i<ecl.size();i++){
				try {
					tem = ecl.getJSONObject(i);
					ift_code = tem.getString("IFT");
					ecl_code = tem.getString("code"); 
					gari = new CureGeoApiReturnInfo();
					gari.setQuery_id(tal.getQuery_id());
//					gari.setQuery_cer_id(qc.getQuery_cid());
//					gari.setQuery_name(qc.getQuery_name());
//					gari.setQuery_mobile(qc.getQuery_mobile());
//					gari.setQuery_contact_mobile(qc.getContact_mobile());
//					gari.setQuery_month(qc.getQuery_month());
//					gari.setQuery_work_address(qc.getWork_address());
//					gari.setQuery_home_address(qc.getHome_address());
					gari.setMain_code(code);
					gari.setMain_msg(main_msg);
					gari.setProvince(province);
					gari.setCity(city);
					gari.setIsp(isp);
					gari.setIft_code(ift_code);
					gari.setEcl_code(ecl_code);
					if(ThirdSpecificUtil.isNormal(ThirdSpecificUtil.ThirdType.GEO,ecl_code)){
						gari.setValid_status(1);						
					}
					else{
						gari.setValid_status(0);  // 20160705 Chen Huajun mod						
					}
					lgari.add(gari); 					
				} catch (Exception e) { 
					logger.info(StackTraceUtil.getStackTrace(e));
					continue;
				}
			}			
			return lgari;
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(StackTraceUtil.getStackTrace(e));
			return null;
		}
	}
	
	@Override
	public void doParse(List<ThirdApiLog> ltal,List<CureGeoApiReturnInfo> infos) throws Exception { 
		
		if(CollectionUtil.isEmpty(ltal)){
			return ;
		} 
		
		List<CureGeoApiReturnInfo>  tem = null;
		for(int i=0;i<ltal.size();i++){
			tem = this.doParseLog(ltal.get(i));
			if(!CollectionUtil.isEmpty(tem)){
				infos.addAll(tem);
			}
		}
		
	}

}
