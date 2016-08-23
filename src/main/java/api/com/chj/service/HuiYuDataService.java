package api.com.chj.service;

import com.alibaba.fastjson.JSONObject;

import api.com.chj.po.User;
import api.com.chj.vo.ClientRequestInfo;

public interface HuiYuDataService {

	JSONObject getData(ClientRequestInfo cri,User user) throws Exception;
}
