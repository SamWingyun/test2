<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<script type="text/javascript" src="${app_path }/js/jquery-1.7.2.min.js"></script>
   <script type="text/javascript">
       function goto_add_car(){
    	  $("#formId").submit(); 
       }
   </script>
</head>
<body>
        
		${object_sku.sku_mch}
		<br>
		${object_sku.jg}
		<br>
		${object_sku.kc}
		<hr>
	    <c:forEach items="${list_sku}" var="sku">
		     <a href="sale_search_goto_sku_detail/${sku.id}/${object_sku.spu.id}.do">${sku.sku_mch}</a><br>
	    </c:forEach>
	    <hr>
		<c:forEach items="${object_sku.list_attr_value_name}" var="l_a_v_n">
			${l_a_v_n.attr_name}:${l_a_v_n.value_name}<br>
		</c:forEach>
		<hr>
		${object_sku.spu.shp_msh}<br>
		<c:forEach items="${object_sku.list_image}" var="image">
			<img alt="" src="upload/${image.url}" style="height:200px;width:200px">
		</c:forEach>
		<hr>
		
		
		<form id="formId" action="goto_add_car.do" method="post">
		     <input type="text" name="sku_mch" value="${object_sku.sku_mch }"/>
		     <input type="text" name="sku_jg" value="${object_sku.jg}" />
		     <input type="text" name="shp_id" value="${object_sku.spu.id}" />
		     <input type="text" name="sku_id" value="${ object_sku.id}" />
		     <input type="submit" />
		</form>
		<a href="javascript:goto_add_car();">添加购物车</a>

</body>
</html>