package com.io.securityInfrun.config;

import java.util.Arrays;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.io.securityInfrun.util.RoleHierarchyServiceImpl;
import com.io.securityInfrun.util.Filter.AjaxLoginProcessingFilter;
import com.io.securityInfrun.util.entryPoint.AjaxAccessDeniedHandler;
import com.io.securityInfrun.util.entryPoint.AjaxLoginAuthenticationEntryPoint;
import com.io.securityInfrun.util.handler.AjaxAuthenticationFailureHandler;
import com.io.securityInfrun.util.handler.AjaxAuthenticationSuccessHandler;
import com.io.securityInfrun.web.user.service.AjaxAuthenticationProvider;

import jakarta.servlet.DispatcherType;

@Order(0)
@Configuration 
@EnableWebSecurity
public class AjaxSecurityConfig {
	
	private AjaxLoginProcessingFilter filter;
	
	@Autowired
	private RoleHierarchyServiceImpl roleHierarchyServiceImpl;
	
	@Bean
	public AuthenticationProvider ajaxAuthenticationProvider() {
        return new AjaxAuthenticationProvider();
    }
	
	@Bean
    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter(AuthenticationManager authenticationManager) {
        filter.setAuthenticationManager(authenticationManager);
        filter.setSecurityContextRepository(delegatingSecurityContextRepository());
        filter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
        return filter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    	filter = new AjaxLoginProcessingFilter(http);
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
	public AjaxAccessDeniedHandler ajaxAccessDeniedHandler() {
        return new AjaxAccessDeniedHandler();
    }
	
	@Bean
	public DelegatingSecurityContextRepository delegatingSecurityContextRepository() {
        return new DelegatingSecurityContextRepository(
        		new RequestAttributeSecurityContextRepository(), 
        		new HttpSessionSecurityContextRepository());
    }
	
	@Bean
    public SecurityFilterChain allfilterChain2(HttpSecurity http) throws Exception { 
		
    	http
    	//.csrf(csrf ->csrf.disable())
    	.cors(cors ->cors.configurationSource(corsConfigurationSource()))
    	.securityMatcher("/api/**")
    	//.sessionManagement(management ->management
        //.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))//IF_REQUIRED
        .authorizeHttpRequests(req -> req
        	    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // 맨 처음
        	    //.requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.*", "/*/icon-*").permitAll()
				.requestMatchers(new AntPathRequestMatcher("/api/login"), new AntPathRequestMatcher("/api")).permitAll() //여기 경로만 탈때 ajaxSecuriyConfig가 작동을 하게 되는 것이다.
				//.requestMatchers(new AntPathRequestMatcher("/api/messages.do")).hasAnyRole("ADMIN","MANAGER") //hasRole("MANAGER")
				.requestMatchers(new AntPathRequestMatcher("/api/user")).hasAuthority("ROLE_USER")
				.requestMatchers(new AntPathRequestMatcher("/api/manager")).hasAuthority("ROLE_MANAGER")
				.requestMatchers(new AntPathRequestMatcher("/api/admin")).hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
        )
        .addFilterAfter(ajaxLoginProcessingFilter(authenticationManager(http)), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(handling ->handling
        		.authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint())
        		.accessDeniedHandler(ajaxAccessDeniedHandler())
        )
        ; 	
        
      return http.build();
   }
	
	@Bean
    public RoleHierarchyImpl roleHierarchy(){

        String allHierarchy = roleHierarchyServiceImpl.findAllHierarchy();
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(allHierarchy);

        return roleHierarchy;
    }
	
	/*
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // 허용할 도메인 설정
        config.addAllowedHeader("*"); // 허용할 헤더 설정
        config.addAllowedMethod("*"); // 허용할 메서드 설정
        source.registerCorsConfiguration("/**", config); // 모든 경로에 대해 CORS 설정 적용
        return new CorsFilter();
    }
	*/
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	    configuration.setAllowCredentials(true);
	    configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Authorization", "Cache-Control", "Content-Type"));
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
	
}
