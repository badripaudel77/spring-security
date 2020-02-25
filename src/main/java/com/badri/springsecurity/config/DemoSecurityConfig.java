package com.badri.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableWebSecurity

//add the authentication
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	//add the reference to our security data source
	@Autowired
	DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//use the jdbc authentication
	auth.jdbcAuthentication().dataSource(securityDataSource);
		
		
	//comment those as we are not going to use this hard coded value
	/*
	// add the users for in memory authentication

	UserBuilder users = User.withDefaultPasswordEncoder();
	 auth.inMemoryAuthentication()
	.withUser(users.username("badri").password("badri123").roles("ADMIN"))
	.withUser(users.username("hari").password("hari123").roles("MANAGER"))
	.withUser(users.username("sujan").password("sujan123").roles("USERS"));
 
    */
		
	}

	// override for to use custom login form

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/").hasAnyRole("USERS","MANAGER","ADMIN")  // "/" is for homepage, so it means 
		//anyone can access homepage if role is 'ADMIN'
		.antMatchers("/leaders/**").hasRole("MANAGER") // ** means all sub directories
		.antMatchers("/systems/**").hasRole("ADMIN")
		.and().formLogin()
		.loginPage("/showLoginPage")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll() // show login form to all (of course)
	     .and()
	     .logout() //add spring security logout support
	     .permitAll()
	      .and() //add custome access denied page
	      .exceptionHandling().accessDeniedPage("/access-denied"); 
		//default logout url is : /logout
	}

}
