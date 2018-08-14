<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加成功</title>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="css/css.css"/>
<link rel="stylesheet" type="text/css" href="css/finishCart.css"/>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
   <script type="text/javascript">
   </script>
</head>
<body>

     <h3>订单详情</h3>
     <c:forEach items="${order_list }" var="order">
                                 总金额：${order.zje }
           <c:forEach items="${order.order_info_list }" var="info">
                ${info.sku_mch } 
                ${info.sku_jg }
                ${info.sku_kcdz }
           </c:forEach>
           <br>
     </c:forEach>

</body>
</html>