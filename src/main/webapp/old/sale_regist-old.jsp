<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="css/regist.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function login_to_submit() {
		$("#regist_form").submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城注册</title>
</head>
<body>
	<div class="up">
			<img src="images/jingdong-logo.png" height="60px;" class="hy_img"/>
			<div class="hy">
				欢迎注册
			</div>
		</div>
		<div class="middle">
			<div class="login">
				<div class="l1 ">
					<div class="l1_font_01 ">会员注册</div>
				</div>
				<div class="blank_01"></div>
				<div class="ts">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${msg}
				</div>
				<div class="blank_01"></div>
				<form action="regist.do" id="regist_form" method="post">
						<div class="input1">
							<input type="text" class="input1_01" name="username" placeholder="用户名称"/>
						</div>
						<br>
						<div class="input2">
							<input type="password" class="input1_01" name="password" placeholder="用户密码"/>
						</div>
						<br>
						<div class="input3">
							<input type="password" class="input1_01" name="surepass" placeholder="确认密码"/>
						</div>
						<br>	
				</form>
				<div class="blank_01" ></div>
				<a href="javascript:login_to_submit();" class="aline">
					<div class="red_button" >
						注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册
					</div>
				</a>
				
</body>
</html>