package com.io.securityInfrun.web.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.web.test.service.TestService;

import jakarta.annotation.Resource;

@Controller
public class TestController {

	@Resource(name = "testService")
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

		System.out.print("path : test.do \n");

		/*
		 * try { Connection conn = DriverManager.getConnection(
		 * "jdbc:mysql://localhost:3306/infunsecuritydb?characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
		 * "root", "Ksw3347!!");
		 * 
		 * Statement st = conn.createStatement(); ResultSet rs =
		 * st.executeQuery("show databases");
		 * 
		 * while(rs.next()) { System.out.println(rs.getString(1)); }
		 * 
		 * 
		 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
		 */
		
		try { 
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/infunsecuritydb?characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root", "Ksw3347!!");
			Statement st = conn.createStatement(); 
			ResultSet rs = st.executeQuery("show databases");
			while(rs.next()) { 
				System.out.println(rs.getString(1)); 
			}
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace();
		}
		
		
		ArrayList<UISMap> testList = testService.selectTest();
		System.out.print("result : " + testList);

		// model.addAttribute("test", testList);

		return "/intro";
	}
}
