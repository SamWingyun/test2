package com.atguigu.sale.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.atguigu.sale.bean.OBJECT_T_MALL_SKU;
import com.atguigu.sale.bean.T_MALL_SHOPPINGCAR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;

public class MyJsonUtil {
	
	public static String from_object_sku_to_json(OBJECT_T_MALL_SKU object_sku) {

		if (object_sku != null) {
			Gson gson = new Gson();
	
			String json = gson.toJson(object_sku);
	
			try {
				json = URLEncoder.encode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return json;
		}else{
			return null;
		}
	}
	
	public static OBJECT_T_MALL_SKU from_json_to_object_sku(String json) {

		if (json != null && json != "") {
			Gson gson = new Gson();

			try {
				json = URLDecoder.decode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			OBJECT_T_MALL_SKU fromJson = gson.fromJson(json, OBJECT_T_MALL_SKU.class);

			return fromJson;
		} else {
			return null;
		}

	}


	/**
	 * （泛型）集合转json_lib
	 * 
	 * @param json
	 * @param t
	 * @return
	 */
	/*public static String from_object_to_json(List<T_MALL_SHOPPINGCAR> list){
		// 1 得到JSONArray
		JSONArray fromObject = JSONArray.fromObject(list);
		// 2调用tostring
		String json = fromObject.toString();
		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}*/
	
	/**
	 * json_lib转（泛型）集合
	 * 
	 * @param json
	 * @param t
	 * @return
	 */
	/*public static List<T_MALL_SHOPPINGCAR> from_json_to_object(String json) {

		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		JSONArray fromObject = JSONArray.fromObject(json);

		List<T_MALL_SHOPPINGCAR> list = (List<T_MALL_SHOPPINGCAR>) JSONArray.toCollection(fromObject,T_MALL_SHOPPINGCAR.class);

		return list;
	}*/


	
	/**
	 * gson转T_MALL_SHOPPINGCAR集合
	 * 
	 * @param json
	 * @param t
	 * @return
	 */
	public static List<T_MALL_SHOPPINGCAR> from_json_to_object(String json) {
		if(json==null){
			return null;
		}else{
			try {
				json = URLDecoder.decode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Gson gson = new Gson();
			TypeToken<List<T_MALL_SHOPPINGCAR>> typeToken = new TypeToken<List<T_MALL_SHOPPINGCAR>>() {
			};
			List<T_MALL_SHOPPINGCAR> fromJson = gson.fromJson(json, typeToken.getType());
			
			return fromJson;
		}
		
	}

	/**
	 * 集合转gson
	 * 
	 * @param json
	 * @param t
	 * @return
	 */
	public static String from_object_to_json(List<T_MALL_SHOPPINGCAR> list_car) {

		Gson gson = new Gson();

		String json = gson.toJson(list_car);

		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * gson转泛型集合
	 * 
	 * @param json
	 * @param t
	 * @return
	 */
	public static <T> List<T> from_cookie_car_to_object(String json, Class<T> t) {

		Gson gson = new Gson();
		TypeToken<List<T>> typeToken = new TypeToken<List<T>>() {
		};
		List<T> fromJson = gson.fromJson(json, typeToken.getType());

		return fromJson;
	}

	
	
	public static void main(String[] args) {
		Gson gson = new Gson();

		List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<T_MALL_SHOPPINGCAR>();
		for (int i = 0; i < 3; i++) {
			T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();
			t_MALL_SHOPPINGCAR.setSku_mch("测试商品" + i);
			t_MALL_SHOPPINGCAR.setTjshl(10 + i);
			t_MALL_SHOPPINGCAR.setHj(10000 + i);
			list_car.add(t_MALL_SHOPPINGCAR);
		}

		String json = gson.toJson(list_car);

		System.out.println(json);

		TypeToken<List<T_MALL_SHOPPINGCAR>> typeToken = new TypeToken<List<T_MALL_SHOPPINGCAR>>() {
		};

		List<T_MALL_SHOPPINGCAR> fromJson = gson.fromJson(json, typeToken.getType());

		for (int i = 0; i < fromJson.size(); i++) {
			System.out.println(fromJson.get(i));
		}
		
		System.out.println("----------------------");
		
		String json2 = from_object_to_json(list_car);
		System.out.println(json2);
		List<T_MALL_SHOPPINGCAR> list_car2 = from_json_to_object(json2);
		for(T_MALL_SHOPPINGCAR con: list_car2){
			System.out.println(con.getSku_mch());
		}
	}
}
