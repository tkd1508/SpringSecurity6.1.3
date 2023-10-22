package com.io.securityInfrun.web.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.util.UISMapUtil;
import com.io.securityInfrun.web.user.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login.do", method =  {RequestMethod.GET, RequestMethod.POST})
    public String GetLogin(@RequestParam(value="error", required = false) String error, 
    		@RequestParam(value="exception", required = false) String exception, ModelMap model) {
		System.out.print("path : login.do \n");
		
		model.addAttribute("exception", exception);
		model.addAttribute("error", error);
        
		return "/login/login";
    }
	
	@RequestMapping(value = "/login-process.do")
	public String PostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		UISMap input = UISMapUtil.getData(req);
		System.out.print("path : login-process.do \n");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("input", input);
		model.addAttribute("isTokenValue", authentication);
		/*
		 * boolean isValidMember = memberService.isValidMember(dto.getUserid(),
		 * dto.getPw()); if (isValidMember) return "dashboard";
		 */
	    return "/index";
	}
	
	@RequestMapping(value = "/logout.do", method =  {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		System.out.print("path : logout.do \n");
		
		UISMap input = UISMapUtil.getData(req);
		UserVo userData = (UserVo) session.getAttribute("");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(req, res, authentication);
		}
		
		
		model.addAttribute("input", input);
		
		return "redirect:/";
	}
	
}
