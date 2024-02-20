package com.eproject.folklor.markovic.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled  from igrac where username=?");

        
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, uloga from uloge where username=?");

        return jdbcUserDetailsManager;
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		
		http
		
			.authorizeHttpRequests(configurer ->
				configurer
					
					.anyRequest().authenticated()
				
		)
		.formLogin(form ->
			form
				.loginPage("/")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()		
		)
		.logout(logout -> logout.permitAll()
		);
		
		return http.build();
	}

}
