package com.badri.springsecurity.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//this file is making dispatcher servlet initializer for servlet.xml file

public class SpringWebMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		// return the classes the file that contains view resolver
		return new Class[] { DemoAppConfig.class };

	}

	@Override
	protected String[] getServletMappings() {
		// for servlet mapping
		return new String[] { "/" };
	}
}
