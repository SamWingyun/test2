<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <c:forEach items="${list_object_sku }" var="object_sku">
        <div style="float:left;width:320px;height:200px;">
            <img alt="" src="${app_path }/upload/${object_sku.spu.shp_tp}">
			<br>
			<a href="http://www.baidu.com">${object_sku.sku_mch}</a><br>
			￥：${object_sku.jg}
		</div>
    </c:forEach>
</body>
</html>