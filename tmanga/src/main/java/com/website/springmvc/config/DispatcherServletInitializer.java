package com.website.springmvc.config;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Web Application initializer
 * 
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final String DISPATCH_OPTIONS_REQUEST = "dispatchOptionsRequest";

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class<?>[] { SpringWebConfig.class, SpringDatabaseConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[] {};
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {

		registration.setInitParameter(DISPATCH_OPTIONS_REQUEST, Boolean.TRUE.toString());
	}
}