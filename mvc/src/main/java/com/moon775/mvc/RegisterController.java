package com.moon775.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	// ctrl+shift+o 자동 import
public class RegisterController {
	@RequestMapping("/register/add")
	public String register() {
		return "registerForm";	// WEB-INF/views/registerForm.jsp
	}
	
	@RequestMapping("/register/save")
	public String save() {
		return "registerInfo";
	}
}
