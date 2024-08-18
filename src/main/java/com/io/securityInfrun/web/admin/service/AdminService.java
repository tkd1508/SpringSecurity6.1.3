package com.io.securityInfrun.web.admin.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.web.admin.mapper.AdminMapper;

import jakarta.annotation.Resource;


@Service("adminService")
public class AdminService {
	
	@Resource(name="adminMapper")
	public AdminMapper adminMapper;
	
	/*
	 * UrlPath 정보와 그에 따른 권한을 가져온다
	 * */
	public int updateAuthInfo() {
        
		ArrayList<UISMap> result = null;
		
		//result = adminMapper.updateAuthInfo();
		
		return 0; 
    }
	
	/*
	 * 권한 리스트 조회
	 * */
	public ArrayList<UISMap> selectRole() {
        
		ArrayList<UISMap> result = null;
		
		result = adminMapper.selectRole();
		return result; 
    }
	
}
