package com.atguigu.sale.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.sale.bean.OBJECT_T_MALL_ORDER_AND_INFO;
import com.atguigu.sale.bean.T_MALL_FLOW;
import com.atguigu.sale.bean.T_MALL_ORDER_INFO;
import com.atguigu.sale.mapper.OrderMapper;
import com.atguigu.sale.service.OrderService;
import com.atguigu.ws.util.MyDateUtil;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderMapper orderMapper;
    
	/*
	 * 根据sku_id获取库存地址
	 */
	@Override
	public List<String> get_kcdz_list_by_sku_id_list(List<Integer> sku_id_list) {
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put("sku_id_list", sku_id_list);
		return orderMapper.select_kcdz_list_by_sku_id_list(hashMap);
	}
    
	/*
	 * 订单提交
	 */
	@Override
	public void add_order(List<OBJECT_T_MALL_ORDER_AND_INFO> order_list) {
		for(int i=0; i<order_list.size(); i++){
			/** 1 插入订单表 **/
			//总金额， 收货人，预计送达时间，收货地址， 收货地址id
			order_list.get(i).setYjsdshj(MyDateUtil.getComeTime(3));
			int a = orderMapper.insert_into_t_mall_order(order_list.get(i));
			
			/** 2 批量插入订单信息表 **/
			// 订单id，skuid，sku名称，商品图片，sku价格，sku数量，sku库存地址
			List<T_MALL_ORDER_INFO> order_info_list = order_list.get(i).getOrder_info_list();
			for (int j = 0; j < order_info_list.size(); j++) {
				//采用主键返回策略
				order_info_list.get(j).setDd_id(order_list.get(i).getId());
			}
			
			int b = orderMapper.insert_into_t_mall_order_info(order_info_list);
			
			/** 3 批量删除购物车表 **/
			orderMapper.delete_t_mall_shoppingCar(order_info_list);
			
			/** 4 插入物流表 **/
			// 配送方式，配送时间，配送描述，用户id，订单id，目前地点，目的地，业务员， 联系方式
			T_MALL_FLOW t_MALL_FLOW = new T_MALL_FLOW();
			t_MALL_FLOW.setPsfsh("顺丰快递");
			t_MALL_FLOW.setYh_id(order_list.get(i).getYh_id());
			t_MALL_FLOW.setDd_id(order_list.get(i).getId());
			t_MALL_FLOW.setMqdd("广州天河");
			int d = orderMapper.insert_into_t_mall_flow(t_MALL_FLOW);
		}
	}
    
	/*
	 * 订单支付
	 */
	@Override
	public void change_order(List<OBJECT_T_MALL_ORDER_AND_INFO> order_list) {
		
		List<T_MALL_ORDER_INFO> order_info_list1 = new ArrayList<>();
		for(int i=0; i<order_list.size(); i++){
		   List<T_MALL_ORDER_INFO> order_info_list2  = order_list.get(i).getOrder_info_list();
		   order_info_list1.addAll(order_info_list2);
		}
		
		boolean flag = true;
		for (int j=0; j<order_info_list1.size(); j++) {
			//返回要购买的sku库存，进行判断
			int a = orderMapper.select_sku_shl_by_sku_id(order_info_list1.get(j).getSku_id());
			if(a<order_info_list1.get(j).getSku_shl()){
				flag = false;
			}
		}
		if (flag) {
			for(int i=0; i<order_list.size(); i++){
				//更该订单进度号
				orderMapper.update_order_jdh_by_order_id(order_list.get(i).getId());
				
				//更改物流状态
				T_MALL_FLOW t_MALL_FLOW = new T_MALL_FLOW();
				t_MALL_FLOW.setDd_id(order_list.get(i).getId());
				t_MALL_FLOW.setYwy("");
				t_MALL_FLOW.setLxfsh("");
				t_MALL_FLOW.setPsshj(MyDateUtil.getComeTime(0));
				t_MALL_FLOW.setPsmsh("商品已出库");
				orderMapper.update_flow_by_dd_id(t_MALL_FLOW);
				
				//更改库存状态
				for(int j=0; j<order_list.get(i).getOrder_info_list().size(); j++){
					orderMapper.update_sku_by_order_info(order_list.get(i).getOrder_info_list().get(j));
				}
			}
		}
	}

}
