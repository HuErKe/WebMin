package api.com.chj.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import api.com.chj.core.DateUtil;
import api.com.chj.core.EncodeUtil;
import api.com.chj.core.SpringContextHolder;
import api.com.chj.core.StackTraceUtil;
import api.com.chj.core.StringUtil;
import api.com.chj.exception.ResponseException;
import api.com.chj.ops.ConsOptionSettings;
import api.com.chj.po.User;
import api.com.chj.vo.ClientRequestInfo;
import api.com.chj.vo.QuerySerial;
import api.com.chj.vo.WebloanSimple;
import api.com.chj.vo.WebloanType;
import api.huiyu.client.HuiYuRequestInfo;
import api.huiyu.client.HuiYuSimpleClient;

@Service
public class HuiYuDataServiceImpl implements HuiYuDataService {

	private static final Logger logger = Logger.getLogger(HuiYuDataServiceImpl.class);
	
	private User userRef = null;
	private ClientRequestInfo criRef = null;
	private String responseBodyRef = null;
	
	private void setWebloanType(WebloanType wt,JSONArray ja){
		JSONObject tem = null;
		int t = 0;
		for(int i=0;i<ja.size();i++){
			try {
				tem = ja.getJSONObject(i);
				t = Integer.parseInt(tem.getString("count"));
				if("其他".equals(tem.getString("class_name"))){
					 wt.setQita(t);
				}
				else if("P2P网贷".equals(tem.getString("class_name"))){
					 wt.setP2pNum(t);;
				}
				else if("消费/分期平台".equals(tem.getString("class_name"))){
					 wt.setXiaofeifenqi(t);;
				}
				else if("汽车金融平台".equals(tem.getString("class_name"))){
					 wt.setQichejinrong(t);;
				}
				else if("小贷公司".equals(tem.getString("class_name"))){
					 wt.setXiaodai(t);;
				}	
				else{
					logger.info("其他接口暂不支持");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				logger.info(StackTraceUtil.getStackTrace(e));
			}
		}
	}
	
	private JSONObject parse(String context) throws Exception{  
		
		JSONObject jsonRetInfo = null; 
		JSONObject result = new JSONObject();
 
		// webloan
		WebloanSimple ws = new WebloanSimple();
		result.put("webloan", ws);
		
		// webloanDetail
		JSONObject jsonWebloanDetail = new JSONObject();
		WebloanType oneMonth = new WebloanType(); 
		WebloanType oneSeason = new WebloanType();
		WebloanType halfYear = new WebloanType();
		WebloanType oneYear = new WebloanType();
		jsonWebloanDetail.put("oneMonth", oneMonth);
		jsonWebloanDetail.put("oneSeason", oneSeason);
		jsonWebloanDetail.put("halfYear", halfYear);
		jsonWebloanDetail.put("oneYear", oneYear);
		result.put("webloanDetail", jsonWebloanDetail);
		
		// serial
		QuerySerial qs = new QuerySerial();
		result.put("serial", qs);
		

		// blkInfo
		result.put("blkInfo", new JSONArray());
		
		// blkInfoDetail
		JSONArray jsonBlkInfoDetail = new JSONArray();
		result.put("blkInfoDetail", jsonBlkInfoDetail); 
		
//		try {
			
			jsonRetInfo = JSON.parseObject(context);
			int resultCode = jsonRetInfo.getIntValue("result_code");
			if(200 != resultCode){
//				return result; // 20160719
				throw new ResponseException(resultCode, jsonRetInfo.getString("result_desc"));
			}
			JSONObject jsonResult = jsonRetInfo.getJSONObject("result"); 
			 
			qs.setQuery_id( jsonResult.getString("query_id"));
			qs.setQuery_time(DateUtil.getDateFromStamp(jsonResult.getString("query_time")));
			
			JSONArray jsonQueryReports = jsonResult.getJSONArray("query_reports");
			JSONObject jsonBS = null;
			int bsResCode = -1;
			String bsId = null;
			
			JSONArray jsonarrayResDetail = null;
			JSONObject tem = null; 
			for(int i=0;i<jsonQueryReports.size();i++){
				try {

					jsonBS = jsonQueryReports.getJSONObject(i);
					bsResCode = jsonBS.getIntValue("business_res_code");
					if(1000 != bsResCode){
						continue;
					}
					
					bsId = jsonBS.getString("business_id");
					jsonarrayResDetail = jsonBS.getJSONArray("business_res_detail");
					
					if("A1".equals(bsId)){
						tem = jsonarrayResDetail.getJSONObject(0);
						ws.setWiskLevel(tem.getString("wd_risk_level"));
					}
					else if("A2".equals(bsId)){
						tem = jsonarrayResDetail.getJSONObject(0);	 
						ws.setTimes(tem.getIntValue("wd_count"));
						ws.setStage(tem.getString("wd_result")); 			
					}
					else if("A3".equals(bsId)){
						setWebloanType(oneMonth,jsonarrayResDetail); 	
					}
					else if("A4".equals(bsId)){
						setWebloanType(oneSeason,jsonarrayResDetail); 	
					}
					else if("A5".equals(bsId)){
						setWebloanType(halfYear,jsonarrayResDetail); 	
					}
					else if("A6".equals(bsId)){
						setWebloanType(oneYear,jsonarrayResDetail); 	
					}
					else if("C1".equals(bsId)){
						result.put("blkInfo", jsonarrayResDetail);
					}
					else if("C3".equals(bsId)){
//						result.put("blkInfoDetail",jsonarrayResDetail); // 20160721 新需求只展示 '贷款逾期'
						if(null != jsonarrayResDetail && jsonarrayResDetail.size()>0){ 
							for(int k = 0;k<jsonarrayResDetail.size();k++){
								if("贷款逾期".equals(jsonarrayResDetail.getJSONObject(k).getString("black_risk_type"))){
									jsonBlkInfoDetail.add(jsonarrayResDetail.getJSONObject(k));
								}
							}
							
						}
					}
					else{
						logger.info("["+bsId+"]暂时不需要解析");
					}					
					
				} catch (Exception e) { 
					logger.info(StackTraceUtil.getStackTrace(e));
					continue;
				}				
				
			}
//			
//		} catch (Exception e) { 
//			logger.info(StackTraceUtil.getStackTrace(e));
//		}
		
		return result;
	}
	
	private void test(){
		
		if(null == criRef || 
		   null == userRef || 
		   StringUtil.emptyString(userRef.getUser_login_name()) ||
		   StringUtil.emptyString(userRef.getUser_password())){
			return ;
		}
		
//		HuiYuRequestQueryCondition hyrqc = null; 
		HuiYuRequestInfo hyri = null;
  
//		String url = "http://192.168.1.161:8080/HuiYuApiServer/user/getUserInfo";
//		String url = "http://api.huiyu.org.cn/HuiYuApiServer//user/getUserInfo";
//		String url = "http://192.168.1.163:8080/HuiYuApiServer//user/getUserInfo";
		String url = null;
		ConsOptionSettings op = (ConsOptionSettings)SpringContextHolder.getBean("consOptionSettings");
		if(null != op){
			url = op.getApiServerUtl();
		}
		else{
			url = "http://api.huiyu.org.cn/HuiYuApiServer//user/getUserInfo";
		}
		logger.info("url:\r\n"+url);
		HuiYuSimpleClient hydc = null;
		
		try {
//			hyri = new HuiYuRequestInfo("wdc","wdc12345");
//			hyri = new HuiYuRequestInfo("admin","admin");
			hyri = new HuiYuRequestInfo(userRef.getUser_login_name(),"abc");
			hyri.setVerify_key(EncodeUtil.encodeStrByMD5(hyri.getUser_name()+hyri.getTime_stamp()+userRef.getUser_password()));
			hyri.setBusiness_set(criRef.getBusiness_set());
//			hyrqc = hyri.getQuery_condition();
//			hyrqc = hyri.getQuery_condition();
//			hyrqc.setQuery_name(hyrqc.getQuery_name());
//			hyrqc.setQuery_cid("330182198405280534");
//			hyrqc.setQuery_mobile("13857109491");
//			hyrqc.setQuery_qq("54445454");
			
			hyri.setQuery_condition(criRef.getQuery_condition());			
			
			hydc = new HuiYuSimpleClient(hyri, url);
			long beginTime = System.currentTimeMillis();
			logger.info("调用ApiServer开始");
			hydc.doPost();
			long endTime = System.currentTimeMillis();
			logger.info("执行时间["+(endTime-beginTime)+"]");
			
			logger.info("requst info\r\n"+hydc.getRequestBody());
			
//			String responseResult = hydc.getResponseBody();
			responseBodyRef = hydc.getResponseBody();
			
			logger.info("response info:\r\n"+responseBodyRef);
			
			
			
		} catch (Exception e) {
			logger.info(StackTraceUtil.getStackTrace(e));
		} 	
	}
	@Override
	public JSONObject getData(ClientRequestInfo cri,User user) throws Exception {
		// TODO Auto-generated method stub
		userRef = user;
		criRef = cri;
		test();
		return this.parse(responseBodyRef);
	}

}
