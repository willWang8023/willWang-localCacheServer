package com.will.localcache.localcacheserver.util;

//import org.apache.commons.lang.StringUtils;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 异常码基类
 * @Author liugj
 */
public interface ExceptionResolvable {

	/**
	 * 获取异常码
	 * @return
	 */
	Integer getCode() ;

	/**
	 * 获取异常信息
	 * @return
	 */
	String getMessage() ;

//	/**
//	 * 默认获取异常信息（考虑国际化）
//	 * @param message
//	 * @return
//	 */
//	default String getMessage(String message) {
//
//		MessageSource messageSource = (MessageSource) SpringApplicationContext.getBean(ReloadableResourceBundleMessageSource.class);
//		Locale locale = LocaleContextHolder.getLocale();
//		String localeMessage = messageSource.getMessage(message, null, "", locale);
//		if(StringUtils.isEmpty(localeMessage)){
//			localeMessage = message;
//		}
//		return localeMessage;
//	}
}
