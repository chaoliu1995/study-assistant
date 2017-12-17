package com.chaoliu1995.english.util;

import java.util.ResourceBundle;

/** 
* @Author: ChaoLiu
* @Description: 
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年12月17日 下午12:40:00
*/
public class BundleUtils {
	
	private BundleUtils(){}
	
	private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
	
	public static final Object getObject(String key){
		return resourceBundle.getObject(key);
	}
	
	public static final String getString(String key){
		return resourceBundle.getString(key);
	}
	
	public static final String[] getStringArray(String key){
		return resourceBundle.getStringArray(key);
	}
}
