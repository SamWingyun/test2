<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单支付</title>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<script type="text/javascript" src='js/jquery-1.7.2.min.js'></script>
<script type="text/javascript">

	function sale_check_order_show_address(addr_id,shjr_dz,shjr){
		var a = "<input type='hidden' name = 'id' value="+addr_id+" /><input type='hidden' name = 'shjr_dz' value="+shjr_dz+" /><input type='hidden' name = 'shjr' value="+shjr+" />";
		var b = "收货地址："+shjr_dz;
		var c = "收货人："+shjr;
		$("#sale_check_order_show_address").empty();
		$("#sale_check_order_show_address").append(a+b+" "+c);
		
	}
</script>
</head>
<body>
				
		<center>
		    <h2>订单提交</h2>
		            收货人信息选择
			<div class="msg_addr">
			<c:forEach items="${addr_list }" var="addr">
			       <input type="radio" name="sale_check_order_address_radio" value="${addr.id}" onclick="sale_check_order_show_address(${addr.id},'${addr.shjr_dz}','${addr.shjr}')"/>
					收货地址：${addr.shjr_dz }
					收货人：${addr.shjr }
					<br>
			</c:forEach>
			</div>
		    
		    <form action="add_order.do" method="post">
				<c:forEach items="${order_list }" var="order">
						<c:forEach items="${order.order_info_list }" var="info">
								<img src="upload/${info.shp_tp }" style="height:80px;width:80px;"><br>
								商品名称 :${info.sku_mch }<br> 
								商品总金额：${order.zje  }元<br> 
								商品数量：${info.sku_shl }件<br>
					    </c:forEach>				
				</c:forEach>		
				应付金额：${list_car_sum }元
				<div id="sale_check_order_show_address" >
				
				</div>
		        <input type="submit" value="提交订单">
		   </form>
		</center>
</body>
</html>
