package com.moon775.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	
	// main()과 main2()에서 중복된 try catch문 발생
	// 코드 중복 방지위해 별도의 예외처리해주는 메소드를 추가 후 @ExceptionHandler(예외처리할 종류)를 붙여줌
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)	// 200 -> 500
	public String catcher (Exception ex, Model m) {
//		m.addAttribute("ex", ex);
		return "error";
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String catcher2 (Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "error";
	}
	
	@RequestMapping("/ex")
	// 매개변수로 Model을 쓴다고 해도 catcher(Model m)의 model과는 별개의 객체임, model을 통해 데이터전달X
	public String main() throws Exception {
		throw new Exception("예외가 발생했습니다.");
	}
	
	@RequestMapping("/ex2")
	public String main2() throws Exception {
		throw new NullPointerException("예외가 발생했습니다.");
	}
}
