package com.digimenu.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.reactive.result.view.CsrfRequestDataValueProcessor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true,jsr250Enabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
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
    public static class AppSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private MyRestAuthenticationEntryPoint authEntryPoint;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.requestMatchers()
					.antMatchers("/user/**", "/table_orders/**").and()
					.authorizeRequests()
					.antMatchers(
							"/user/register",
							"/user/confirmaccount/**",
							"/user/forgetpassword/**",
							"user/resetpassword",
							"/user/resetpassword/**"
							//"/user/login"
					)
					.permitAll()
					//.antMatchers("/restaurant/**").hasRole("RESTAURANT")
					.antMatchers("/city/**").hasRole("ADMIN")
					.antMatchers(HttpMethod.POST, "/table_orders/**").hasRole("USER")
					.antMatchers(HttpMethod.GET, "/table_orders/**").hasRole("ADMIN")
					.antMatchers("/menu/**").hasRole("USER")
					.antMatchers(HttpMethod.POST, "/user/login").hasRole("USER")
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
    	
    	@Bean  
    	public AuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
    		return new LoginUrlAuthenticationEntryPoint("/restaurant/login");
    	}

    	@Override
    	protected void configure(HttpSecurity http) throws Exception{
    		http.csrf().disable()
    		.antMatcher("/restaurant/**")  //antmatcher tekil şekilde urlleri farklı configler için gruplamada kullanılır
    		.authorizeRequests()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.antMatchers("/restaurant/flushitem/**").permitAll()
				.anyRequest().hasRole("RESTAURANT")
				.and()
			.formLogin()
				.loginPage("/restaurant/login")
				//.loginProcessingUrl("/restaurant/reslogin")
				.failureUrl("/restaurant/login?error")
				.defaultSuccessUrl("/restaurant/tables",true)
				.permitAll()
				.and()
			.logout()
			.logoutUrl("/restaurant/logout")
			.logoutSuccessUrl("/restaurant/login?logout")
			.deleteCookies("JSESSIONID")
			.permitAll()
			.and()
			.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/restaurant/login"))

			;
    	}


    }

}
    
    
