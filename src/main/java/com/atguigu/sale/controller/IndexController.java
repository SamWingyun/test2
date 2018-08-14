package com.atguigu.sale.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.sale.bean.T_MALL_USER;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpSession session) {
		System.out.println("index");
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			String cookieUser = "";
			for(Cookie cookie : cookies) {
				System.out.println(cookie.getValue());
				if(cookie.getName()!=null && cookie.getName().equals("cookieUser")) {
					cookieUser = cookie.getValue();
				}
				if(!cookieUser.equals("")) {
					try {
						cookieUser = URLDecoder.decode(cookieUser,"utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
						Gson gson = new Gson();
						T_MALL_USER user =  gson.fromJson(cookieUser, T_MALL_USER.class);
					
					session.setAttribute("user", user);
				}
			}
		}
		return "sale_index";
	}

}
