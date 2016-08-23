package api.com.chj.core;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import api.com.chj.mapper.POConstantsMapper;

@Component("mapperContainer")
public class MapperUtil {

	private POConstantsMapper pOConstantsMapper;

	@Resource
	public void setpOConstantsMapper(POConstantsMapper pOConstantsMapper) {
		this.pOConstantsMapper = pOConstantsMapper;
	}

	
	public POConstantsMapper getpOConstantsMapper() {
		return pOConstantsMapper;
	} 
	
}
