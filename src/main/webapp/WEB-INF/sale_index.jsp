<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<script type="text/javascript" src='js/jquery-1.7.2.min.js'></script>
<script type="text/javascript">
	
	
	$(function(){
		// 访问一级分类的js，然后将数据加载到一级分类的下拉选项中
		$.getJSON("js/json/class_1.js",function(data){
			//遍历json数组，加载到一级分类的下拉选项中
			$(data).each(function(i,json){
				$("#index_select_class_1").append("<li value="+json.id+" onmouseover='javascript:index_select_class_2_by_class_1(this.value);'><a href='javascript:;' style=\'font-family:微软雅黑;font-size:18px;\'>"+json.flmch1+"</a></li><br>");
			});
		}); 
	});
	
	function index_select_class_2_by_class_1(class_1_id){
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#index_select_class_2").empty();
			$(data).each(function(i,json){
				$("#index_select_class_2").append("<a style=\'font-family:微软雅黑;font-size:15px;\' href='goto_sku_by_class_2_id_and_attr_id_and_value_id/"+json.id+"/"+json.flmch2+".do' target='_blank'>"+json.flmch2);
			});
			$("#index_select_class_2").show();
		});
	}
</script>
</head>
<body>

	<div class="top">
		<div class="top_text">
			<a href="">供应商注册</a>
			<a href="">供应商登录</a>
			<a href="">${user.yh_mch }</a>
		    <c:if test="${user==null}">
		        <a href="goto_login.do" target="_blank">用户登录</a>
	        </c:if>
			<c:if test="${user!=null}">
		        <a href="logout.do" >用户注销</a>
	        </c:if>
		</div>
	</div>
	
	<div class="search">
		<div class="logo"><img src="images/jingdong-logo.png" alt=""></div>
		<div class="search_on">
			<div class="se">
				<input type="text" name="search" class="lf">
				<input type="submit" class="clik" value="搜索">
			</div>
			
		</div>

       <!-- 引入mini购物车 -->
	   <jsp:include page="sale_miniCar.jsp"></jsp:include>
	
	</div>
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini">
						 <ul id="index_select_class_1" style="width:70px;">
							<li>
								<div class="two_nav" id="index_select_class_2">
									
								</div>
							</li>
		 	
	                     </ul>    
					</div>
				</div>
				<ul>
					<li><a href="">服装城</a></li>
					<li><a href="">美妆馆</a></li>
					<li><a href="">超市</a></li>
					<li><a href="">全球购</a></li>
					<li><a href="">闪购</a></li>
					<li><a href="">团购</a></li>
					<li><a href="">拍卖</a></li>
					<li><a href="">金融</a></li>
					<li><a href="">智能</a></li>
					<br>
				</ul>
				
			</div>
		</div>
		
	</div>
	
	   
	<div class="banner">
		<div class="ban">
			<img src="images/jdindex.jpg" width="980" height="380" alt="">
		</div>
	</div>
	
</body>
</html>