package org.sid.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	
	  auth.inMemoryAuthentication().withUser("hassan").password("{noop}1234").
	  roles( "USER","ADMIN");
	  auth.inMemoryAuthentication().withUser("mohamed").password("{noop}1234").
	  roles( "USER");}
	 
		
		  
		 
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin().loginPage("/login");
		//http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/saveEntreprise","/formEntreprise").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/entreprises","/taxes").hasRole("USER");

		http.exceptionHandling().accessDeniedPage("/403");
	}
}
