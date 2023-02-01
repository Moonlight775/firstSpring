package com.moon775.mvc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller	// ctrl+shift+o 자동 import
public class RegisterController {

	@InitBinder
	public void toDate(WebDataBinder binder) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));	// false는 빈 값 허용 유무
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));	// "#":구분자
//		binder.setValidator(new UserValidator());	// UserValidator를 WebDataBinder의 로컬 validator로 등록
//		binder.addValidators(new UserValidator());	// validator를 WebDataBinder에 등록, 글로벌 Validator와 로컬 Validator를 동시에 적용, set이 아닌 add를 쓰는 이유는 UserValidator()를 추가하는 개념이기 때문
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList = " + validatorList);
	}
	
	@RequestMapping(value="/register/add", method=RequestMethod.GET)	// get요청 허용 (method를 생략하면 get, post 둘 다 허용) 
	public String register() {
		return "registerForm";	// WEB-INF/views/registerForm.jsp
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/register/add")	// 스프링 4.3부터
	public String save(@Valid User user, BindingResult result, Model m) throws Exception{
		System.out.println("result = " + result);
		System.out.println("user = " + user);
		
		// 수동 검증 - Validator를 직접 생성하고, validate()를 직접 호출
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result);	// BindingResult는 Errors의 자손
		
		// User객체를 검증한 결과 에러가 있으면, registerForm을 이욯해서 에러를 보여줘야 함
		if (result.hasErrors()) {
			return "registerForm";
		}
		
//		// 1. 유효성 검사
//		if (!isValid(user)) {
//			String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8");
//			
//			m.addAttribute("msg", msg);	// model에 담아서 넘겨도 됨
//			return "forward:/register/add";
////			return "redirect:/register/add?msg="+msg;	// URL재작성(rewriting)
//		}
		
		// 2. DB에 신규회원 정보를 저장
		
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
