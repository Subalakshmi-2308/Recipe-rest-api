package com.receipe_rest_api.receipe_api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuth {

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeRequests().requestMatchers("/category/**").permitAll()
		.requestMatchers("/receipe/**").hasAnyRole("User","Admin")
		.anyRequest().authenticated().and().httpBasic();
		return http.build();
		
	}
	
	@Bean
	public UserDetailsService userDetails() {
		UserDetails user=User.builder().username("user").password(PasswordEncoder().encode("1234")).roles("User").build();
		
		UserDetails admin=User.builder().username("Admin").password(PasswordEncoder().encode("000")).roles("Admin").build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
