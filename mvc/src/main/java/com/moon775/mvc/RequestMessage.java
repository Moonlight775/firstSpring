package com.moon775.mvc;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMessage {
	@RequestMapping("/requestMessage")
	public void main(HttpServletRequest request) throws Exception {
		
		// 1. request line
		String requestLine = request.getMethod();       // GET 또는 POST
		requestLine += " " + request.getRequestURI();   // /mvc/requestMessage
		
		String queryString = request.getQueryString();  // year=2021&month=10&day=1
		requestLine += queryString == null ? "" : "?"+queryString;  
		requestLine += " " + request.getProtocol();     // HTTP/1.1
		System.out.println(requestLine);		

		
		// 2. request headers
		Enumeration<String> e = request.getHeaderNames();

		while (e.hasMoreElements()) {
			String name = e.nextElement();
			System.out.println(name + ":" + request.getHeader(name));
		}
		
		// 3. request body - POST일 때만 해당, GET은 body가 없음(CONTENT_LENGTH=0)
		final int CONTENT_LENGTH = request.getContentLength();
		System.out.println("content length="+CONTENT_LENGTH);
		
		if(CONTENT_LENGTH > 0) {
			byte[] content = new byte[CONTENT_LENGTH];

			InputStream in = request.getInputStream();
			in.read(content, 0, CONTENT_LENGTH);
			
			System.out.println(); // empty line
			System.out.println(new String(content, "utf-8")); // year=2021&month=10&day=1
		}  // if
	} // main
}

//[실행결과]
//GET /mvc/requestMessage?year=2021&month=10&day=1 HTTP/1.1
//host:localhost:8080
//connection:keep-alive
//sec-ch-ua:" Not;A Brand";v="99", "Google Chrome";v="97", "Chromium";v="97"
//sec-ch-ua-mobile:?0
//sec-ch-ua-platform:"Windows"
//upgrade-insecure-requests:1
//user-agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36
//accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
//sec-fetch-site:none
//sec-fetch-mode:navigate
//sec-fetch-user:?1
//sec-fetch-dest:document
//accept-encoding:gzip, deflate, br
//accept-language:ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7

