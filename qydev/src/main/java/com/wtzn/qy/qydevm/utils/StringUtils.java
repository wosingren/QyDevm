package com.wtzn.qy.qydevm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串工具类
 * 
 * @author baijw
 * 
 * @date 2017年5月2日 下午3:37:10
 */
public class StringUtils {

	/**
	 * 验证字符串是否是空值
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return str==null || str.trim().equals("") || str.trim().equals("null");
	} 
	
	/**
	 * date 转化为字符串  yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String dateTimeToString(Date date){
		SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return datetemp.format(date);
	}
	
	/**
	 * date 转化为字符串  yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd");
		return datetemp.format(date);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss 格式的字符串转化为Date类型
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDateTime(String date) throws ParseException{
		SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return datetemp.parse(date);
	}
	
	
//	public static String dateTimeToStringWithoutSpit(Date date){
//		SimpleDateFormat durn datetemp.format(datatetemp = new SimpleDateFormat("yyyyMMddHHmmss");
//		rete);
//	}

	public static int carrieroperator2Index(String carrieroperator){
		if(carrieroperator.equals("电信"))
			return 0;
		else if(carrieroperator.equals("联通"))
			return 1;
		else if(carrieroperator.equals("移动"))
			return 2;
		else if(carrieroperator.equals("广电"))
			return 3;
		else if(carrieroperator.equals("电信待装"))
			return 4;
		return 1;
	}
	
}
