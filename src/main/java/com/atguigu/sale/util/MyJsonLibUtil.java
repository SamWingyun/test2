package com.atguigu.sale.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.atguigu.sale.bean.T_MALL_SHOPPINGCAR;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Until;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyJsonLibUtil {
 
	//集合与json之间的转换，json不能使用泛型json转化成集合，但是jsonLib可以所以建议jsonLib
	@Test
	public  void object_test1(){
		List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<T_MALL_SHOPPINGCAR>();
		for (int i = 0; i < 3; i++) {
			T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();
			t_MALL_SHOPPINGCAR.setSku_mch("测试商品" + i);
			t_MALL_SHOPPINGCAR.setTjshl(10 + i);
			t_MALL_SHOPPINGCAR.setHj(10000 + i);
			list_car.add(t_MALL_SHOPPINGCAR);
		}

		// 1 得到JSONArray
		JSONArray fromObject = JSONArray.fromObject(list_car);
		// 2调用tostring
		String string = fromObject.toString();
		System.out.println(string);

		JSONArray fromObject2 = JSONArray.fromObject(string);

		List<T_MALL_SHOPPINGCAR> collection = (List<T_MALL_SHOPPINGCAR>) JSONArray.toCollection(fromObject2,
				T_MALL_SHOPPINGCAR.class);
		for(T_MALL_SHOPPINGCAR con: collection){
			System.out.println(con.getSku_mch());
		}

	}
    
	//对象与json之间的转换
	@Test
	public  void object_test2() {
		T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();
		t_MALL_SHOPPINGCAR.setSku_mch("测试商品" + 0);
		t_MALL_SHOPPINGCAR.setTjshl(10 + 0);
		t_MALL_SHOPPINGCAR.setHj(10000 + 0);

		// 1 得到jsonobject
		JSONObject fromObject = JSONObject.fromObject(t_MALL_SHOPPINGCAR);

		// 2调用tostring
		String string = fromObject.toString();

		System.out.println(string);

		// 1 得到jsobobject
		JSONObject fromObject2 = JSONObject.fromObject(string);

		// 2调用toBean转化
		T_MALL_SHOPPINGCAR a = (T_MALL_SHOPPINGCAR) JSONObject.toBean(fromObject2, T_MALL_SHOPPINGCAR.class);

		System.out.println(a.getSku_mch());
	}

}
