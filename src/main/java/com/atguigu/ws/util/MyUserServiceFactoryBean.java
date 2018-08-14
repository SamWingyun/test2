package com.atguigu.ws.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;
/*
 * 创建这个类实现FactoryBean接口，用spring容器来管理 ，以此避免多线程安全问题
 */
public class MyUserServiceFactoryBean<T> implements FactoryBean<T> {
    
	private static final Object MyPasswordCallbackClient = null;

	//接口泛型类型
	Class<T> t;
	
    //传入的url
	String wsUrl;
	
	public Class<T> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}

	public String getWsUrl() {
		return wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	@Override
	public T getObject() throws Exception {
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

		//加入客户端的安全拦截器
		Map<String, Object> map = new HashMap<String, Object>();

		map.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		map.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
		map.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyPasswordCallbackClient.class.getName());
		map.put(WSHandlerConstants.USER, "username");

		WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(map);

		jaxWsProxyFactoryBean.getOutInterceptors().add(wss4jOutInterceptor);
		jaxWsProxyFactoryBean.setAddress(wsUrl);
		T create = jaxWsProxyFactoryBean.create(t);
		return create;
	}

	@Override
	public Class<?> getObjectType() {
		return this.t;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
