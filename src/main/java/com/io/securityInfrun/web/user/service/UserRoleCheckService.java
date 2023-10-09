/*
package com.io.securityInfrun.web.user.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.io.securityInfrun.web.user.vo.UserVo;

@Service("userRoleCheckService")
public class UserRoleCheckService extends User{
	
	
	@Autowired
	private final UserVo userVo;

	public UserRoleCheckService(UserVo userVo, Collection<? extends GrantedAuthority> authorities) {
		super(userVo.getUsername(), userVo.getPassword(), authorities);
		this.userVo = userVo;
	}

	public UserVo getUserVo() {
		return userVo;
	}
	
	
}*/