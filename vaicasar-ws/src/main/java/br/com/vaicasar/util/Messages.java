package br.com.vaicasar.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages implements InitializingBean {

	@Autowired
	private MessageSource messageSource;
	
	private MessageSourceAccessor accessor;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		accessor = new MessageSourceAccessor(messageSource);
	}
	
	public String get(String code) {
		return accessor.getMessage(code);
	}
	
	public String get(String code, String... parameters) {
		return accessor.getMessage(code, parameters);
	}
}
