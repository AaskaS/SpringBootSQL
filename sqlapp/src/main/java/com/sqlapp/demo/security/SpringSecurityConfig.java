package com.sqlapp.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private DataSource dataSource;

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	/*
	 * @Autowired public void configAuthentication(AuthenticationManagerBuilder
	 * auth) throws Exception { auth.jdbcAuthentication().passwordEncoder(new
	 * BCryptPasswordEncoder()) .dataSource(dataSource)
	 * .usersByUsernameQuery("select username, password, enabled from users where username=?"
	 * )
	 * .authoritiesByUsernameQuery("select username, role from users where username=?"
	 * ) ; }
	 */

	// roles admin allow to access /admin/**
	// roles user allow to access /user/**
	// custom 403 access denied handler
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		 * .antMatchers("/", "/**").permitAll().anyRequest().authenticated().and()
		 * .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/user").and()
		 * .csrf().disable();
		 */

		//without roles
		
		  http.authorizeRequests().antMatchers("/", "/signup", "/home", "/success",
		  "/register").permitAll()
		  .antMatchers("/admin/**").hasAnyRole("ADMIN").antMatchers("/user/**").
		  hasAnyRole("USER")
		  .antMatchers("/main").authenticated().and().formLogin().loginPage("/login").
		  permitAll()
		  .defaultSuccessUrl("/main").usernameParameter("user_name").passwordParameter(
		  "pass").and().logout()
		  .permitAll().and().exceptionHandling().accessDeniedHandler(
		  accessDeniedHandler);
		 

		// default use
		
		/*
		 * http.authorizeRequests().antMatchers("/", "/signup", "/home", "/success",
		 * "/register").permitAll()
		 * .antMatchers("/admin/**").hasAnyRole("ADMIN").antMatchers("/main").hasAnyRole
		 * ("ADMIN", "USER")
		 * .antMatchers("/user/**").hasAnyRole("USER").anyRequest().authenticated().and(
		 * ).formLogin()
		 * .loginPage("/login").permitAll().defaultSuccessUrl("/main").usernameParameter
		 * ("user_name")
		 * .passwordParameter("pass").and().logout().permitAll().and().exceptionHandling
		 * () .accessDeniedHandler(accessDeniedHandler);
		 */
		 

	}


	
}