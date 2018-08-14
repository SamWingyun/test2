package com.atguigu.sale.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.sale.bean.OBJECT_SKU_DETAIL;
import com.atguigu.sale.bean.OBJECT_T_MALL_ATTR_VALUE;
import com.atguigu.sale.bean.OBJECT_T_MALL_SKU;
import com.atguigu.sale.bean.T_MALL_PRODUCT;
import com.atguigu.sale.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.sale.mapper.SearchMapper;
import com.atguigu.sale.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
    
	@Autowired
	SearchMapper searchMapper;

	
	@Override
	public List<OBJECT_T_MALL_ATTR_VALUE> get_attr_value_by_class_2_id(int class_2_id) {
		List<OBJECT_T_MALL_ATTR_VALUE> attr_value = searchMapper.select_attr_value_by_class_2_id(class_2_id);
		return attr_value;
	}


	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id_and_attr_id_and_value_id(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_attr_value,String order_sql,int spu_id) {
		//动态根据热及分类id属性和属性值id拼接sku_id子查询的sql语句
		StringBuffer sbf = new StringBuffer();
		//(select sku_0.sku_id from
		//(select sku_id from t_mall_sku_attr_value e where e.shxm_id = cpu(属性id) and e.shxzh_id = (4核心属性值id)) sku_0
        //(select sku_id from t_mall_sku_attr_value e where e.shxm_id = 硬盘(属性id) and e.shxzh_id = (512GB属性值id)) sku_1
		//where sku_0.sku_id = sku_1.sku_id and sku_1.sku_id = sku_2.sku_id)
		sbf.append("");
		if(list_attr_value!=null && list_attr_value.size()>0) {
			sbf.append(" AND a.id IN ");
			sbf.append("(");
			sbf.append(" select sku_0.sku_id from ");
			for(int i=0;i<list_attr_value.size();i++) {
				sbf.append(" (select sku_id from t_mall_sku_attr_value e where e.shxm_id = "
						+ list_attr_value.get(i).getShxm_id() + " and e.shxzh_id = "
						+ list_attr_value.get(i).getShxzh_id() + ") sku_" + i);
				if (i != (list_attr_value.size() - 1)) {
					sbf.append(" , ");
				}
			}
			if(list_attr_value.size()>1) {
				sbf.append(" where ");
			}
			for(int i=0; i<list_attr_value.size();i++) {
				if(i<(list_attr_value.size()-1) && i>0) {
					sbf.append(" and ");
				}
				if(i<(list_attr_value.size()-1)) {
					sbf.append(" sku_" + i + ".sku_id = sku_" + (i + 1) + ".sku_id ");
				}
			}
			sbf.append(")");
		}
		
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put("class_2_id", class_2_id);
		hashMap.put("attr_value_sql", sbf.toString());
		hashMap.put("order_sql", order_sql);
		hashMap.put("spu_id", spu_id);
		List<OBJECT_T_MALL_SKU> list_object_sku = searchMapper.select_sku_by_class_2_id_and_attr_id_and_value_id(hashMap);
		return list_object_sku;
	}


	@Override
	public Map<Object, Object> get_sku_detail_by_sku_id_and_spu_id(int sku_id, int spu_id) {
		HashMap<Object, Object> hashMap = new HashMap<>();
		List<OBJECT_T_MALL_SKU> list_sku = get_sku_by_class_2_id_and_attr_id_and_value_id(0, null, "", spu_id);
        hashMap.put("sku_id", sku_id);
        hashMap.put("spu_id", spu_id);
        OBJECT_SKU_DETAIL object_sku = searchMapper.select_sku_object_by_sku_id_and_spu_id(hashMap);
		hashMap.put("list_sku", list_sku);
		hashMap.put("object_sku", object_sku);
        return hashMap;
	}

}
