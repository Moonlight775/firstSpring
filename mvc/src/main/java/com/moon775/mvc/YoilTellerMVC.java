package com.moon775.mvc;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC {
	@RequestMapping("/getYoilMVC")
	public ModelAndView main(int year, int month, int day) throws IOException{
//		1. ModelAndView를 생성하고, 기본 뷰를 지정
		ModelAndView mv = new ModelAndView();
		mv.setViewName("yoilError");	// 뷰의 이름을 지정
		
//		2. 유효성 검사
		if(!isValid(year, month, day)) {
			return mv;
		}
		
//		3. 요일 계산
		char yoil = getYoil(year, month, day);
		
//		4. ModelAndView에 작업한 결과를 저장
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("yoil", yoil);
		
//		5. 결과를 보여줄 view를 지정
		mv.setViewName("yoil");
		
//		6. ModelAndView를 반환
		return mv;
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
