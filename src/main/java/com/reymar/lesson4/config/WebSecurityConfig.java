package com.reymar.lesson4.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig {
	@Value("${keys.user}") private String USER;
	@Value("${keys.password}") private String PASS;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder()
				.username(USER)
				.password(passwordEncoder().encode(PASS))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests(auth -> {
					auth.antMatchers("/**").hasRole("USER");
				})
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
}
