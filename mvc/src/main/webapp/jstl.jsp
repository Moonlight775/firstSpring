<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>JSTL</title>
</head>
<body>
<c:set var="to"   value="10"/>	<!-- EL은 lv사용X, 그래서 저장소에 저장 / map형식 key:"to", value:"10" -->
<c:set var="arr"  value="10,20,30,40,50,60,70"/>	<!-- 배열 -->
<c:forEach var="i" begin="1" end="${to}">
	${i}
</c:forEach>
<br>
<c:if test="${not empty arr}">
	<c:forEach var="elem" items="${arr}" varStatus="status">	<!-- status는 count와 index 멤버를 갖고 있음 -->
		${status.count}. arr[${status.index}]=${elem}<BR>	<!-- count는 1부터시작, index는 0부터 -->
	</c:forEach>
</c:if>	
<c:if test="${param.msg != null}">	<!-- http://localhost/mvc/jstl.jsp?msg=blahblah -->
	msg=${param.msg}	<!-- 태그가 해석되어 출력 -->
	msg=<c:out value="${param.msg}"/>	<!-- 태그 해석 없이 문자취급 -->
</c:if>
<br>
<c:if test="${param.msg == null}">메시지가 없습니다.<br></c:if>
<c:set var="age" value="${param.age}"/>	<!-- http://localhost/mvc/jstl.jsp?age=18 -->
<c:choose>
	<c:when test="${age >= 19}">성인입니다.</c:when>
	<c:when test="${0 <= age && age < 19}">성인이 아닙니다.</c:when>
	<c:otherwise>값이 유효하지 않습니다.</c:otherwise>
</c:choose>
<br>
<c:set var="now" value="<%=new java.util.Date() %>"/>
Server time is <fmt:formatDate value="${now}" type="both" pattern="yyyy/MM/dd HH:mm:ss"/>	
</body>
</html>