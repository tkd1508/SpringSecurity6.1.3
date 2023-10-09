package com.io.securityInfrun.web.login.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.web.user2.mapper.User2Mapper;

import jakarta.annotation.Resource;

@Service("loginService")
public class LoginService {
	
	@Resource(name="user2Mapper")
	public User2Mapper user2Mapper;
	
	/*
	 * 유저 정보 리스트 조회
	 * */
	public ArrayList<UISMap> user2InfoSelect() {
        
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
	public int user2InfoInsert() throws SQLException {
		int result = -1;
		//ArrayList<UISMap> result2 = userMapper.userInfoSelect();
		result = user2Mapper.user2InfoInsert();
		
		return result; 
    }
	
}
