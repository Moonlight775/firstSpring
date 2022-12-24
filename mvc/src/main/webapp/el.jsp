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
name=${requestScope.name} <br>	<!-- request Map 이름이 requestScope -->
name=${name} <br>	<!-- Scope이 없으면 page부터 순서대로 찾음 -->
id=<%=request.getParameter("id")%> <br>
id=${pageContext.request.getParameter("id")} <br>	<!-- 바로 request. 를 사용할 수 없다. request는 lv이므로 EL에서는 사용할 수 없다. 그래서 pageContext를 앞에 써주어야 한다. -->
id=${param.id} <br>	<!-- EL은 null을 출력하지 않아 공백으로 출력된다. -->
"1"+1 = ${"1"+1} <br>	<!-- 계산 시 "1"이 숫자 1로 변경 -->
"1"+="1" = ${"1"+="1"} <br>
"2">1 = ${"2">1} <br>   
null = ${null}<br>
null+1 = ${null+1} <br>	<!-- 계산 시 null은 0으로 변경 -->
null+null = ${null+null} <br>
"" + null = ${""+null} <br>   <!-- 빈문자열도 0으로 변경 -->
""-1 = ${""-1} <br> 
empty null=${empty null} <br>	<!-- empty는 null, 빈 컬렉션 배열일 때 true -->
empty list=${empty list} <br>
null==0 = ${null==0} <br>
null eq 0 = ${null eq 0} <br>
name == "문관영"=${name=="문관영"} <br>
name != "문관영"=${name!="문관영"} <br>
name eq "문관영"=${name eq "문관영"} <br>  
name ne "문관영"=${name ne "문관영"} <br>  <!-- ne = not equals -->
name.equals("문관영")=${name.equals("문관영")} <br>   
</body>
</html>