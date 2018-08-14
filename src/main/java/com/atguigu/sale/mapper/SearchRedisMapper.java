package com.atguigu.sale.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.sale.bean.OBJECT_T_MALL_SKU;

public interface SearchRedisMapper {

	List<OBJECT_T_MALL_SKU> select_sku_by_class_2_id_and_attr_id_and_value_id(HashMap<Object, Object> hashMap);

	List<Integer> select_value_list_by_attr_id(int attr_id);

}
