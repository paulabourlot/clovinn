package ar.com.clovinn.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public final class AutowireHelper implements ApplicationContextAware {
	 
    private static final AutowireHelper INSTANCE = new AutowireHelper();
    private static ApplicationContext context;
 
    private AutowireHelper() {
    }
 
    public static void autowire(Object classToAutowire, Object... beansToAutowireInClass) {
        for (Object bean : beansToAutowireInClass) {
            if (bean == null) {
            	context.getAutowireCapableBeanFactory().autowireBean(classToAutowire);
                return;
            }
        }
    }
 
    public static AutowireHelper getInstance() {
        return INSTANCE;
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
	public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
 
}