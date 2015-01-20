<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function _change() {
	/*
	1.获取<img>元素 
	*/
	var imgEle = document.getElementById("verifyImg");
	imgEle.src = "<c:url value='/VerifyCodeServlet'/>?" + new Date().getTime();
}

/*
 * 对表单中的每个项都进行校验，只要有一项有错误，就返回false
 */
function check() {
	var form = document.getElementById("form1");//得到表单元素
	
	// 校验用户名
	// 得到username项的值
	var val = form.username.value;
	if(!val) {
		alert("用户名不能为空!");
		return false;
	} else if(val.length < 2 || val.length > 10) {
		alert("用户名长度必须在2 ~ 10之间!");
		return false;
	}
	
	// 校验密码
	val = form.password.value;
	if(!val) {
		alert("密码不能为空!");
		return false;
	} else if(val.length < 2 || val.length > 10) {
		alert("密码长度必须在2 ~ 10之间!");
		return false;
	}
	
	// 校验确认密码
	val = form.repassword.value;
	if(!val) {
		alert("确认密码不能为空!");
		return false;
	} else if(val != form.password.value) {
		alert("两次密码输入不一致！");
		return false;
	}

	// email校验
	val = form.email.value;
	if(!val) {
		alert("Email不能为空!");
		return false;
	} else if(val.indexOf("@") == -1) {
		alert("错误的Email格式！");
		return false;
	}
	
	// 校验验证码
	val = form.verifyCode.value;

	if(!val) {
		alert("验证码不能为空!");
		return false;
	} else if(val.length != 4) {
		alert("验证码错误！");
		return false;
	}
	return true;
	}
	
</script>
  </head>
  
  <body>
<h1>注册页面</h1>
<%--
1. 请求RegistServlet
2. js表单校验
3. 错误信息的显示
4. 表单数据的回显  
--%>
<p style="color:red; font-weight: 900;">${msg }</p>
<form id="form1" action="<c:url value='/RegistServlet'/>" method="post">
用户名：　<input type="text" name="username" value="${form.username }"/>
<span style="color:red; font-weight: 900;">${errors.username }</span>
<br/>
密　码：　<input type="password" name="password" value="${form.password }"/>
<span style="color:red; font-weight: 900;">${errors.password }</span>
<br/>
确认密码：<input type="password" name="repassword" value="${form.repassword }"/>
<span style="color:red; font-weight: 900;">${errors.repassword}</span>
<br/>
邮　箱：　<input type="text" name="email" value="${form.email }"/>
<span style="color:red; font-weight: 900;">${errors.email }</span>
<br/>
验证码：　<input type="text" name="verifyCode" size="3" value="${form.verifyCode }"/>
<img src="<c:url value='/VerifyCodeServlet'/>" id="verifyImg"/>
<a href="javascript:_change()">换一张</a>
<span style="color:red; font-weight: 900;">${errors.verifyCode }</span>
<br/>
<input type="submit" value="注册"/>
</form>
  </body>
</html>
