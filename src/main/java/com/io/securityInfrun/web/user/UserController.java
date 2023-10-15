package com.io.securityInfrun.web.user;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.util.UISMapUtil;
import com.io.securityInfrun.web.user.service.UserService;
import com.io.securityInfrun.web.user.vo.UserVo;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Resource(name="userService")
	UserService userService;
	
	@GetMapping("/user.do")
	public String user(HttpSession session, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		System.out.print("path : user.do \n");
		
		UISMap input = UISMapUtil.getData(req);
		UserVo userData = (UserVo) session.getAttribute("");
		
		String ip = req.getRemoteAddr();
		input.set("ip", ip);
		
		//String timeSet = 
		
		model.addAttribute("input", input);
		
		return "/user/userInfo";
	}
	
	@PostMapping("/userInfo.do")
	public String userInfo(HttpSession session, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		System.out.print("path : userInfo.do \n");
		
		UISMap input = UISMapUtil.getData(req);
		UserVo userData = (UserVo) session.getAttribute("");
		
		int result = -1;
		try {
			result = userService.userInfoInsert();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("Insert 성공!!");
		
		//model.addAttribute("input", input);
		
		return "/user/userInfo";
	}
}
