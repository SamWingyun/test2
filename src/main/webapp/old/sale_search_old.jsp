<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${app_path }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
    function sale_search_attr_value_up(attr_id,value_id,attr_value_name){
    	//将属性id和属性值id以“json”的形式添加进隐藏的文本输入框
    	$("#sale_search_up_attr").append("<span id='sale_search_"+attr_id+"'><input type='hidden' value='{\"shxm_id\":"+attr_id+",\"shxzh_id\":"+value_id+"}'/><a href='javascript:sale_search_attr_value_down("+attr_id+","+value_id+",\""+attr_value_name+"\");'>"+attr_value_name+"</a></span>&nbsp;");
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
    	
    	parm = parm + "class_2_id="+class_2_id;
    	
    	$.ajax({
		    type:"post",
			url:"${app_path}/get_sku_by_class_2_id_and_attr_id_and_value_id.do",
			data:parm,
			success:function(data){
				$("#sale_search_inner").html(data);
			}
	    });
		
    }
</script>
</head>
<body>
    <div id="sale_search_up_attr">
       ${class_2_name }:
    </div>
    <hr>
    <c:forEach items="${list_attr_value }" var="attr_value">
       <div id="sale_search_down_attr_${attr_value.id }">
           ${attr_value.shxm_mch }:
           <c:forEach items="${attr_value.list_value }" var="val">
               <a href="javascript:sale_search_attr_value_up(${attr_value.id},${val.id },'${val.shxzh}${val.shxzh_mch}')">${val.shxzh}${val.shxzh_mch}</a>
           </c:forEach>
       </div>
    </c:forEach>
    
    <hr>
    <div id="sale_search_inner">
        <jsp:include page="sale_search_inner_old.jsp"></jsp:include>
    </div>

</body>
</html>