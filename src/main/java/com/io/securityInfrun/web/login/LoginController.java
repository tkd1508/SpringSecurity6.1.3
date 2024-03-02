package com.io.securityInfrun.web.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value = {"/denied.do","/api/denied.do"}, method =  {RequestMethod.GET})
	public String accessDenied(@RequestParam(value="exception", required = false) String exception, ModelMap model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		model.addAttribute("exception", exception);
		model.addAttribute("username", userDetails.getUsername());
		
		return "/login/denied";
	}
	
	@RequestMapping(value = "/api/messages.do", method =  {RequestMethod.GET})
	@ResponseBody
	public String apiMessage(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		;
		String kk = "권한 없음";
		
		/*
		if(authentication != null || authentication.getAuthorities().toString() != "[ROLE_ANONYMOUS]") {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			kk = userDetails.getAuthorities().toString();
		}*/
		
		System.out.println("■■■■■■■■■■■■■■22■■■■■■■■■■■■ "+authentication.getAuthorities().toString());
		return "ok messages";
	}
	
	@RequestMapping(value = "/api/messages2.do", method =  {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String apiMessage2() {
		
		return "ok messages22";
	}
	
	
	@RequestMapping(value = "/sample/ajaxTest", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ModelAndView ajaxTest() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("testJson", "ajaxTest");
		mv.setViewName("jsonView");
		return mv;
	}
	
}
