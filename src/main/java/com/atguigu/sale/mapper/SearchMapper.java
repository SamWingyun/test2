package com.atguigu.sale.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.sale.bean.OBJECT_SKU_DETAIL;
import com.atguigu.sale.bean.OBJECT_T_MALL_ATTR_VALUE;
import com.atguigu.sale.bean.OBJECT_T_MALL_SKU;

public interface SearchMapper {

	public List<OBJECT_T_MALL_ATTR_VALUE> select_attr_value_by_class_2_id(int class_2_id);

	public List<OBJECT_T_MALL_SKU> select_sku_by_class_2_id_and_attr_id_and_value_id(HashMap<Object, Object> hashMap);

	public OBJECT_SKU_DETAIL select_sku_object_by_sku_id_and_spu_id(HashMap<Object, Object> hashMap);

}
