package com.atguigu.sale.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class ServerStartupListener extends ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {
		// 初始化Spring的环境
		super.contextInitialized(event);
		// 将路径保存到应用范围中
		ServletContext application = event.getServletContext();
		String path = application.getContextPath();
		application.setAttribute("app_path", path);
		
	}

}
