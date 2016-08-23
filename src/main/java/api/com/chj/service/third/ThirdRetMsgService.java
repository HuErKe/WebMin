package api.com.chj.service.third;

import java.util.List;

public interface ThirdRetMsgService<ParseResult,ThirdApiLog> {

	void doParse(List<ThirdApiLog> lstals,List<ParseResult> lsInfos) throws Exception;
}
