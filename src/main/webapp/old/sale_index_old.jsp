<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${app_path }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		// 访问一级分类的js，然后将数据加载到一级分类的下拉选项中
		$.getJSON("js/json/class_1.js",function(data){
			//遍历json数组，加载到一级分类的下拉选项中
			$(data).each(function(i,json){
				$("#index_select_class_1").append("<li value="+json.id+" onmouseover='javascript:index_select_class_2_by_class_1(this.value);'>"+json.flmch1+"</li>");
			});
		}); 
	});
	
	function index_select_class_2_by_class_1(class_1_id){
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#index_select_class_2").empty();
			$(data).each(function(i,json){
				$("#index_select_class_2").append("<li value="+json.id+">"+'<a href=javascript:window.open(\'goto_sku_by_class_2_id_and_attr_id_and_value_id/'+json.id+'/'+json.flmch2+'.do\')>'+json.flmch2+'</a>'+"</li>");
			});
		});
	}
</script>
</head>
<body>
      <h2>商城首页</h2>
      <c:if test="${user==null}">
		 <h3><a href="goto_login.do" target="_blank">登录</a></h3>
	  </c:if>
	  <c:if test="${user!=null}">
		 <h3> 欢迎${user.yh_nch}&nbsp;&nbsp;<a href="logout.do" >注销</a></h3>
	  </c:if>
	  
	  <ul id="index_select_class_1" style="width:70px;">
	 
	  </ul>
	
	  <ul id="index_select_class_2" style="width:70px;">
		
	  </ul>
  
	
</body>
</html>