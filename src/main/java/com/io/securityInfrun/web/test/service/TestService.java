package com.io.securityInfrun.web.test.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.web.test.mapper.TestMapper;

import jakarta.annotation.Resource;

@Service("testService")
public class TestService {
	
	@Resource(name="testMapper")
	public TestMapper testMapper;
	
	 public ArrayList<UISMap> selectTest() {
        
		 ArrayList<UISMap> check = null;
		 
		 check = testMapper.selectTest();
		 /*
		 try {
			 check = mapper.selectTest();
		 }
		 catch(Exception e) {
			 System.out.println("값 : [ |"+check+"| ]");
		 }
*/
		 return check; 
        
    }
	
}
