package com.io.securityInfrun.util;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.io.securityInfrun.web.user.vo.UserVo;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter{

	// json으로 받을거니까 담을 그릇만들기
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	public AjaxLoginProcessingFilter() {
		super(new AntPathRequestMatcher("/api/login")); //해당 url로 요청이 된게 아니라면 필터를 하지 않은다.
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		if(!isAjax(request)){
			throw new IllegalStateException("인증 is not supprted");
		}
		
		//request에서 받은 정보를 읽어서 UserVo 타입으로 object를 만들어주는거야.
		UserDetails userDetails = objectMapper.readValue(request.getReader(), UserVo.class); 
		
		// api Login으로부터 받은 request 정보 중에서 아이디와 비밀번호 데이터가 있는지 없는지 판단해주는 곳
		if(StringUtils.isEmpty(userDetails.getUsername()) || StringUtils.isEmpty(userDetails.getPassword())) {
			throw new IllegalArgumentException("아이디 또는 비밀번호가 비어있습니다");
		}
		
		//값이 다 들어가 있으면 token 만들어주는 곳.
		AjaxAuthentivationToken ajaxAuthentivationToken = new AjaxAuthentivationToken(userDetails.getUsername(), userDetails.getPassword());
		
		//매니저에다가 우리가 만든 토큰을 전달하면 인증처리가 이루어진다.
		return getAuthenticationManager().authenticate(ajaxAuthentivationToken); 
	}

	private boolean isAjax(HttpServletRequest request) {
		//여기서 이제 이게 아작스 인지 아닌지 판단할거임.
		//해당 기준은 해더에 어떤 정보를 클라이언트가 설정한 값으로 아작스 인지아닌지 판단하자. 이거은 우리가 그냥 정하는거임.
		if("XMLHttpRequest".equals(request.getHeader("X-Requested-with"))) {
			return true;
		}
		return false;
	}

}
