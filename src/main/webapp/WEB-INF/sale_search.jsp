<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>分类检索</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	    function sale_search_attr_value_up(attr_id,value_id,attr_value_name){
	    	//将属性id和属性值id以“json”的形式添加进隐藏的文本输入框
	    	$("#sale_search_up_attr").append("<span id='sale_search_"+attr_id+"'><input type='hidden' value='{\"shxm_id\":"+attr_id+",\"shxzh_id\":"+value_id+"}'/><a style=\"font-size:20px\" href='javascript:sale_search_attr_value_down("+attr_id+","+value_id+",\""+attr_value_name+"\");'>"+attr_value_name+"</a></span>&nbsp;");
	    	$('#sale_search_down_attr_'+attr_id).hide();
	    	sale_search_get_sku_by_attr_id_and_value_id();
	    }
	    
	    function sale_search_attr_value_down(attr_id,value_id,attr_value_name){
			$("#sale_search_"+attr_id).remove();
			$("#sale_search_down_attr_"+attr_id).show();
			sale_search_get_sku_by_attr_id_and_value_id();
		}
	    
	    function sale_search_get_sku_by_attr_id_and_value_id(){
	
	    	//放在域中的class_2_id
	    	var class_2_id = ${class_2_id};
	    	
	    	var parm = "";
	    	
	    	$("#sale_search_up_attr input").each(function(i,json_input){
				var json_object = $.parseJSON(json_input.value);
				parm = parm + "list_attr_value["+i+"].shxm_id="+json_object.shxm_id+"&";
				parm = parm + "list_attr_value["+i+"].shxzh_id="+json_object.shxzh_id+"&";
			});
	    	
	    	var order_sql = $("#sale_search_order").val();
	    	parm = parm + "class_2_id="+class_2_id + "&order_sql=" + order_sql;
	    	
	    	$.ajax({
			    type:"post",
				url:"${app_path}/get_sku_by_class_2_id_and_attr_id_and_value_id.do",
				data:parm,
				success:function(data){
					$("#sale_search_inner").html(data);
				}
		    });
			
	    }
	    
	    function sale_search_order(order_sql){
	    	var input_order = $("#sale_search_order").val();
		
			if(order_sql==input_order){
				$("#sale_search_order").val(order_sql+" desc ");
			}else{
				$("#sale_search_order").val(order_sql);
			}
			
			sale_search_get_sku_by_attr_id_and_value_id();
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
	

	<div class="filter">
		<h2> <div id="sale_search_up_attr"  > ${class_2_name }:&nbsp;&nbsp;</div> </h2>
	</div>
	
	<div class="Sscreen">
		  <c:forEach items="${list_attr_value }" var="attr_value">
             <div style="font-size:18px" id="sale_search_down_attr_${attr_value.id }">
               ${attr_value.shxm_mch }:
               <c:forEach items="${attr_value.list_value }" var="val">
                   <a style="font-size:16px" href="javascript:sale_search_attr_value_up(${attr_value.id},${val.id },'${val.shxzh}${val.shxzh_mch}')">${val.shxzh}${val.shxzh_mch}</a>&nbsp;
               </c:forEach>
               <hr>
            </div>
         </c:forEach>
         <br>		
		<div class="list">
			<span class="list_span">
			<a style="font-size:15px" href="javascript:sale_search_order(' order by jg ');" >价格</a></span>
			<span class="list_span" >
			<a style="font-size:15px" href="javascript:sale_search_order(' order by sku_xl ');" >销量</a></span>
			</span>
			<span class="list_span">
			<a style="font-size:15px" href="javascript:sale_search_order(' order by a.chjshj ');" >时间</a></span>
			<span class="list_span">
			<a style="font-size:15px" href="javascript:void(0);" >评论</a></span></span>
			</span>
			<input type="hidden" id="sale_search_order"  value=" order by jg "/>
		</div>
	</div>
	
	 <div id="sale_search_inner">
        <jsp:include page="sale_search_inner.jsp"></jsp:include>
     </div>


	<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="images/foot.jpg" alt=""></div>
	</div>
</body>
</html>