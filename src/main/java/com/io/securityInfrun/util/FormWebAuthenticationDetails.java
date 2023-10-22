package com.io.securityInfrun.util;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import jakarta.servlet.http.HttpServletRequest;

public class FormWebAuthenticationDetails extends WebAuthenticationDetails{

	//로그인 할때 무언가 값을 저장시키는 클래스
	private String secretkey;
	
	public FormWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		secretkey = request.getParameter("secret_key");
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

}
