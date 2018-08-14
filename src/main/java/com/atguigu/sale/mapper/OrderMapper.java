package com.atguigu.sale.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.sale.bean.OBJECT_T_MALL_ORDER_AND_INFO;
import com.atguigu.sale.bean.T_MALL_FLOW;
import com.atguigu.sale.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {

	public List<String> select_kcdz_list_by_sku_id_list(HashMap<Object, Object> hashMap);

	public int insert_into_t_mall_order(OBJECT_T_MALL_ORDER_AND_INFO object_T_MALL_ORDER_AND_INFO);

	public int insert_into_t_mall_order_info(List<T_MALL_ORDER_INFO> list);

	public void delete_t_mall_shoppingCar(List<T_MALL_ORDER_INFO> list);

	public int insert_into_t_mall_flow(T_MALL_FLOW t_MALL_FLOW);

	public void update_order_jdh_by_order_id(int id);

	public void update_flow_by_dd_id(T_MALL_FLOW t_MALL_FLOW);

	public void update_sku_by_order_info(T_MALL_ORDER_INFO t_MALL_ORDER_INFO);

	public int select_sku_shl_by_sku_id(int sku_id);

}
