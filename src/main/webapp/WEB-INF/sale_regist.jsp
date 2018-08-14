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
<link rel="stylesheet" href="css/sign.css"/>
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
<!--头部-->
    <div class="header">
        <a class="logo" href="##"></a>
        <div class="desc">欢迎注册</div>
    </div>
    <!--版心-->
    <div class="container">
    <form action="regist.do" id="regist_form" method="post">
    	<!--京东注册模块-->
    	<div class="register">
    		<!--用户名-->
    		<div class="register-box">
    			<!--表单项-->
    			<div class="box default" style="font-size:18px;font-family:微软雅黑;">
    				<label for="userName">用&nbsp;户&nbsp;名&nbsp;称</label>
    				<input type="text" id="username" name="username" placeholder="您的账户名和登录名" />
    				<i></i>
    			</div>
    			<!--提示信息-->
    			<div class="tip" >
    				<i></i>
    				<span style="color:red;font-size:16px;">${msg}</span>
    			</div>
    		</div>
    		<!--设置密码-->
    		<div class="register-box">
    			<!--表单项-->
    			<div class="box default" style="font-size:18px;font-family:微软雅黑;">
    				<label for="pwd" >设 置 密 码</label>
    				<input type="password" id="password" name="password" placeholder="建议至少两种字符组合" />
    				<i></i>
    			</div>
    			<!--提示信息-->
    			<div class="tip">
    				<i></i>
    				<span></span>
    			</div>
    		</div>
    		<!--确认密码-->
    		<div class="register-box">
    			<!--表单项-->
    			<div class="box default" style="font-size:18px;font-family:微软雅黑;">
    				<label for="pwd2">确 认 密 码</label>
    				<input type="password" id="surepass" name="surepass" placeholder="请再次输入密码" />
    				<i></i>
    			</div>
    			<!--提示信息-->
    			<div class="tip">
    				<i></i>
    				<span></span>
    			</div>
    		</div>
			<!--设置密码-->
    		<div class="register-box">
    			<!--表单项-->
    			<div class="box default" style="font-size:18px;font-family:微软雅黑;">
    				<label for="email">邮 箱 验 证</label>
    				<input type="text" id="email" name="email" placeholder="请输入邮箱" />
    				<i></i>
    			</div>
    			<!--提示信息-->
    			<div class="tip">
    				<i></i>
    				<span></span>
    			</div>
    		</div>
    		<!--手机验证-->
    		<div class="register-box">
    			<!--表单项-->
    			<div class="box default" style="font-size:18px;font-family:微软雅黑;">
    				<label for="mobile">手 机 验 证</label>
    				<input type="text" id="mobile" name="mobile" placeholder="请输入手机号" />
    				<i></i>
    			</div>
    			<!--提示信息-->
    			<div class="tip">
    				<i></i>
    				<span></span>
    			</div>
    		</div>
    		 <!--注册协议-->
    		<div class="register-box xieyi">
    			<!--表单项-->
    			<div class="box default">
    				<input type="checkbox" id="ck" />
    				<span>我已阅读并同意<a href="##">《京东用户注册协议》</a></span>
    			</div>
    			<!--提示信息-->
    			<div class="tip">
    				<i></i>
    				<span></span>
    			</div>
    		</div>
    		<!--注册-->
    		<button id="regist_form">注册</button>
    	</div>
    	
    </form>
    	
    </div>
</body>
</html>