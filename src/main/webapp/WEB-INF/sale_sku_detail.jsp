<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
   <script type="text/javascript">
       function goto_add_car(){
    	  $("#carFormId").submit(); 
       }
       function goto_buy(){
    	  $("#buyFormId").submit(); 
       }
       
       function plus(){
    	   var num = $("#numbox").val();
    	   var intNum = parseInt(num);
    	   $("#numbox").val(1+intNum)
    	   $("#carNumId").val(1+intNum)
       }
       
       function minus(){
    	   var num = $("#numbox").val();
    	   if(num>1){
    	       $("#numbox").val(num-1)
    	       $("#carNumId").val(num-1)
    	   }
       }
       
       
   </script>
</head>
<body>
	<div class="top">
		<div class="top_text">
			<a href="">供应商注册</a>
			<a href="">供应商登录</a>
			<a href="">${user.yh_nch }</a>
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
					<div class="nav_mini" style="display:none">
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
				</ul>
			</div>
		</div>
	</div>
	
	<div class="Dbox">
		<div class="box">
			<div class="left">
				<div class="timg2">
					<div class="lf"><img src="images/lf.jpg" alt=""></div>
					<div class="center">
						<c:forEach items="${object_sku.list_image}" var="image">
					        <span><img alt="" src="upload/${image.url}" style="height:95px;width:140px"></span>
				       </c:forEach>
					</div>
					<div class="rg"></div>
				</div>
				<div class="goods"></div>
			</div>
			<div class="cent">
				<div class="title">${object_sku.sku_mch}</div>
				<div class="bg">
					<p style="font-size:15px">价格：<strong style="font-size:15px">${object_sku.jg}</strong></p>
					<p style="font-size:15px">库存：<strong style="font-size:15px">${object_sku.kc}</strong></p>
					<div class="clear">
						<div class="min_t" style="font-size:15px">&nbsp;&nbsp;产品详情：</div>
						<c:forEach items="${object_sku.list_attr_value_name}" var="l_a_v_n">
						    <div class="min_mx" style="font-size:15px" >${l_a_v_n.attr_name}:${l_a_v_n.value_name}<br></div>
					    </c:forEach>
					</div>
				    <p style="font-size:15px">产品描述：${object_sku.spu.shp_msh}<br></p>
				    <p style="font-size:15px">发货地址：${object_sku.dz}<br></p>
				    <p style="font-size:15px">库存数量：${object_sku.kc}<br></p>
			
				</div>
				
			
				<div class="clear" style="margin-top:20px;">
					<div class="min_t" style="line-height:36px">数量：</div>
					<div class="num_box">
						<input type="text" id="numbox"  name="num" value="1" style="width:40px;height:32px;text-align:center;float:left">
						<div class="rg">
							<img src="images/jia.jpg" id='jia' alt="" onClick="plus()">
							<img src="images/jian.jpg" id='jian' alt="" onClick="minus()">
						</div>
					</div>
				<br><br><br>
				</div>
				
				<!-- 购物车的订单表 -->
				<form id="carFormId" action="goto_add_car.do" method="post">
				     <input type="hidden" name="sku_mch" value="${object_sku.sku_mch }"/>
				     <input type="hidden"  name="sku_jg" value="${object_sku.jg}" />
				     <input type="hidden"  name="shp_id" value="${object_sku.spu.id}" />
				     <input type="hidden"  name="sku_id" value="${ object_sku.id}" />
				     <input type="hidden"  name="shp_tp" value="${ object_sku.spu.shp_tp}" />
				     <input type="hidden"  name="kc_dz" value="${ object_sku.dz}" />
				     <input type="hidden"  name="shfxz" value="1" />
				     <input id="carNumId" type="hidden"  name="tjshl" value="1" />
				     <input type="hidden"  style="display:none"/>
				</form>
				<!-- 购买的订单表 -->
				<form id="buyFormId" action="goto_buy.do" method="post">
				     <input type="hidden" name="sku_mch" value="${object_sku.sku_mch }"/>
				     <input type="hidden"  name="sku_jg" value="${object_sku.jg}" />
				     <input type="hidden"  name="shp_id" value="${object_sku.spu.id}" />
				     <input type="hidden"  name="sku_id" value="${ object_sku.id}" />
				     <input type="hidden"  style="display:none"/>
				</form>
				
		        <button style="height:35px;width:150px;color:red;"><a style="font-size:18px;color:red;" href="javascript:goto_buy();" >立即购买</a></button>
		        <button style="height:35px;width:150px;color:red;"><a style="font-size:18px;color:red;" href="javascript:goto_add_car();" >加入购物车</a></button>
			
			</div>
		</div>
	</div>
	<div class="Dbox1">
		<div class="boxbottom">
			<div class="top">
				<span>商品详情</span>
				<span>评价</span>
			</div>
			<div class="btm">
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="images/foot.jpg" alt=""></div>
	</div>
</body>
</html>