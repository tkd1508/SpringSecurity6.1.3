package com.io.securityInfrun.web.user.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.web.user.mapper.UserMapper;

import jakarta.annotation.Resource;


@Service("userService")
public class UserService {
	
	@Resource(name="userMapper")
	public UserMapper userMapper;
	
	/*
	 * 유저 정보 리스트 조회
	 * */
	public ArrayList<UISMap> userInfoSelect() {
        
		ArrayList<UISMap> result = null;
		
		result = userMapper.userInfoSelect();
		return result; 
    }
	
	/*
	 * 유저 정보 리스트 조회
	 * */
	public UISMap findUserInfo2(int id) {
        
		UISMap result = null;
		
		result = userMapper.findUserInfo2(id);
		return result; 
    }
	
	/*
	 * 유저 정보 insert
	 * */
	public int userInfoInsert(UISMap input) throws SQLException {
		int result = -1;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
		String test = input.getString("password");
		String test2 = passwordEncoder.encode(input.getString("password"));
		input.setString("password", passwordEncoder.encode(input.getString("password")));
		result = userMapper.userInfoInsert(input);
		return result; 
    }
	
	
	
	
}
