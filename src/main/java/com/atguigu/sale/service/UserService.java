package com.atguigu.sale.service;

import com.atguigu.sale.bean.T_MALL_USER;

public interface UserService {

	T_MALL_USER queryUserByUsername(String username);

	void saveUser(T_MALL_USER userPage);

	T_MALL_USER queryUser(T_MALL_USER userPage);

}
