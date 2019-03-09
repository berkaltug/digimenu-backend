package com.digimenu.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
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
                
                .authorizeRequests()
                .antMatchers(
                		"/user/register",
                		"/user/confirmaccount/**",
                		"/user/forgetpassword/**",
                		"/user/resetpassword/**",
                		"/user/login"
                		).permitAll()
                .antMatchers("/user/savepassword").hasAuthority("CHANGE_PASSWORD_PRIVILIGE")
//                .antMatchers("/restaurant/**").hasRole("RESTAURANT")
                .antMatchers("/city/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/table_orders/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/table_orders/**").hasRole("ADMIN")
                .antMatchers("/menu/**").hasRole("USER")

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .csrf().disable()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint)
                
                 ;
        }
    }
    
    @Configuration
    @Order(2)	// no order means order of config value is last
    public static class RestaurantSecurityConfig extends WebSecurityConfigurerAdapter{
    	
    	@Override
    	protected void configure(HttpSecurity http) throws Exception{
    		http
    		.antMatcher("/restaurant/**")  //antmatcher tekil şekilde urlleri farklı configler için gruplamada kullanılır
    		.authorizeRequests()
				.antMatchers("/assets/**", "/webjars/**","/static/**").permitAll()
				.antMatchers("/restaurant/**")
				.hasRole("RESTAURANT")
				.and()
			.formLogin()
				.loginPage("/restaurant/login")
				.loginProcessingUrl("/restaurant/login")
				.failureUrl("/restaurant/login?error=true")
				.defaultSuccessUrl("/restaurant/tables",true)
				.permitAll()
				.and()
			.logout()
			.logoutUrl("/restaurant/logout")
			.deleteCookies("JSESSIONID")
			;
    	}
    	
    }
    
    
    
}