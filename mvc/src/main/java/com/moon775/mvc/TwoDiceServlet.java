package com.moon775.mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet = @Contoller + @RequestMapping
@WebServlet("/rollDice2")
public class TwoDiceServlet extends HttpServlet {	// HttpServlet을 상속받아야 하는데, Java는 단일상속이므로 안 받는 것이 좋으므로 Controller에서는 개선 
    int getRandomInt(int range) {
	return new Random().nextInt(range)+1;
    }
    
    // 서블릿에서는 service메서드가 고정
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idx1 = getRandomInt(6);
        int idx2 = getRandomInt(6);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<img src='resources/img/dice"+idx1+".jpg'>");
        out.println("<img src='resources/img/dice"+idx2+".jpg'>");
        out.println("</body>");
        out.println("</html>");
        out.close();	    
    }
}