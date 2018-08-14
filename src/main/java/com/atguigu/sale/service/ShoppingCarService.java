package com.atguigu.sale.service;

import java.util.List;

import com.atguigu.sale.bean.T_MALL_SHOPPINGCAR;

public interface ShoppingCarService {

	int add_car(T_MALL_SHOPPINGCAR car);

	int update_car(T_MALL_SHOPPINGCAR car);

	List<T_MALL_SHOPPINGCAR> get_car_list_by_user_id(int id);

	void delete_goods(int user_id, int sku_id);

}
