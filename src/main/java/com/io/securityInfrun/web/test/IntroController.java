package com.io.securityInfrun.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroController {

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
	
	@GetMapping("/k")
	public String getInts() {
		System.out.println("path: /k /n");
		return "index";
	}
	
	@GetMapping("/mypage")
	public String getInt23() {
		System.out.println("path: /mypage /n");
		return "index";
	}
	
	@GetMapping("/config")
	public String getInt45() {
		System.out.println("path: /config /n");
		return "index";
	}
	
	
	
}
