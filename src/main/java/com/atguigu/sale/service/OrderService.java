package com.atguigu.sale.service;

import java.util.List;

import com.atguigu.sale.bean.OBJECT_T_MALL_ORDER_AND_INFO;

public interface OrderService {

	List<String> get_kcdz_list_by_sku_id_list(List<Integer> sku_id_list);

	void add_order(List<OBJECT_T_MALL_ORDER_AND_INFO> order_list);

	void change_order(List<OBJECT_T_MALL_ORDER_AND_INFO> order_list);

}
