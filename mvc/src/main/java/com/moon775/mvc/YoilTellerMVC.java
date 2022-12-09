package com.moon775.mvc;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC {
	@RequestMapping("/getYoilMVC")
	public String main(int year, int month, int day, Model m) throws IOException{
		
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
		return true;
	}

	private static char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month -1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일, 2:월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}
