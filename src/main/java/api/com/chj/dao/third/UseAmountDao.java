package api.com.chj.dao.third;

import java.util.List;

import api.com.chj.po.third.UsageAmountPerDayPerIfcd;
import api.com.chj.service.third.geo.UsageAmountHelperGeo;

public interface UseAmountDao {

	void writeUsageOfEachIfcdEachItemChart(String filename,
			UsageAmountHelperGeo uaHelper) throws Exception;
}
