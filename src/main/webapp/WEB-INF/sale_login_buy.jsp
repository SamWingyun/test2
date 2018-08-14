<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>购买登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<meta charset="UTF-8">
<title>登录页面</title>
<meta name="Keywords" content="关键词123">
<meta name="Description" content="描述">
<link rel="stylesheet" type="text/css" href="css/login_buy.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script src="js/checkcode.js"></script>
<style type="text/css">
	#body{
		background: #e3e0e0;
		opacity: .8;
	}
</style>
<script type="text/javascript">
    function login_buy_(){
    	$("#login_form").submit();
    }
</script>
</head>
<body id="body">
<div id="content">
  <div class="login-wrap">
  	<div class="w">
  		<div class="login-form" align="center">
  			<div class="login-tab login-tab-l">
  				<a href="javascript:;">会员登录</a>
  			</div>
  			<div class="login-box" style="visibility: visible; display:block">
  			  <div class="mt tab-h"></div>
  			  <!-- 登录提示信息开始 -->
  			  <div class="msg-wrap">
  			  	  <div class="msg-warn" style="height:10px;">
  					  <b></b>
  					  ${msg }
  				  </div>
  				  <div class="msg-error hide">
  					  <b></b>
  				  </div>
  			  </div>
  			  <!-- 登录提示信息结束 -->
  			  <div class="mc">
  			  	<div class="form">
  			  		<form action="login_buy.do" id="login_form" method="post" >
  			  			
  			  			<!-- 用户名输入框fore1 -->
  			  			<div class="item item-fore1 item-error">
  			  				<label for="loginname" class="login-label name-label"></label>
  			  				<input type="text" name="username" id="username" class="itxt" tabindex="1" autocomplete="off" placeholder="邮箱/用户名/已验证手机">
  			  				<span class="clear-btn" style="display:inline;"></span>
  			  			</div>
  			  			<!-- 密码输入框fore2 -->
  			  			<div id="entry" class="item item-fore2" style="visibility: visible">
  			  				<label class="login-label pwd-label" for="nloginpwd"></label>
  			  				<input type="password" name="password" id="password" class="itxt itxt-error" tabindex="2" autocomplete="off" placeholder="密码">
  			  				<span class="clear-btn" style="display: inline;"></span>
  			  			</div>
  			  		    <br><br>
                        <!-- 登录按钮开始 -->
                        <div class="item item-fore5">
                        	<div class="login-btn">
                        		<a href="javascript:login_buy_();" class="btn-img btn-entry" id="loginsubmit" tabindex="6" ">登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
                        	</div>
                        </div>
  			  		</form>
  			  	</div>
  			  </div>
  				
  			</div>
  			
  			<div class="coagent" id="kbCoagent">
  				<ul>
  					<li>
  						<a href="javascript:void(0)" onClick="return false;" class="pdl">
  							<b class="QQ-icon"></b>
  							<span>QQ</span>
  						</a>
  						<span class="line">|</span>
  					</li>
  					<li>
  						<a href="javascript:void(0)" onClick="return false;" class="pdl">
  							<b class="weixin-icon"></b>
  							<span>微信</span>
  						</a>
  						<span class="line">|</span>
  					</li>
  					<li>
  						<a href="javascript:void(0)" onClick="return false;">
  							京东钱包
  						</a>
  						<span class="line">|</span>
  					</li>
  					<li class="extra-r">
  					   <div class="regist-link">
  						<a href="javascript:void(0)" onClick="return false;" class="">
  							<b></b><a href="goto_regist.do">立即注册</a>
  						</a>
  					   </div>
  					</li>
  				</ul>
  			</div>
  		</div>
  	</div>
	
</body>
</html>