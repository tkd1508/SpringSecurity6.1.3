package com.io.securityInfrun.util.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//SimpleUrlAuthenticationFailureHandler는 기본 시큐리티에서 제공하는 핸들러중 하나이다.
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String errorMessage = "Invalid ID or PASSWORD";
		
		String ex_num = exception.getMessage();
		
		if("1".equals(ex_num)) {
			errorMessage = "Invalid Secret key(error_code:1)";
		}
		/*
		if(exception instanceof BadCredentialsException){
			errorMessage = "Invalid ID or PASSWORD";
		} else if(exception instanceof InsufficientAuthenticationException) {
			errorMessage = "Invalid Secret Key";
		} else if(exception instanceof DisabledException) {
			errorMessage = "Locked";
		} else if(exception instanceof CredentialsExpiredException) {
			errorMessage = "Expried password";
		} else if(exception instanceof SecretException) {
            errorMessage = "Invalid Secret key";
        }*/
		
		setDefaultFailureUrl("/login.do?error=true&exception=" + errorMessage);
		
		System.out.println("/login.do?error=true&exception=" + errorMessage);
		
		
		super.onAuthenticationFailure(request, response, exception);
		
	}
	
}
