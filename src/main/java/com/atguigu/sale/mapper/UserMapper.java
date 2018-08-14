package com.atguigu.sale.mapper;

import com.atguigu.sale.bean.T_MALL_USER;

public interface UserMapper {

	T_MALL_USER selectUserByUsername(String username);

	void insertUser(T_MALL_USER userPage);

	T_MALL_USER selectUser(T_MALL_USER userPage);


}
