package com.io.securityInfrun.web.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.io.securityInfrun.web.user.mapper.UserMapper;
import com.io.securityInfrun.web.user.vo.UserVo;

import jakarta.annotation.Resource;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Resource(name="userMapper")
	public UserMapper userMapper;
	
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserVo users = userMapper.findUserInfo(email); // 로그인.jsp에서의 아이디(이메일) 값을 조회한다.
		if(users == null) {
			 throw new UsernameNotFoundException("email [" + email + "] not found");
		}
		
		//String test = passwordEncoder.encode(users.getPassword());
		
		System.out.println("**************CustomUserDetailsService***************");
		System.out.println("id : " + users.getRole());
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(users.getRole()));
		
		//UserRoleCheckService userRoleCheck = new UserRoleCheckService(users, roles);
		
		//return userRoleCheck;
		return User.builder()
                .username(users.getEmail())
                .password(users.getPassword())
                .roles(users.getRole())
                .build();
	}
	
	
}
