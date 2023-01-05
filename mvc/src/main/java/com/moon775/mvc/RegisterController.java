package com.moon775.mvc;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller	// ctrl+shift+o 자동 import
public class RegisterController {
	@RequestMapping(value="/register/add", method={RequestMethod.GET, RequestMethod.POST}) 
//  get요청과 post 요청을 둘 다 허용 (method를 생략해도 같은 의미)
	public String register() {
		return "registerForm";	// WEB-INF/views/registerForm.jsp
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/register/save")	// 스프링 4.3부터
	public String save(User user, Model m) throws Exception{
		// 1. 유효성 검사
		if (!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8");
			
			m.addAttribute("msg", msg);	// model에 담아서 넘겨도 됨
			return "forward:/register/add";
//			return "redirect:/register/add?msg="+msg;	// URL재작성(rewriting)
		}
		
		// 2. DB에 신규회원 정보를 저장
		
		
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}
}
