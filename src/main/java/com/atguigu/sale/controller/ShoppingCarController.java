package com.atguigu.sale.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.sale.bean.T_MALL_USER;
import com.atguigu.sale.service.SearchService;
import com.atguigu.sale.service.ShoppingCarService;
import com.atguigu.sale.util.MyJsonUtil;

@Controller
public class ShoppingCarController {
	
	@Autowired
    ShoppingCarService shoppingCarService;
	
	/*
	 * 获取mini购物车
	 */
	@RequestMapping("/get_miniCar")
	public String get_miniCar(@CookieValue(value="cookie_car_list",required=false)String cookie_car_list,
			HttpSession session, ModelMap map){
		System.out.println("get_miniCar");
		
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> car_list = new ArrayList<>();
		if(user==null){
			car_list = MyJsonUtil.from_json_to_object(cookie_car_list);
		}else{
			car_list = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_car_list");
			if (car_list == null || car_list.size() == 0) {
				// 如果session与数据库中的数据不同步，防止出现异常情况
				car_list = shoppingCarService.get_car_list_by_user_id(user.getId());
				session.setAttribute("session_car_list", car_list);

			}
		}
		map.put("car_list", car_list);
		return "sale_miniCar_inner";
	}

    /*
     * 添加到购物车时的操作
     */
	@RequestMapping("/goto_add_car")
	public String goto_add_car(T_MALL_SHOPPINGCAR car, HttpSession session, HttpServletRequest request,
			HttpServletResponse response,
			@CookieValue(value = "cookie_car_list", required = false)String cookie_car_list) {
		System.out.println("goto_add_car");
		
		List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<>();
		
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		
		if(user==null||user.getId()==0){
			//用户未登录
			//如果有cookie，获得购物车总cookie
				
			list_car = MyJsonUtil.from_json_to_object(cookie_car_list);
				
			if(list_car==null||list_car.size()==0){
				//cookie中没有购物车数据
				list_car = new ArrayList<>();
				//直接添加购物车数据到cookie中
				car.setHj(car.getTjshl()*car.getSku_jg());
				list_car.add(car);
			}else{
				//cookie中有购物数据
			    //判断是新商品还是老商品
				boolean flag = if_old_car(list_car, car);
				if(!flag){
					//新商品作添加
					car.setHj(car.getTjshl()*car.getSku_jg());
					list_car.add(car);
				}else{
					//老商品做修改，更新cookie中的购物车数据字段
					for(int i=0; i<list_car.size();i++){
						if(list_car.get(i).getSku_id()==car.getSku_id()){
							//修改添加数量
							list_car.get(i).setTjshl(list_car.get(i).getTjshl()+car.getTjshl());
							//修改商品合计
							list_car.get(i).setHj(list_car.get(i).getTjshl()*car.getSku_jg());
						}
					}
				}
			}
			//重新放置cookie到客户端
			cookie_car_list = MyJsonUtil.from_object_to_json(list_car);
			Cookie cookie = new Cookie("cookie_car_list", cookie_car_list);
			cookie.setMaxAge(60*60*24*7);
			response.addCookie(cookie);
			
		}else{
			//用户已登录
			list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_car_list");
			//添加用户id
			car.setYh_id(user.getId());
			if(list_car==null||list_car.size()==0){
				//如果session没有购物车直接添加集合到session和数据库
				//session之前没有购物数据
				list_car = new ArrayList<>();
				car.setHj(car.getTjshl()*car.getSku_jg());
				//添加一条购物数据到数据库
				//每添加一条数据需要自动返回购物车主键，然后再加到session中
				int a = shoppingCarService.add_car(car);
				list_car.add(car);
				session.setAttribute("session_car_list", list_car);
			}else{
				//判断是新的购物车还是原有的购物车数据
				boolean flag = if_old_car(list_car, car);
				if(!flag){
					// 如果是新的购物车数据数据库中插入shoppingCar,然后list_car插入一个购物车数据
					// 插入一条购物数据到数据库
					car.setHj(car.getTjshl()*car.getSku_jg());
					int a = shoppingCarService.add_car(car);
					// 同步session中的购物信息
					list_car.add(car);
				}else{
					//如果是老的购物车，更新session和数据库中的数据
					for(int i=0;i<list_car.size();i++){
						if(list_car.get(i).getSku_id()==car.getSku_id()){
							//通过Mybatis主键返回策略获取的carId
							// 更新添加数量时，要根据数据库的添加数量
							car.setId(list_car.get(i).getId());
							car.setTjshl(list_car.get(i).getTjshl() + car.getTjshl());
							// 每次添加相同商品时，合计是session(bd)中的数量+刚刚添加的car的数量
							car.setHj(car.getTjshl() * car.getSku_jg());
							
							// 是对session的一个同步操作
							list_car.get(i).setTjshl(car.getTjshl());
							list_car.get(i).setHj(car.getTjshl() * car.getSku_jg());
						}
					}
					
					int a = shoppingCarService.update_car(car);
				}
			}
					
		}
        request.setAttribute("car", car);
		return "sale_success_car";
	}
	
	/*
	 * 判断是否老数据
	 */
	private boolean if_old_car(List<T_MALL_SHOPPINGCAR> list_car, T_MALL_SHOPPINGCAR car) {
		boolean flag = false;
		for (int i = 0; i < list_car.size(); i++) {
			if (list_car.get(i).getSku_id() == car.getSku_id()) {
				flag = true;
			}
		}
		return flag;
	}
	
	/*
	 * 跳转到购物车
	 */
	@RequestMapping("/goto_shoppingCar")
	public String goto_car(HttpSession session,ModelMap map,
			@CookieValue(value = "cookie_car_list", required = false)String cookie_car_list){
		System.out.println("goto_shoppingCar");
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<>();
		if(user==null){
			//从cookie中cookie_car_list
			list_car = MyJsonUtil.from_json_to_object(cookie_car_list);
			if(list_car==null){
				return "sale_index";
			}
		}else{
			//从session中取，也就是再数据库中取
			list_car= (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_car_list");
			if(list_car==null){
				return "sale_index";
			}
			
		}

		double list_car_sum = 0;
		for(int i=0; i<list_car.size();i++){
			if(list_car.get(i).getShfxz().equals("1")){
				list_car_sum = list_car_sum + list_car.get(i).getHj();
			}
		}
		
		map.put("list_car", list_car);
		session.setAttribute("list_car_sum", list_car_sum);
		return "sale_shoppingCar";
	}
	
	/*
	 * 改变购物车状态和数量价格
	 */
	@RequestMapping(value = "change_shoppingCar", method = RequestMethod.POST)
	public String change_shppingCar(int car_id, int sku_id, int tjshl, String shfxz,
			HttpSession session,ModelMap map,HttpServletResponse response,
			@CookieValue(value = "cookie_car_list", required = false)String cookie_car_list){
		System.out.println("change_shppingCar");
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<>();
		if(user==null){
			//未登录，从cookie中取
			list_car = MyJsonUtil.from_json_to_object(cookie_car_list);
			for (int i=0; i <list_car.size(); i++){
				if (list_car.get(i).getSku_id() == sku_id) {
					if(tjshl<=0){
						//改变状态
						list_car.get(i).setShfxz(shfxz);
					}else{
						//改变数量
						list_car.get(i).setTjshl(tjshl);
						//改变总计
						list_car.get(i).setHj(list_car.get(i).getSku_jg()*list_car.get(i).getTjshl());
					}
				}
			}
			//重新放到cookie中
			cookie_car_list = MyJsonUtil.from_object_to_json(list_car);
			Cookie cookie = new Cookie("cookie_car_list", cookie_car_list);
			cookie.setMaxAge(60*60*24*7);
			response.addCookie(cookie);
		}else{
			//已登录再session中取
			list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_car_list");
			for(int i=0; i<list_car.size();i++){
				if(list_car.get(i).getSku_id()==sku_id){
					if(tjshl<=0){
						//改变状态
						list_car.get(i).setShfxz(shfxz);
						shoppingCarService.update_car(list_car.get(i));
					}else{
						//改变数量和合计
						list_car.get(i).setTjshl(tjshl);
						list_car.get(i).setHj(list_car.get(i).getSku_jg()*list_car.get(i).getTjshl());
						shoppingCarService.update_car(list_car.get(i));
					}
				}
			}
			
		}
		
		double list_car_sum = 0;
		for(int i=0; i<list_car.size();i++){
			if(list_car.get(i).getShfxz().equals("1")){
				list_car_sum = list_car_sum + list_car.get(i).getHj();
			}
		}
		
		map.put("list_car", list_car);
		map.put("list_car_sum", list_car_sum);
		return "sale_shoppingCar_inner";
	}
	
	/*
	 * 删除购物车的商品
	 */
	@RequestMapping("/delete_goods/{sku_id}")
	public String delete_goods(@PathVariable int sku_id, HttpSession session, HttpServletResponse response,
			@CookieValue(value = "cookie_car_list", required = false)String cookie_car_list){
		System.out.println("delete_goods");
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		if(user==null){
			List<T_MALL_SHOPPINGCAR> list_car = MyJsonUtil.from_json_to_object(cookie_car_list);
			for(int i=0; i<list_car.size(); i++){
				if(list_car.get(i).getSku_id()==sku_id){
					list_car.remove(list_car.get(i));
				}
			}
			//重新放到cookie中
			cookie_car_list = MyJsonUtil.from_object_to_json(list_car);
			Cookie cookie = new Cookie("cookie_car_list", cookie_car_list);
			cookie.setPath("/mall-sale");
			cookie.setMaxAge(60*60*24*7);
			response.addCookie(cookie);

		}else{
			int user_id = user.getId();
			shoppingCarService.delete_goods(user_id, sku_id);
			List<T_MALL_SHOPPINGCAR> list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_car_list");
			for(int i=0; i<list_car.size(); i++){
				if(list_car.get(i).getYh_id()==user_id&&list_car.get(i).getSku_id()==sku_id){
					list_car.remove(list_car.get(i));
				}
			}
		}
		return "redirect:/goto_shoppingCar.do";
	}
}
