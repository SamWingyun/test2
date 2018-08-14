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
     <center>
        <h4>确认支付</h4>
                        收货地址：${order_list[0].dzh_mch }<br>
                        收货人：${order_list[0].shjr }<br>
        <c:forEach items="${order_list }" var="order">
              <c:forEach items="${order.order_info_list }" var="order_info">
                                                         商品名称：${order_info.sku_mch }<br>
              </c:forEach>
        </c:forEach>
                         应付金额：${order_sum }元
        <form action="pay_order.do">
           <input type="submit" value="确认支付" />
        </form>
     </center>
</body>
</html>