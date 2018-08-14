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
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function login_to_submit() {
		$("#login_form").submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城登录</title>
</head>
<body>
	<div class="up">
			<img src="images/jingdong-logo.png" height="60px;" class="hy_img"/>
			<div class="hy">
				欢迎登录
			</div>
		</div>
		<div class="middle">
			<div class="login">
				<div class="l1 ">
					<div class="l1_font_01 ">会员登录</div>
					<a class="l1_font_02 " href="<%=application.getContextPath() %>/goto_regist.do">用户注册</a>
				</div>
				<div class="blank_01"></div>
				<div class="ts">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${msg}
				</div>
				<div class="blank_01"></div>
				<form action="login.do" id="login_form" method="post">
						<div class="input1">
							<input type="text" class="input1_01" name="username"  placeholder="用户名称"/>
						</div>
						<div class="blank_01"></div>
						<div class="blank_01"></div>
						<div class="input2">
							<input type="password" class="input1_01" name="password"  placeholder="用户密码"/>
						</div>
					
					<div class="blank_01"></div>
					<div class="blank_01"></div>
					<div class="box_01">
						<input type="checkbox" value="1" name="login_auto_login" class="box_01_box"/>
						<div class="box_01_both">
							<div class="box_01_both_1">自动登陆</div>
							<div class="box_01_both_2">忘记密码？</div>
						</div>
					</div>
				</form>
				<div class="blank_01" ></div>
				<a href="javascript:login_to_submit();" class="aline">
					<div class="red_button" >
						登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
					</div>
				</a>
				<div class="blank_01"></div>
				<div class="blank_01"></div>
				<div class="box_down">
					<div class="box_down_1">使用合作网站账号登录京东：</div>
					<div class="box_down_1">京东钱包&nbsp;&nbsp;|&nbsp;&nbsp;QQ 
					&nbsp;&nbsp;|&nbsp;&nbsp;微信
					</div>
				</div>
			</div>	
		</div>
		
		<!-- <div class="down">
			<br />
			Copyright©2004-2015  SamWingyun.com 版权所有
		</div> -->
</body>
</html>