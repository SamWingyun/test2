package com.atguigu.ws.service;

import java.util.List;

import javax.jws.WebService;

import com.atguigu.sale.bean.T_MALL_ADDRESS;
import com.atguigu.sale.bean.T_MALL_USER;

@WebService
public interface MyUserService {
	
	public T_MALL_USER queryUser(T_MALL_USER user);

	public T_MALL_USER queryUserByUsername(String yh_mch);
	
	public boolean saveUser(T_MALL_USER user);

	public boolean update_user(T_MALL_USER user);

	public List<T_MALL_ADDRESS> get_addr_by_user_id(T_MALL_USER user);

	public int add_addresses(List<T_MALL_ADDRESS> addr_list);

	public int update_addresses(T_MALL_ADDRESS address);



}
