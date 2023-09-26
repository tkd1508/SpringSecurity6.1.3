package com.io.securityInfrun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityInfrun2ApplicationTests {

	@Test
	void contextLoads() {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "1234");
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("show databases");
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
