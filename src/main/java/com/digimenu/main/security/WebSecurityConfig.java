package com.digimenu.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true,jsr250Enabled=true)
public class WebSecurityConfig {
	
	@Autowired
    private UserDetailsService customUserDetailsService;

    
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception
    {
    	auth.userDetailsService(customUserDetailsService)
    	.passwordEncoder(passwordEncoder());
    }
    
    @Configuration
    @Order(1)
    public static class AppSecurityConfig extends WebSecurityConfigurerAdapter{
    	
    	@Autowired
        private AuthenticationEntryPoint authEntryPoint;
    	
    	@Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
//                .antMatchers("/restaurant/**").hasRole("RESTAURANT")
//                .antMatchers("/city/**").hasRole("ADMIN")
//                .antMatchers("/table_orders/**").hasRole("ADMIN")
//                .antMatchers("/menu/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 ;
        }
    }
    
    @Configuration
    @Order(2)
    public static class RestaurantSecurityConfig extends WebSecurityConfigurerAdapter{
    	
    	@Override
    	protected void configure(HttpSecurity http) throws Exception{
    		http
    		
    		.authorizeRequests()
				.antMatchers("/assets/**", "/webjars/**").permitAll()
				.antMatchers("/restaurant/**")
				.hasRole("RESTAURANT")
				.and()
			.formLogin()
				.loginPage("/restaurant/login")
				.failureUrl("/restaurant/login-error")
				.permitAll()
				.and()
			.logout()
			.logoutUrl("/restaurant/logout")
			.deleteCookies("JSESSIONID");
    	}
    	
    }
    
    
    
}