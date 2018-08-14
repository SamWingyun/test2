package com.atguigu.ws.util;

import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {
	
	//在当前日期上加上相应的天数
	public static Date getComeTime(int i){
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DATE, i);
		return instance.getTime();
	}

}
