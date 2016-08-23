package api.com.chj.core.third;

import java.util.HashMap;
import java.util.Map;

public class ThirdSpecificUtil {
	
	public static final String KEY_THIRD_DATASOURCE = "third_data_source";
	public class ThirdDataSource{
		public static final String THIRD_DATASOURCE_GEO = "datasource_geo";
	}
	
	public static final double PRICE_UNIT_GEO = 1.0;
	
	public enum ThirdType{
		GEO,
		GET
	}
	
	private static final Map<String,String> geoMapNormalCode = new HashMap<String,String>();
	static{
		geoMapNormalCode.put("10000002", "10000002"); // 暂不支持此运营商
		geoMapNormalCode.put("10000004", "10000004"); // 查无此记录
	}

	public static boolean isNormal(ThirdType thirdType,String code){	
		switch(thirdType){
			case GEO:
				return null != geoMapNormalCode.get(code);  
			default:
				return false; 			
		}
	}
}
