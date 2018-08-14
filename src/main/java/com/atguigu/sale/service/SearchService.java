package com.atguigu.sale.service;

import java.util.List;
import java.util.Map;

import com.atguigu.sale.bean.OBJECT_T_MALL_ATTR_VALUE;
import com.atguigu.sale.bean.OBJECT_T_MALL_SKU;
import com.atguigu.sale.bean.T_MALL_PRODUCT;
import com.atguigu.sale.bean.T_MALL_SKU_ATTR_VALUE;


public interface SearchService {


	List<OBJECT_T_MALL_ATTR_VALUE> get_attr_value_by_class_2_id(int class_2_id);

	List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id_and_attr_id_and_value_id(int class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value, String string, int i);

	Map<Object, Object> get_sku_detail_by_sku_id_and_spu_id(int sku_id, int spu_id);





}
