package com.ecommerce.ecommerceapplicatiom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfig {

	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(admin);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * @Bean SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
	 * throws Exception { httpSecurity .csrf().disable();
	 * httpSecurity.authorizeHttpRequests().anyRequest().permitAll(); return
	 * httpSecurity.build(); }
	 */
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .requestMatchers("/admin/**").hasRole("ADMIN") 
            .anyRequest()
            .permitAll()
            .and()
            .formLogin()
            .defaultSuccessUrl("/admin/dashboard", true)
            .and()
            .logout()
            .logoutUrl("/admin/logout").permitAll()
            .and()
            .csrf().disable();
        return http.build();
    }
	
}
