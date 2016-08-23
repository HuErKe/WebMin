package api.com.chj.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import api.com.chj.exception.ResponseErrorCodeUtil;
import api.com.chj.exception.ResponseException;
import api.com.chj.mapper.UserMapper;
import api.com.chj.po.LoginLog;
import api.com.chj.po.User;

@Service
public class UserServiceImpl implements UserService{


	private UserMapper userMapper;
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	

	@Override
	public void saveLoginLog(LoginLog llog) throws Exception { 
		if(null == llog){
			return;
		}
		this.userMapper.saveLoginLog(llog);
	}
	
	@Override
	public User getUserByUserLoginName(String loginName) throws Exception { 
		logger.info("UserServiceImpl.getUserByUserLoginName");
		List<User> ul =  getUsersByUserLoginName(loginName); 
		if(null != ul && ul.size() == 1){
			return ul.get(0);
		}
		else{ 
			logger.info("没有获取到User数据或者获取到多个数据"); 
			throw new ResponseException(ResponseErrorCodeUtil.RESPONSE_CODE_VERYFYKEY_INVALID,"账户或密码错误");
		}  
		
	}

	@Override
	public List<User> getUsersByUserLoginName(String loginName) throws Exception { 
		
		if(null == userMapper){
			logger.info("user mapper is null");
			return null;
		}
		else{ 			
			logger.info("about to load user");
			return this.userMapper.getValidUsersByUserLoginName(loginName);
		} 
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


}
