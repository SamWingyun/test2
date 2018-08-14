package com.atguigu.sale.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.sale.mapper.ShoppingCarMapper;
import com.atguigu.sale.service.ShoppingCarService;

@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {
    
	@Autowired
	ShoppingCarMapper shoppingCarMapper;

	@Override
	public int add_car(T_MALL_SHOPPINGCAR car) {
		return  shoppingCarMapper.insert_car(car);
	}

	@Override
	public int update_car(T_MALL_SHOPPINGCAR car) {
		return shoppingCarMapper.update_car(car);
	}

	@Override
	public List<T_MALL_SHOPPINGCAR> get_car_list_by_user_id(int id) {
		return shoppingCarMapper.select_car_list_by_user_id(id);
	}

	@Override
	public void delete_goods(int user_id, int sku_id) {
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put("user_id", user_id);
		hashMap.put("sku_id", sku_id);
		shoppingCarMapper.delete_goods(hashMap);
	}

}
