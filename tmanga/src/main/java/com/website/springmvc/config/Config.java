package com.website.springmvc.config;

import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 * Configuration class to get data from properties file
 * 
 */
@Configuration
public class Config {
//	private static HttpSession session;	
	private static final String BASE_MESSAGES = "messages";

	private static final String UTF_8 = "UTF-8";
	private static final String MESSAGES_EN = "messages_en";
	private static final String MESSAGES_VN = "messages_vn";

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

//	@Bean(name = "localeResolver")
//	public LocaleResolver getLocaleResolver() {
//		CookieLocaleResolver resolver = new CookieLocaleResolver();
//		resolver.setCookieDomain("myAppLocaleCookie");
//		// 60 minutes
//
//		resolver.setCookieMaxAge(60 * 60);
//		return resolver;
//	}

	/**
	 * @return {@link MessageSource}
	 */
	@Bean
	public MessageSource messageSource() {

		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(MESSAGES_VN, MESSAGES_EN);
		messageSource.setDefaultEncoding(UTF_8);
		return messageSource;
	}

	@Bean
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
		messageResource.setBasename("classpath:messages/messages");
		messageResource.setDefaultEncoding(UTF_8);
		return messageResource;
	}

	/**
	 * Get value from a key code from messages_en_US.properties.
	 * 
	 * @param code
	 * @return {@link String}
	 */
	public String getProperty(String code) {

		return messageSource().getMessage(code, null, null);
	}

	/**
	 * Get value from a key code from messages_en_US.properties.
	 * 
	 * @param code
	 * @param args
	 * @return {@link String}
	 */
	public String getProperty(String code, String... args) {

		return messageSource().getMessage(code, args, null);
	}
}