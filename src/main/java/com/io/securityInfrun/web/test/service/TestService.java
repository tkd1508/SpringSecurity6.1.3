package com.io.securityInfrun.web.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.securityInfrun.web.test.mapper.TestMapper;
import com.io.securityInfrun.web.test.vo.TestVo;

import jakarta.annotation.Resource;

@Service
public class TestService {
	
	public TestMapper mappera;
	
	 public List<TestVo> selectTest() {
        
		 List<TestVo> check = null;
		 
		 check = mappera.selectTest();
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
