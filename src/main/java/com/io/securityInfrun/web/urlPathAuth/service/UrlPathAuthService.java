package com.io.securityInfrun.web.urlPathAuth.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.web.urlPathAuth.mapper.UrlPathAuthMapper;

import jakarta.annotation.Resource;


@Service("urlPathAuthService")
public class UrlPathAuthService {
	
	@Resource(name="urlPathAuthMapper")
	public UrlPathAuthMapper urlPathAuthMapper;
	
	/*
	 * UrlPath 정보와 그에 따른 권한을 가져온다
	 * */
	public ArrayList<UISMap> setUrlPathAuth() {
        
		ArrayList<UISMap> result = null;
		
		result = urlPathAuthMapper.setUrlPathAuth();
		
		return result; 
    }
	
}
