package com.moon775.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. ���� ȣ�Ⱑ���� ���α׷����ε��
@Controller
public class Hello {
	// 2. URL�� �޼��带 ����
	@RequestMapping("/hello")
	public void main() {
		System.out.println("Hello");
	}
}
