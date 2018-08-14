package com.atguigu.sale.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.sale.bean.OBJECT_T_MALL_SKU;
import com.atguigu.sale.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.sale.service.SearchRedisService;

import redis.clients.jedis.Jedis;

public class MyRedisRefreshUtil {
	
	@Autowired
	static SearchRedisService searchRedisService;
	
	/*
	 * 刷新二级缓存
	 */                            
	public static int refresh_redis_class_2( int class_2_id) {
		System.out.println("refresh_redis_class_2");
		List<OBJECT_T_MALL_SKU> list_object_sku = searchRedisService.get_sku_by_class_2_id_and_attr_id_and_value_id(class_2_id,null,"",0);
        
		String key = "class_2_id_"+class_2_id;
		int num = 0;
		//获取jedis
		Jedis jedis = JedisPoolUtils.getJedis();
		//刷新（添加缓存）缓存之前，清空缓存
		jedis.del(key);
		
		for(int i=0; i<list_object_sku.size(); i++){
			OBJECT_T_MALL_SKU object_T_Mall_Sku = list_object_sku.get(i);
			String sku_json = MyJsonUtil.from_object_sku_to_json(object_T_Mall_Sku);
			jedis.zadd(key, i,sku_json);
			
			num++;
		}
		return num;
	}
	
	
	/*
	 * 测试二级缓存
	 */
	public static List<OBJECT_T_MALL_SKU> test_redis_class_2(int class_2_id){
		System.out.println("test_redis_class_2");
		
		String key = "class_2_id_" + class_2_id;
		//获取jedis
		Jedis jedis = JedisPoolUtils.getJedis();
		
		List<OBJECT_T_MALL_SKU> object_sku_list = new ArrayList<>();
		
		Set<String> zrange = jedis.zrange(key, 0, -1);
		Iterator<String> iterator = zrange.iterator();
		while(iterator.hasNext()){
			String json = iterator.next();
			OBJECT_T_MALL_SKU object_sku = MyJsonUtil.from_json_to_object_sku(json);
			object_sku_list.add(object_sku);
		}
		return object_sku_list;
	}
	
	/*
	 * 刷新分类属性缓存
	 */
	@ResponseBody
	@RequestMapping(value="/refresh_redis_attr",method=RequestMethod.POST)
	public static int refresh_redis_attr(@RequestParam("attr_id_array[]") int[] attr_id_array,int class_2_id){
		System.out.println("refresh_redis_attr");
		
		int num = 0;
		for(int i=0; i<attr_id_array.length; i++){
			List<Integer> value_list = searchRedisService.get_value_list_by_attr_id(attr_id_array[i]);
			//循环分类属性值id
			for(int j=0; j<value_list.size(); j++){
				List<OBJECT_T_MALL_SKU> object_sku_list = searchRedisService.get_object_sku_by_value_id(class_2_id,
						attr_id_array[i], value_list.get(j));
				
				//分类属性检索缓存key
				String attr_key = "attr_key_"+class_2_id+"_"+attr_id_array[i]+"_"+value_list.get(j);
				Jedis jedis = JedisPoolUtils.getJedis();
				//刷新（添加缓存）缓存之前，清空缓存
				jedis.del(attr_key);
				
				for(int k=0; k<object_sku_list.size(); k++){
					//将sku对象转化成json
					String object_sku_json = MyJsonUtil.from_object_sku_to_json(object_sku_list.get(k));
					jedis.zadd(attr_key, k,object_sku_json);
					num++;
				}
			}
		}
		return num;
	}
	
	/*
	 * 测试分类属性缓存
	 */
	public static List<OBJECT_T_MALL_SKU> test_redis_attr(List<T_MALL_SKU_ATTR_VALUE> attr_id_value_id_list, int class_2_id){
		System.out.println("test_redis_attr");
		
		//需要交叉检索的key的数组
		String[] keys = new String[attr_id_value_id_list.size()];
		//交叉检索产生的key
		String out = "out";
		
		for(int i=0; i<attr_id_value_id_list.size(); i++){
			String attr_key = "attr_key_"+class_2_id+"_"+attr_id_value_id_list.get(i).getShxm_id() 
					+ "_" + attr_id_value_id_list.get(i).getShxzh_id();
			//为了保证交叉检索的out具有唯一性，使用属性名id和属性值id生成唯一的out
			out = out + attr_id_value_id_list.get(i).getShxm_id() + attr_id_value_id_list.get(i).getShxzh_id();
		    keys[i] = attr_key;
		}
		//获取jedis
		Jedis jedis = JedisPoolUtils.getJedis();
		//进行交叉检索,第二个参数需要的是一个数组
		jedis.zinterstore("out", keys);
		//去除交叉检索之后的value值
		Set<String> zrange = jedis.zrange("out", 0, -1);
		
		//去除jedis集合转化为Object_T_Mall_Sku对象集合
		List<OBJECT_T_MALL_SKU> object_sku_list = new ArrayList<>();
		Iterator<String> iterator = zrange.iterator();
		while(iterator.hasNext()){
			String json = iterator.next();
			OBJECT_T_MALL_SKU object_sku = MyJsonUtil.from_json_to_object_sku(json);
			object_sku_list.add(object_sku);
		}
		return object_sku_list;
	}

}
