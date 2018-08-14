package com.atguigu.sale.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.sale.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.sale.bean.OBJECT_SKU_DETAIL;
import com.atguigu.sale.bean.OBJECT_T_MALL_ATTR_VALUE;
import com.atguigu.sale.bean.OBJECT_T_MALL_SKU;
import com.atguigu.sale.bean.T_MALL_PRODUCT;
import com.atguigu.sale.service.SearchService;
import com.atguigu.sale.util.MyRedisRefreshUtil;

@Controller
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	/*
	 * 二级分类检索
	 */
	@RequestMapping(value="/goto_sku_by_class_2_id_and_attr_id_and_value_id/{class_2_id}/{class_2_name}",method=RequestMethod.GET)
	public String goto_sku_by_class_2_id_and_attr_id_and_value_id(@PathVariable int class_2_id, @PathVariable String class_2_name, ModelMap map,HttpServletRequest request) {
		System.out.println("goto_sku_by_class_2_id_and_attr_id_and_value_id");
		//从数据库进行二级分类检索
		List<OBJECT_T_MALL_SKU> list_object_sku = searchService.get_sku_by_class_2_id_and_attr_id_and_value_id(class_2_id,null,"",0);
		
		//从redis缓存中获取二级分类
		//List<OBJECT_T_MALL_SKU> list_object_sku_redis  = MyRedisRefreshUtil.test_redis_class_2(class_2_id);
		
		List<OBJECT_T_MALL_ATTR_VALUE> list_attr_value = searchService.get_attr_value_by_class_2_id(class_2_id);
		
		map.put("list_object_sku", list_object_sku);
		map.put("list_attr_value", list_attr_value);
		map.put("class_2_id", class_2_id);
		//map.put("class_2_name", class_2_name);
		request.setAttribute("class_2_name", class_2_name);
		
		return "sale_search";
	}
	
	/*
	 * 分类属性检索
	 */
	@RequestMapping(value="/get_sku_by_class_2_id_and_attr_id_and_value_id",method=RequestMethod.POST)
	public String get_sku_by_class_2_id_and_attr_id_and_value_id(String order_sql,int class_2_id,MODEL_T_MALL_SKU_ATTR_VALUE model_attr_value,ModelMap map) {
		//从数据库中进行分类属性检索
		List<OBJECT_T_MALL_SKU> list_object_sku = searchService.get_sku_by_class_2_id_and_attr_id_and_value_id(class_2_id, model_attr_value.getList_attr_value(),order_sql,0);
		
		//从redis缓存中获取分类属性
		//List<OBJECT_T_MALL_SKU> list_object_sku_redis = MyRedisRefreshUtil.test_redis_attr(model_attr_value.getList_attr_value(), class_2_id);
		
		map.put("list_object_sku", list_object_sku);
		return "sale_search_inner";
	}
	
	/*
	 * 商品详情
	 */
	@RequestMapping(value = "/sale_search_goto_sku_detail/{sku_id}/{spu_id}", method = RequestMethod.GET)
	public String sale_search_goto_sku_detail(@PathVariable int sku_id, @PathVariable int spu_id, ModelMap map) {
		Map<Object, Object> list_map = searchService.get_sku_detail_by_sku_id_and_spu_id(sku_id, spu_id);
		List<OBJECT_T_MALL_SKU> list_sku  = (List<OBJECT_T_MALL_SKU>) list_map.get("list_sku");
		OBJECT_SKU_DETAIL object_sku = (OBJECT_SKU_DETAIL) list_map.get("object_sku");
		map.put("list_sku", list_sku);
		map.put("object_sku", object_sku);
		return "sale_sku_detail";
	}
	
}
