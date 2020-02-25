package com.badri.springsecurity.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

// this is configuration class as we are not using xml 
@Configuration
@EnableWebMvc // similar to annotation : <mvc: annotation-driven >
@ComponentScan("com.badri.springsecurity")

//read the property file
@PropertySource("classpath:persistence-mysql.properties")

public class DemoAppConfig {
	
	//set up variable to hold the properties 
	@Autowired
	Environment env; //will hold data read from the properties file
	

	//define bean for the view resolver
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		//resolve the prefix
		viewResolver.setPrefix("/WEB-INF/view/");
		
		//resolve the suffix .file extension
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
	
	//define the bean for our security datasource
	@Bean
	public DataSource securityDataSource() {
		
		//create the connection pool
		ComboPooledDataSource ds = new ComboPooledDataSource();
		
		//set up jdbc driver
		try {
			ds.setDriverClass(env.getProperty("jdbc.driver")); //jdbc.driver is from the properties file
			
		} catch (PropertyVetoException e) {

			e.printStackTrace();
		}
		
		//setting up the database connection properties
		ds.setJdbcUrl(env.getProperty("jdbc.url"));
		ds.setUser(env.getProperty("jdbc.username"));
		ds.setPassword(env.getProperty("jdbc.password"));
		
		//set connection pool properties, all read from the properties file
		ds.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		ds.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		ds.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		ds.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return ds;
	}	
		//set up helper method
		//when we read environment it is always string so we need to convert to int
		private int getIntProperty(String propName) {
			
	      String propVal  = env.getProperty(propName);
		  
	      //convert to int
	      int intPropVal = Integer.parseInt(propVal);
	      
			return intPropVal;
	
	}
}
