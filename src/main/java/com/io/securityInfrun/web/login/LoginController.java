package com.io.securityInfrun.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.util.UISMapUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@GetMapping("/login.do")
    public String loginPage() {
        return "/login/login";
    }
	
	@PostMapping("/login-process.do")
	public String login(HttpSession session, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		UISMap input = UISMapUtil.getData(req);
		
		/*
		 * boolean isValidMember = memberService.isValidMember(dto.getUserid(),
		 * dto.getPw()); if (isValidMember) return "dashboard";
		 */
	    return "/index";
	}
}
