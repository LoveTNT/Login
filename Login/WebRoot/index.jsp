<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%-- 
  查看session中是否存在user
  * 存在：显示用户名
  * 不存在：转发到login.jsp
   --%>
<c:choose>
	<c:when test="${empty sessionScope.session_user }">
		<c:set var="msg" value="您还没有登录，不要瞎遛达！" scope="request"/>
		<jsp:forward page="/login.jsp" />
	</c:when>
	<c:otherwise>
		<h2>欢迎${sessionScope.session_user.username }登录本系统</h2>
	</c:otherwise>
</c:choose>
  </body>
</html>
