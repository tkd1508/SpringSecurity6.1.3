package com.io.securityInfrun.web.login;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping(value="/user")
    public UserDetails restUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @GetMapping(value="/manager")
    public UserDetails restManager(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @GetMapping(value="/admin")
    public UserDetails restAdmin(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
    
    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        
    	response.sendRedirect("login");
    }
}
