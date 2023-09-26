package com.io.securityInfrun.web.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.securityInfrun.web.test.service.TestService;
import com.io.securityInfrun.web.test.vo.TestVo;

import jakarta.annotation.Resource;

@RestController
public class TestController {
	
	@Resource(name="testService")
	TestService testService;
	
//	@RequestMapping(value = "/test")
//	public ModelAndView test() throws Exception{
//	    ModelAndView mav = new ModelAndView("test");
//
//	    List<TestVo> testList = testService.selectTest();
//	    mav.addObject("list", testList);
//
//	    return mav;
//	}
	
	@GetMapping("/test.do")
	public String getIntro(Model model) {
		
		System.out.print("testStart");
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC", "root", "Ksw3347!!");
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("show databases");
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		/*
		List<TestVo> testList = testService.selectTest();*/
		System.out.print("testEnd");
		
		//model.addAttribute("test", testList);
		
		return "intro";
	}
}
