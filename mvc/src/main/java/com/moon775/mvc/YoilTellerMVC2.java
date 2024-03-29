package com.moon775.mvc;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC2 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC2")
	public String main(@RequestParam(required=true) int year, 
    				   @RequestParam(required=true) int month, 
    				   @RequestParam(required=true) int day, Model m) throws IOException{
		
//		1. 유효성 검사
		if(!isValid(year, month, day)) {
			return "yoilError";
		}
		
//		2. 요일 계산
		char yoil = getYoil(year, month, day);
		
//		3. 계산한 결과를 model에 저장
		m.addAttribute("year", year);
		m.addAttribute("month", month);
		m.addAttribute("day", day);
		m.addAttribute("yoil", yoil);
		
		
		return "yoil";	// /WEB-INF/views/yoil.jsp
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
