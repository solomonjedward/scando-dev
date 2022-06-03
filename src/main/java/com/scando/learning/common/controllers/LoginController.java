package com.scando.learning.common.controllers;

import com.scando.learning.common.constants.ApiUrls;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {	
	
	private static final Map<String, String> apiSpecCredentialsMap = new HashMap<>();
	
	static {
		apiSpecCredentialsMap.put("developer", "Qwe@12345");
	}
    
    @GetMapping(ApiUrls.API_SPEC_LOGIN)
    public String apiSpecLogin(HttpServletRequest request) {     	
        return "api-spec-login";
    }
    
    @PostMapping(ApiUrls.API_SPEC_LOGIN)
    public String validateApiSpecLogin(HttpServletRequest request, Model model) {  
    	String userName = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	String userPwd = apiSpecCredentialsMap.get(userName);
    	if(userPwd == null || !userPwd.equals(password)) {
    		model.addAttribute("error", "Invalid Credentials");
    		return "api-spec-login";
    	}    	      
    	HttpSession session = request.getSession(true);
    	session.setAttribute("API_SPEC_AUTH_USER", "admin");
        return "redirect:/swagger-ui.html";
    }
}
