<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.moon775.mvc.*" %>
<%
	Person person = new Person();	//person은 지역변수
	request.setAttribute("person", person);	// request 객체에 저장 -> attribute (Map)에 저장
	request.setAttribute("name", "문관영");   
	request.setAttribute("list", new java.util.ArrayList());	
%>
<html>  
<head>   
	<title>EL</title>   
</head>  
<body>   
person.getCar().getColor()=<%=person.getCar().getColor()%> <br>
person.getCar().getColor()=${person.getCar().getColor()} <br>	<!-- request 객체에 저장되어 있지 않다면 el을 쓸 수 없음 -->
person.getCar().getColor()=${person.car.color} <br>    
name=<%=request.getAttribute("name")%> <br>   
name=${requestScope.name} <br>
name=${name} <br>
id=<%=request.getParameter("id")%> <br>
id=${pageContext.request.getParameter("id")} <br>
id=${param.id} <br>
"1"+1 = ${"1"+1} <br>
"1"+="1" = ${"1"+="1"} <br>
"2">1 = ${"2">1} <br>   
null = ${null}<br>
null+1 = ${null+1} <br>
null+null = ${null+null} <br>
"" + null = ${""+null} <br>   
""-1 = ${""-1} <br> 
empty null=${empty null} <br>
empty list=${empty list} <br>
null==0 = ${null==0} <br>
null eq 0 = ${null eq 0} <br>
name == "문관영"=${name=="문관영"} <br>
name != "문관영"=${name!="문관영"} <br>
name eq "문관영"=${name eq "문관영"} <br>  
name ne "문관영"=${name ne "문관영"} <br>  
name.equals("문관영")=${name.equals("문관영")} <br>   
</body>
</html>