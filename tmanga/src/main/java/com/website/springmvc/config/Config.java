package com.website.springmvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Configuration class to get data from properties file
 * 
 */
@Configuration
public class Config {

	private static final String BASE_MESSAGES = "messages";
	
	private static final String UTF_8 = "UTF-8";

	/**
	 * @return {@link MessageSource}
	 */
	@Bean
	public MessageSource messageSource() {

		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(BASE_MESSAGES);
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