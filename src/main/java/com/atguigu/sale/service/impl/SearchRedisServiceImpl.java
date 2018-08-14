package com.atguigu.sale.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.sale.bean.OBJECT_T_MALL_SKU;
import com.atguigu.sale.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.sale.mapper.SearchRedisMapper;
import com.atguigu.sale.service.SearchRedisService;

@Service
public class SearchRedisServiceImpl implements SearchRedisService{
	
	@Autowired
	SearchRedisMapper searchRedisMapper;
	/***
	 * 根据分类属性条件进行二级检索
	 */
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id_and_attr_id_and_value_id(int class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value, String order_sql, int spu_id) {

		// 动态根据二级分类id和属性和属性值id拼接sku_id子查询的sql语句
		StringBuffer sbf = new StringBuffer();
		sbf.append("");
		if (list_attr_value != null && list_attr_value.size() > 0) {

			sbf.append(" AND a.id IN ");

			sbf.append("(");

			sbf.append(" select sku_0.sku_id from ");

			for (int i = 0; i < list_attr_value.size(); i++) {
				sbf.append(" (select sku_id from t_mall_sku_attr_value e where e.shxm_id = "
						+ list_attr_value.get(i).getShxm_id() + " and e.shxzh_id = "
						+ list_attr_value.get(i).getShxzh_id() + ") sku_" + i);
				if (i != (list_attr_value.size() - 1)) {
					sbf.append(" , ");
				}
			}

			if (list_attr_value.size() > 1) {
				sbf.append(" where ");
			}

			for (int i = 0; i < list_attr_value.size(); i++) {
				if (i < (list_attr_value.size() - 1) && i > 0) {
					sbf.append(" and ");
				}
				if (i < (list_attr_value.size() - 1)) {
					sbf.append(" sku_" + i + ".sku_id = sku_" + (i + 1) + ".sku_id ");
				}

			}

			sbf.append(")");
		}

		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();

		hashMap.put("class_2_id", class_2_id);

		hashMap.put("attr_value_sql", sbf.toString());

		hashMap.put("order_sql", order_sql);

		hashMap.put("spu_id", spu_id);

		List<OBJECT_T_MALL_SKU> list_object_sku = searchRedisMapper
				.select_sku_by_class_2_id_and_attr_id_and_value_id(hashMap);

		return list_object_sku;

	}
	@Override
	public List<Integer> get_value_list_by_attr_id(int attr_id) {
		return searchRedisMapper.select_value_list_by_attr_id(attr_id);
	}

	@Override
	public List<OBJECT_T_MALL_SKU> get_object_sku_by_value_id(int class_2_id, int attr_id, int value_id) {
		HashMap<Object, Object> hashMap = new HashMap<>();
		
		hashMap.put("class_2_id", class_2_id);
		hashMap.put("attr_id", attr_id);
		hashMap.put("value_id", value_id);
		List<OBJECT_T_MALL_SKU> object_sku_list = searchRedisMapper.select_sku_by_class_2_id_and_attr_id_and_value_id(hashMap);
		return object_sku_list;
	}



}