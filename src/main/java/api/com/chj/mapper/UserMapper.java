package api.com.chj.mapper;

import java.util.List;

import api.com.chj.po.LoginLog;
import api.com.chj.po.User;

public interface UserMapper {
	/**
	 * 20160718 Chen Huajun 
	 * @param llog
	 */
	void saveLoginLog(LoginLog llog);
	User getUserByUserLoginName(String userLoginName);
	List<User> getUsersByUserLoginName(String userLoginName);
	List<User> getValidUsersByUserLoginName(String userLoginName);
}
