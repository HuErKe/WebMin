package api.com.chj.service;

import java.util.List;

import api.com.chj.po.LoginLog;
import api.com.chj.po.User;

public interface UserService {
	/**
	 * 20160718 Chen Huajun 
	 * @param llog
	 * @throws Exception
	 */
	void saveLoginLog(LoginLog llog) throws Exception;
	User getUserByUserLoginName(String loginName) throws Exception;
	List<User> getUsersByUserLoginName(String loginName) throws Exception;
}
