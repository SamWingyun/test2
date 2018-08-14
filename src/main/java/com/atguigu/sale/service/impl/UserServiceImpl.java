package com.atguigu.sale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.sale.bean.T_MALL_USER;
import com.atguigu.sale.mapper.UserMapper;
import com.atguigu.sale.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	UserMapper userMapper;

	@Override
	public T_MALL_USER queryUserByUsername(String username) {
		return userMapper.selectUserByUsername(username);
	}

	@Override
	public void saveUser(T_MALL_USER userPage) {
		userMapper.insertUser(userPage);
	}

	@Override
	public T_MALL_USER queryUser(T_MALL_USER userPage) {
		return userMapper.selectUser(userPage);
	}

}
