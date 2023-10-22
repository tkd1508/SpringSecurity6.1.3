package com.io.securityInfrun.web.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.io.securityInfrun.util.FormWebAuthenticationDetails;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub 1. 검증을 위한 메소드
		
		String username = authentication.getName(); //로그인할때의 id
		String password = (String) authentication.getCredentials(); //로그인시 패스워드 object라서 String으로 형변환 일반 패스워드
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username); // 여기서 저장된 패스워드를 뽑아와 암호화된 패스워드 여기를 거치면 일단 아이디만 검증이 된거야.
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			//LLog.debug 기록해주고
			throw new BadCredentialsException("패스워드가 일치하지 않습니다.");
			//메세지 안뜨네 이것도 에러페이지 만들때 수정해주자
		}
		
		//======================================================== 여기까지 넘어오면 id와 password는 검증이 끝난 것이다. 이후 다양한 인증 검증 로직은 아래에 추가해주기.
		
		//로그인창에서의 secret 값 있는지 없는지로 검증
		FormWebAuthenticationDetails f = (FormWebAuthenticationDetails) authentication.getDetails();
		String secretKey = f.getSecretkey();
		
		if(secretKey == null || !"kkk".equals(secretKey)) {
			throw new InsufficientAuthenticationException("InsufficientAuthenticationException");
		}
		
		//이제 토큰만들어주기
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub 2. 위에서 검증이 끝나면 우리가 전달한 객체에 토큰을 추가해주기
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
