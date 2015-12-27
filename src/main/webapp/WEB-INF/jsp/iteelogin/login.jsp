<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
  <head>
  	<%@ include file="../layout/htmlHeader.jsp"%>
    <title>iTEE管理后台</title>
   	<link rel="stylesheet" href="<%=path%>/css/iteelogin/login.css?t=20151206000"/>
  </head>
  <body>
  	<div class="itee-login">
  	  <div class="itee-login-title">ITEE后台管理</div>
  	  <div class="itee-login-form">
		  <form id="loginForm" action="<%=path%>/iteelogin/loginCheck.htm" method="post">
		  	<input type="hidden" name="notifyUrl" value="${notifyUrl}" />
		  	<div class="itee-login-username">
		  		<input id="userNameId" class="input" type="text" placeholder="输入账号" name="username" />
		  	</div>
		  	<div class="itee-login-password">
		  		<input id="passwordId" class="input" type="password" placeholder="输入密码" name="password" />
		  	</div>
		  	<div class="itee-login-error-msg">${error_msg}</div>
			<div class="blue-btn login-btn" onclick="clickSubmit();">登录</div>
		  </form>
	  </div>
	</div>
  </body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/iteelogin/login.js?t=20151206000"></script>
</html>