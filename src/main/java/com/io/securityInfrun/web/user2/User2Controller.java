package com.io.securityInfrun.web.user2;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.util.UISMapUtil;
import com.io.securityInfrun.web.user.vo.UserVo;
import com.io.securityInfrun.web.user2.service.User2Service;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class User2Controller {
	
	@Resource(name="user2Service")
	User2Service user2Service;
	
	@GetMapping("/user2.do")
	public String user(HttpSession session, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		System.out.print("path : user.do \n");
		
		UISMap input = UISMapUtil.getData(req);
		UserVo userData = (UserVo) session.getAttribute("");
		
		String ip = req.getRemoteAddr();
		input.set("ip", ip);
		
		//String timeSet = 
		
		model.addAttribute("input", input);
		
		return "/user2/user2Info";
	}
	
	@PostMapping("/user2Info.do")
	public String userInfo(HttpSession session, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		System.out.print("path : userInfo.do \n");
		
		UISMap input = UISMapUtil.getData(req);
		UserVo userData = (UserVo) session.getAttribute("");
		
		int result = -1;
		try {
			result = user2Service.user2InfoInsert();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("Insert 성공!!");
		
		//model.addAttribute("input", input);
		
		return "/user2/user2Info";
	}
	
	@RequestMapping(value = "/user3.do", method =  {RequestMethod.GET, RequestMethod.POST})
	public String user3(HttpSession session, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		System.out.print("path : user3.do \n");
		
		//model.addAttribute("input", input);
		
		return "user3";
	}
}
