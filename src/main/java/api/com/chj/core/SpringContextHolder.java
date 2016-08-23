package api.com.chj.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		checkApplicationContext();		
		return (T)applicationContext.getBean(beanName);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();		
		return (T)applicationContext.getBeansOfType(clazz);	
	}
	
	public static ApplicationContext getApplicationContext(){
		checkApplicationContext();
		return applicationContext;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		SpringContextHolder.applicationContext = arg0;
	}

	public static void cleanApplicationContext(){
		applicationContext = null;
	}
	
	private static void checkApplicationContext() {  
		if (applicationContext == null) {  
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");  
		}  
	} 
	
}
