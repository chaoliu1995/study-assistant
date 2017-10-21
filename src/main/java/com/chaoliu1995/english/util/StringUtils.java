package com.chaoliu1995.english.util;

import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.text.RandomStringGenerator;

import com.google.gson.Gson;

import lombok.Getter;

public class StringUtils {
	
	@Getter
	private static RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0','z').build();
	@Getter
	private static Gson gson = new Gson();
	
	private StringUtils() {}
	
	/**
	 * object转JSON
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}
	
    public static final String EMPTY = "";
	
	/**
	 * 校验字符串是否为null
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || str.length() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 字符串是否由纯数字组成
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
	     Pattern pattern = Pattern.compile("[0-9]*");
	     return pattern.matcher(str).matches();
	}
	
	/**
	 * 校验邮箱格式
	 * @param str
	 * @return
	 */
	public static boolean checkEmail(String str){
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+");
	     return pattern.matcher(str).matches();
	}
	
	/**
	 * 生成一个code 当前毫秒值+随机数
	 * @return
	 */
	public static String getRandomCode(String str){
		Long millis = System.currentTimeMillis();
		Random rd = new Random();
		millis += rd.nextInt();
		return str + String.valueOf(millis);
	}
	
}
