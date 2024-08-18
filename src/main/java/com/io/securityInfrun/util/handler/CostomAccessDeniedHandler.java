package com.io.securityInfrun.util.handler;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CostomAccessDeniedHandler implements AccessDeniedHandler{

	private String errorPage;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String deniedUrl = errorPage + "?exception=" + accessDeniedException.getMessage();
		
		System.out.println(deniedUrl);
		
		response.sendRedirect(deniedUrl);
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	
	
}
