package com.atguigu.sale.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.sale.bean.T_MALL_SHOPPINGCAR;

public interface ShoppingCarMapper {

	public int insert_car(T_MALL_SHOPPINGCAR car);

	public int update_car(T_MALL_SHOPPINGCAR car);

	public List<T_MALL_SHOPPINGCAR> select_car_list_by_user_id(int id);

	public void delete_goods(HashMap<Object, Object> hashMap);

}
