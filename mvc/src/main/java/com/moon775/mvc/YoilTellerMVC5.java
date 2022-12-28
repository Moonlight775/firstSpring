package com.moon775.mvc;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC5 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		return "yoilError";
	}
	
	// @ModelAttribute : 적용 대상을 Model의 속성으로 자동 추가해주는 애너테이션
	// 반환 타입 또는 컨트롤러 메서드의 매개변수에 적용 가능
	// @ModelAttribute가 있는 대상은 model에 자동 저장, key의 이름을 설정해주지 않으면 타입의 첫 글자를 소문자로 하는 이름으로 저장됨
	// 참조형 매개변수 앞에 @ModelAttribute는 생략가능

	// 컨트롤러 매개변수에 2가지 애너테이션이 붙을 수 있음. @RequestParam : 기본형, String 일때 생략 / @ModelAttribute : 참조형일때 생략
	
	@RequestMapping("/getYoilMVC5")	// http://localhost/mvc/getYoilMVC5
	// public String main(@ModelAttribute("myDate") MyDate date, Model m)	// 아래와 동일
	public String main(@ModelAttribute MyDate date, Model m) {
		
//		1. 유효성 검사
		if(!isValid(date)) {
			return "yoilError";
		}
		
//		2. 요일 계산
//		char yoil = getYoil(date);
		
//		3. 계산한 결과를 model에 저장
//		m.addAttribute("myDate", date);
//		m.addAttribute("yoil", yoil);
		
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
