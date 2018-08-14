package com.atguigu.sale.service;

import java.util.List;

import com.atguigu.sale.bean.OBJECT_T_MALL_SKU;
import com.atguigu.sale.bean.T_MALL_SKU_ATTR_VALUE;

public interface SearchRedisService {

	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id_and_attr_id_and_value_id(int class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value, String order_sql, int spu_id);

	public List<Integer> get_value_list_by_attr_id(int attr_id);

	public List<OBJECT_T_MALL_SKU> get_object_sku_by_value_id(int class_2_id, int attr_id, int value_id);

}