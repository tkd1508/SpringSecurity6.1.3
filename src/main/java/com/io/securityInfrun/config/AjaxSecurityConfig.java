package com.io.securityInfrun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.io.securityInfrun.util.AjaxLoginProcessingFilter;
import com.io.securityInfrun.util.handler.AjaxAuthenticationFailureHandler;
import com.io.securityInfrun.util.handler.AjaxAuthenticationSuccessHandler;
import com.io.securityInfrun.web.user.service.AjaxAuthenticationProvider;

import jakarta.servlet.DispatcherType;

@Order(0)
@Configuration 
@EnableWebSecurity
public class AjaxSecurityConfig {
	
	private AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
	
	@Bean
	public AuthenticationProvider ajaxAuthenticationProvider() {
        return new AjaxAuthenticationProvider();
    }
	
	@Bean
    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter(AuthenticationManager authenticationManager) {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter();
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .authenticationProvider(ajaxAuthenticationProvider())
                   .build();
    }
	
	@Bean
	public AuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
        return new AjaxAuthenticationSuccessHandler();
    }
	
	@Bean
	public AuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
        return new AjaxAuthenticationFailureHandler();
    }
	
	@Bean
    public SecurityFilterChain allfilterChain2(HttpSecurity http) throws Exception { 
		
		// 추가된 코드
		//ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean);
		ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
		ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());

    	http.csrf().disable().cors().disable().securityMatcher("/api/**")
        .authorizeHttpRequests(request -> request
        	    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // 맨 처음
				.requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll() //여기 경로만 탈때 ajaxSecuriyConfig가 작동을 하게 되는 것이다.
				//.requestMatchers(new AntPathRequestMatcher("/user2.do"), new AntPathRequestMatcher("/user2Info.do")).hasRole("ADMIN")
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
        )
        .addFilterBefore(ajaxLoginProcessingFilter(authenticationManager(http)), UsernamePasswordAuthenticationFilter.class);
        ; 	
        
      return http.build();
   }
	
	
}
