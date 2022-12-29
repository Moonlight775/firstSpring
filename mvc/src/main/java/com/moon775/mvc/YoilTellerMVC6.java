package com.moon775.mvc;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC6 {
	
	 /*
	 Query String의 데이터가 map 형식으로 구성됨
	 Controller 메서드에 전달
	 전달받는 타입이 MyDate 이므로 MyDate타입의 객체가 만들어짐
	 요청받은 Query String 값은 String이고 MyDate 객체에는 int로 저장 되어야 하므로 변환 필요
	 변환을 해주는 것이 WebDataBinder
	 WebDataBinder가 하는 일은 2가지, 1. 타입 변환  2. 데이터 검증
	 1. 타입 변환 : 타입이 일치하지 않을 경우 변환 후 BindingResult에 결과&에러 저장
	 2. 데이터 검증 : 조건이 맞는지 검사 후 BindingResult에 결과&에러 저장
	 BindingResult 값을 Controller에 넘겨줄 수 있음
	 BindingResult는 바인딩할 객체 바로 뒤에 온다. (ex. MyDate date, BindingResult result)
	*/
	
	// 테스트 시 브라우저에서 직접 url을 치고 들어가기 때문에 Controller 메서드까지 가지 못해서 에러핸들러로 처리
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		System.out.println("result = " + result);
		// BindingResult가 에러객체를 갖고 있어서 에러 객체로부터 무엇을 확인할 수 있는지 확인
		FieldError error = result.getFieldError();
		
		System.out.println("code = " + error.getCode());
		System.out.println("field = " + error.getField());
		System.out.println("msg = " + error.getDefaultMessage());
		
		ex.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC6")	// http://localhost/mvc/getYoilMVC6
	// public String main(@ModelAttribute("myDate") MyDate date, Model m)	// 아래와 동일
	public String main(MyDate date, BindingResult result) {
		System.out.println("result = " + result);
		
//		1. 유효성 검사
		if(!isValid(date)) {
			return "yoilError";
		}
		
//		4. 작업 결과를 보여줄 View의 이름을 반환
		return "yoil";	// /WEB-INF/views/yoil.jsp
	}

	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private static boolean isValid(int year, int month, int day) {
		if (year == -1 || month == -1 || day == -1) {
			return false;
		}
		
		return (1 <= month && month <= 12) && (1 <= day && day <= 31);	// 간단히 체크
	}

	private static char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month -1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일, 2:월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}
