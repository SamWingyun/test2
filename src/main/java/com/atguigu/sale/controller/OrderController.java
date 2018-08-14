package com.atguigu.sale.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atguigu.sale.bean.OBJECT_T_MALL_ORDER_AND_INFO;
import com.atguigu.sale.bean.T_MALL_ADDRESS;
import com.atguigu.sale.bean.T_MALL_ORDER_INFO;
import com.atguigu.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.sale.bean.T_MALL_USER;
import com.atguigu.sale.service.OrderService;
import com.atguigu.sale.service.ShoppingCarService;
import com.atguigu.sale.util.MD5Util;
import com.atguigu.sale.util.MyJsonUtil;
import com.atguigu.ws.service.MyUserService;

@Controller
@SessionAttributes("order_list")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MyUserService myUserService;
	
	@Autowired
	ShoppingCarService shoppingCarService;
	
	@RequestMapping("/goto_check_order")
	public String goto_check_order(HttpSession session, ModelMap map){
		System.out.println("goto_check_order");
		
		T_MALL_USER  user = (T_MALL_USER) session.getAttribute("user");
		//购物车集合
		List<T_MALL_SHOPPINGCAR> car_list = new ArrayList<>();
		//订单信息集合
		List<OBJECT_T_MALL_ORDER_AND_INFO> order_list = new ArrayList<>();
		
		if(user==null){
			return "sale_login_buy";
		}else{
			// 用户已登录,从sessin中拿出购物车数据
			car_list = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_car_list");
	
			// 先查询出car_list中所有去重复的sku库存地址集合
			List<Integer> list_sku_id = new ArrayList<Integer>();
			for (int i = 0; i < car_list.size(); i++) {
				if (car_list.get(i).getShfxz().equals("1")) {
					list_sku_id.add(car_list.get(i).getSku_id());
				}
			}
	
			// 调用查询库存信息集合的查询方法
			List<String> kcdz_list = orderService.get_kcdz_list_by_sku_id_list(list_sku_id);
	
			// 循环sku库存地址结合,封装order集合
			for (int i = 0; i < kcdz_list.size(); i++) {
	
				// 当前订单
				OBJECT_T_MALL_ORDER_AND_INFO object_T_MALL_ORDER_AND_INFO = new OBJECT_T_MALL_ORDER_AND_INFO();
	
				// 当前订单的订单信息集合
				List<T_MALL_ORDER_INFO> list_order_info = new ArrayList<T_MALL_ORDER_INFO>();
	
				// 当前订单的总金额
				double zje = 0d;
	
				// 订单信息t_mall_order_info
				for (int j = 0; j < car_list.size(); j++) {
					if (car_list.get(j).getShfxz().equals("1")) {
						T_MALL_ORDER_INFO t_MALL_ORDER_INFO = new T_MALL_ORDER_INFO();
						if (kcdz_list.get(i).equals(car_list.get(j).getKc_dz())) {
							// 封装订单信息表
							t_MALL_ORDER_INFO.setGwch_id(car_list.get(j).getId());
							t_MALL_ORDER_INFO.setShp_tp(car_list.get(j).getShp_tp());
							t_MALL_ORDER_INFO.setSku_id(car_list.get(j).getSku_id());
							t_MALL_ORDER_INFO.setSku_jg(car_list.get(j).getSku_jg());
							t_MALL_ORDER_INFO.setSku_kcdz(car_list.get(j).getKc_dz());
							t_MALL_ORDER_INFO.setSku_mch(car_list.get(j).getSku_mch());
							t_MALL_ORDER_INFO.setSku_shl(car_list.get(j).getTjshl());
							list_order_info.add(t_MALL_ORDER_INFO);
							zje = zje + car_list.get(j).getHj();
						}
					}
				}
				// 把订单信息集合放入当前订单中
				object_T_MALL_ORDER_AND_INFO.setOrder_info_list(list_order_info);
	
				// 将总金额放到订单中
				object_T_MALL_ORDER_AND_INFO.setZje(zje);
				
				//将用户id加入订单中
				object_T_MALL_ORDER_AND_INFO.setYh_id(user.getId());
	
				// 把订单放在订单集合中
				order_list.add(object_T_MALL_ORDER_AND_INFO);

			}
			//查询收货人地址
			List<T_MALL_ADDRESS> addr_list = myUserService.get_addr_by_user_id(user);
			map.put("addr_list", addr_list);
			map.put("order_list", order_list);
			return "sale_check_order";
		}
	}
	
	@RequestMapping("/login_buy")
	public String login_buy(String username, String password, ModelMap map,
			HttpSession session, HttpServletResponse response,
			@CookieValue(value="cookie_car_list",required=false)String cookie_car_list){
		System.out.println("login_buy");
		
		if(username==""||password==""){
			System.out.println("用户信息不能为空");
			map.put("msg", "用户信息不能为空");
			return "sale_login_buy";
		}
		//查询数据库用户
		T_MALL_USER userPage = new T_MALL_USER(username,password);
		T_MALL_USER user = myUserService.queryUserByUsername(username);
		if(user==null) {
			System.out.println("用户名或密码错误");
			map.put("msg", "用户名或密码错误");
			return "sale_login_buy";
		}else {
            String passwordMd5 = MD5Util.digest(password);
            String passwordQuery = user.getYh_mm();
			if(!passwordQuery.equals(passwordMd5)) {
				System.out.println("用户名称或密码错误");
				map.put("msg", "用户名称或密码错误");
				return "sale_login_buy";
			}else {
				//用户已经登录
				session.setAttribute("user", user);
				//获取cookie中的购物车信息，将之同步至数据库，如果数据库中有购物车中的商品，
				//将此商品的数量变为cookie中商品的数量，而且将以前数据库中的商品的选中状态变为否
				List<T_MALL_SHOPPINGCAR> car_list = MyJsonUtil.from_json_to_object(cookie_car_list);
				
				//取出数据中的商品,将之和cookie中的商品比较
				List<T_MALL_SHOPPINGCAR> db_car_list = shoppingCarService.get_car_list_by_user_id(user.getId());
				
				//将数据库中的已有商品状态改为0
				for(int j=0; j<db_car_list.size(); j++){
					db_car_list.get(j).setShfxz("0");
					shoppingCarService.update_car(db_car_list.get(j));
				}
				
				//再次取出数据中的商品,将之和cookie中的商品比较
				db_car_list = shoppingCarService.get_car_list_by_user_id(user.getId());
				
				for(int i=0; i<car_list.size(); i++){
					
					Map<Object, Object> flagMap = if_old_car(db_car_list, car_list.get(i));
					if((boolean) flagMap.get("flag")){
						//更改数据库商品数量为cookie的数量，以及价格，状态
						car_list.get(i).setId(((T_MALL_SHOPPINGCAR) flagMap.get("db_car")).getId());
						car_list.get(i).setTjshl(car_list.get(i).getTjshl());
						car_list.get(i).setHj(car_list.get(i).getSku_jg()*car_list.get(i).getTjshl());
					}else{
						//数据中没有的则添加
						shoppingCarService.add_car(car_list.get(i));
					}

					// 将cookie中的老数据更新到db中
					shoppingCarService.update_car(car_list.get(i));
					
					db_car_list = shoppingCarService.get_car_list_by_user_id(user.getId());
                    
					session.setAttribute("session_car_list", db_car_list);
					
					//清空cookie
					response.addCookie(new Cookie("cookie_car_list", ""));
					
					
				}
				
				return "forward:/goto_check_order.do";
			}
		}
	}
	
	//判断是否是同一个数据
	private Map<Object, Object> if_old_car(List<T_MALL_SHOPPINGCAR> db_car_list, T_MALL_SHOPPINGCAR car){
		HashMap<Object, Object> hashMap = new HashMap<>();
		boolean flag =false;
		for(int i=0; i<db_car_list.size(); i++){
			if(db_car_list.get(i).getSku_id()==car.getSku_id()){
				flag = true;
				hashMap.put("db_car", db_car_list.get(i));
			}
		}
		hashMap.put("flag", flag);
		return hashMap;
	}
	
	@RequestMapping(value="/add_order.do",method=RequestMethod.POST)
	public String add_order(@ModelAttribute("order_list") ArrayList<OBJECT_T_MALL_ORDER_AND_INFO> order_list,
			T_MALL_ADDRESS addr, HttpSession session){
		System.out.println("add_order");
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		
		//将收件人地址信息加入订单
		for(int i=0; i<order_list.size(); i++){
			order_list.get(i).setDzh_id(addr.getId());
			order_list.get(i).setDzh_mch(addr.getShjr_dz());
			order_list.get(i).setShjr(addr.getShjr());
		}
		//添加进数据库
		orderService.add_order(order_list);
		//查询数据库中的购物车同步至session
		List<T_MALL_SHOPPINGCAR> db_car_list = shoppingCarService.get_car_list_by_user_id(user.getId());
		session.setAttribute("session_car_list", db_car_list);
		
		return "redirect:/goto_pay_order.do";
	}
	
	@RequestMapping("/goto_pay_order")
	public String goto_pay_order(@ModelAttribute("order_list") List<OBJECT_T_MALL_ORDER_AND_INFO> order_list,
			ModelMap map){
		System.out.println("goto_pay_order");
		
		double order_sum = 0d;
		for(int i=0; i<order_list.size(); i++){
			order_sum = order_sum + order_list.get(i).getZje();
		}
		map.put("order_list", order_list);
		map.put("order_sum", order_sum);
		return "sale_pay_order";
	}
	
	@RequestMapping("/pay_order")
	public String pay_order(@ModelAttribute("order_list") List<OBJECT_T_MALL_ORDER_AND_INFO> order_list,
			ModelMap map, HttpSession session){
		System.out.println("pay_order");
		//支付成功,更改商品库存和销量
		orderService.change_order(order_list);
		
		//移除session中的订单order_list
		session.removeAttribute("order_list");
		return "sale_pay_order_success";
	}
	
}
