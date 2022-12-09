package com.moon775.mvc;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
	public static void main(String[] args) throws Exception{
		
		// 1. YoilTeller클래스의 객체를 생성
		Class clazz = Class.forName("com.moon775.mvc.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// 2. 모든 메서드 정보를 가져와서 배열에 저장
		Method[] methodArr = clazz.getDeclaredMethods();
		
		for (Method m : methodArr) {
			String name = m.getName();	// 메서드의 이름
			Parameter[] paramArr = m.getParameters();	// 매개변수 목록
			Class returnType = m.getReturnType();	// 반환 타입
			
			StringJoiner paramList = new StringJoiner(", ", "(", ")");	// ", " : 구분자, "(" : 접두사, ")" : 접미사
			
			for (Parameter param : paramArr) {
				String paramName = param.getName();
				Class paramType = param.getType();
				
				paramList.add(paramType.getName() + " " + paramName);
			}
			
			System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
		}
		
	}
}
