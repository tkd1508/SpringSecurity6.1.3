package com.io.securityInfrun.web.test;

import java.sql.*;

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
		
		Connection conn = null; // Connection 객체 생성 DB 연결
		String driver = "com.mysql.cj.jdbc.Driver"; 
		String url = "jdbc:mysql://localhost:3306/infunsecuritydb?characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		
		Boolean connect = false;
		
		try{
			Class.forName(driver); // JDBC 드라이버 로딩 Class.forName() 객체 사용
			conn = DriverManager.getConnection(url,"root","0000"); // 드라이버와 url(주소), 계정아이디, 비번
			// 드라이버를 로딩해주는 역활도 해주지만 더 중요한 Connection 객체를 생성해 준다. 
	        // 객체가 생성되면 데이터베이스에 연결이 된다.
			connect = true;
			
			conn.close();
			
		}catch(Exception e){
			connect = false;
			e.printStackTrace();
		}
		
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
