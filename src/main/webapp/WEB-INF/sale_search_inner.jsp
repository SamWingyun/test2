<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>检索</title>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<title>Insert title here</title>
</head>
<body>
    <div class="Sbox">
	    <c:forEach items="${list_object_sku }" var="object_sku">
	        <div style="float:left;width:200px;height:200px;">
	            <img  style="float:left;width:200px;height:150px;" alt="" src="${app_path }/upload/${object_sku.spu.shp_tp}"><br>
				<br>
				<a href="sale_search_goto_sku_detail/${object_sku.id }/${object_sku.shp_id}.do">${object_sku.sku_mch}</a><br>
				￥：${object_sku.jg}
			</div>
	    </c:forEach>

	</div>
</body>
</html>