package com.io.securityInfrun.web.test;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroController {
	
    private PasswordEncoder passwordEncoder;
    
    

	@GetMapping("/intro.do")
	public String getIntro() {
		System.out.println("찍히니? [/intro.do] /n");
		return "intro";
	}
	
	@GetMapping("/intro2.do")
	public String getInt() {
		System.out.println("찍히니? /n");
		return "intro";
	}
	
	@GetMapping("/main")
	public String getInt22() {
		System.out.println("path: /main /n");
		return "index";
	}
	
	@GetMapping("/")
	public String getInts() {
		System.out.println("path: / /n");
		
		
		
		
		//System.out.println("암호화: /k /n" + passwordEncode());
		
		
		
		
		return "/index";
	}
	
	
   String passwordEncode() {
      // given
      String rawPassword = "12345678"; // VO 생성자에다가 넣어 or 회원가입 로직에 넣던가

      // when
      String encodedPassword = passwordEncoder.encode(rawPassword);
      
      //passwordEncoder.matches(rawPassword, encodedPassword);

      return encodedPassword;
   } 
	
	@GetMapping("/mypage")
	public String getInt23() {
		System.out.println("path: /mypage /n");
		//System.out.println("암호화: /k /n" + passwordEncode());
		return "index";
	}
	
	@GetMapping("/config")
	public String getInt45() {
		System.out.println("path: /config /n");
		return "index";
	}
	
	
	
}
