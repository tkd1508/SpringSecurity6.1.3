package com.io.securityInfrun.web.user.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

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
		
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IP를 찾을 수 없습니다.");
		}
		
		//result = userMapper.userInfoSelect();
		
		
		return result; 
    }
	
	/*
	 * 유저 정보 insert
	 * */
	public int userInfoInsert() throws SQLException {
		int result = -1;
		//ArrayList<UISMap> result2 = userMapper.userInfoSelect();
		result = userMapper.userInfoInsert();
		
		return result; 
    }
	
}
