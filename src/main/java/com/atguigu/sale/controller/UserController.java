package com.atguigu.sale.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.sale.bean.T_MALL_USER;
import com.atguigu.sale.service.ShoppingCarService;
import com.atguigu.sale.service.UserService;
import com.atguigu.sale.util.MD5Util;
import com.atguigu.sale.util.MyJsonUtil;
import com.atguigu.ws.service.MyUserService;
import com.google.gson.Gson;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ShoppingCarService shoppingCarService;
	
	 // 使用FactoryBean,getObject(),容器在初始化FactoryBean类型的对象的时候调用getObject()，返回实例
	@Autowired
	MyUserService myUserService;
	
	@RequestMapping("/goto_login")
	public String goto_login() {
		System.out.println("goto_login");
		return "sale_login";
	}
	
	//登录
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@CookieValue(value = "cookie_car_list", required = false) String cookie_car_list,
			String username, String password, String login_auto_login, HttpSession session,HttpServletResponse response, ModelMap map) {
		System.out.println("login");
		if(username==""||password==""){
			System.out.println("用户信息不能为空");
			map.put("msg", "用户信息不能为空");
			return "sale_login";
		}
		//查询数据库用户
		T_MALL_USER userPage = new T_MALL_USER(username,password);
		T_MALL_USER user = myUserService.queryUserByUsername(username);
		if(user==null) {
			System.out.println("用户名或密码错误");
			map.put("msg", "用户名或密码错误");
			return "sale_login";
		}else {
            String passwordMd5 = MD5Util.digest(password);
            String passwordQuery = user.getYh_mm();
			if(!passwordQuery.equals(passwordMd5)) {
				System.out.println("用户名称或密码错误");
				map.put("msg", "用户名称或密码错误");
				return "sale_login";
			}else {
				//自动登录
				auto_login(login_auto_login, user, response);
				
				// 将cookie中的数据转入session
				from_cookie_to_session(cookie_car_list, session, user);
				//清空cookie
				response.addCookie(new Cookie("cookie_car_list", ""));

				
				session.setAttribute("user", user);
				return "sale_index";
			}
		}
	}
	
	//将cookie中购物信息转换到session中
	public void from_cookie_to_session(String cookie_car_list,HttpSession session,T_MALL_USER user){
		//查询数据库中的值
		List<T_MALL_SHOPPINGCAR> db_car_list = shoppingCarService.get_car_list_by_user_id(user.getId());
		if(cookie_car_list==null||cookie_car_list.equals("")){
			
		}else{
			List<T_MALL_SHOPPINGCAR> list_car = MyJsonUtil.from_json_to_object(cookie_car_list);
			for(int i=0; i<list_car.size(); i++){
				list_car.get(i).setYh_id(user.getId());
				Map<Object, Object> map = if_old_car(db_car_list, list_car.get(i));
				if((boolean) map.get("flag")){
					// 更新数据库
					list_car.get(i).setId(((T_MALL_SHOPPINGCAR) map.get("db_car")).getId());
					list_car.get(i).setTjshl(
							list_car.get(i).getTjshl() + ((T_MALL_SHOPPINGCAR) map.get("db_car")).getTjshl());
					list_car.get(i).setHj(
							list_car.get(i).getTjshl() * ((T_MALL_SHOPPINGCAR) map.get("db_car")).getSku_jg());

					// 将cookie中的老数据更新到db中
					shoppingCarService.update_car(list_car.get(i));
				}else{
					shoppingCarService.add_car(list_car.get(i));
				}
			}
			// 同步session，cookie+db
			db_car_list = shoppingCarService.get_car_list_by_user_id(user.getId());
			session.setAttribute("session_car_list", db_car_list);
		}
	}
	
	//判断是否是老数据
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
	
	//提取自动登录方法
	public void auto_login(String login_auto_login, T_MALL_USER user, HttpServletResponse response){
		if(login_auto_login != null && !login_auto_login.equals("")) {
			//将用户数据放进cookie中
			Gson gson = new Gson();
			String jsonUser = gson.toJson(user);
			try {
				jsonUser = URLEncoder.encode(jsonUser,"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Cookie cookie = new Cookie("cookieUser", jsonUser);
			cookie.setMaxAge(60*60*24*7);
			response.addCookie(cookie);
		}
	}
	
	

    //注销
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("logout");
		session.invalidate();
		return "sale_index";
	}
	
	//去注册
	@RequestMapping("/goto_regist")
	public String to_regist() {
		System.out.println("goto_regist");
		return "sale_regist";
	}
	
	//注册
	@RequestMapping("/regist")
	public String regist(String username, String password, String email, String mobile, ModelMap map) {
		System.out.println("regist");
		if(username==""||password==""){
			System.out.println("注册信息不能为空");
			map.put("msg", "注册信息不能为空");
			return "sale_regist";
		}
		T_MALL_USER userPage = new T_MALL_USER();
		userPage.setYh_mch(username);
		userPage.setYh_mm(password);
		userPage.setYh_yx(email);
		userPage.setYh_shjh(mobile);
		T_MALL_USER user = myUserService.queryUserByUsername(username);
		if(userPage!=null) {
			if(user!=null) {
				System.out.println("用户已经存在");
				map.put("msg", "用户已经存在");
				return "sale_regist";
			}else {
				String passwordMd5 = MD5Util.digest(password);
				userPage.setYh_mm(passwordMd5);
				myUserService.saveUser(userPage);
				return "sale_index";
			}
		}
		return null;
	}

}
