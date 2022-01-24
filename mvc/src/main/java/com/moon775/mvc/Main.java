package com.moon775.mvc;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws Exception{
//		Hello hello = new Hello();
//		hello.main();
		
		// Reflection API�� ��� - Ŭ���� ������ ��� �ٷ� �� �ִ� ������ �������
		// java.lang.reflct��Ű���� ����
		// Hello Ŭ������ Class��ü(Ŭ������������ ��� �ִ� ��ü)�� ���´�.
		Class helloClass = Class.forName("com.moon775.mvc.Hello");
		Hello hello = (Hello)helloClass.newInstance(); // Class��ü�� ���� ������ ��ü ����
		Method main = helloClass.getDeclaredMethod("main");
		main.setAccessible(true); // private�� main()�� ȣ�Ⱑ���ϰ� �Ѵ�.
		
		main.invoke(hello); // hello.main()
	}

}
