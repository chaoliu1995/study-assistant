package com.chaoliu1995.english.util;

/**
 * 项目中的一些常量
 */
public class Consts {
	private Consts(){}

	public static final long SERIAL_VERSION_UID = -6192949389726527999L;
	
	public static final String EMPTY_STRING = "";
	public static final String STATUS = "status";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String MESSAGE = "message";
	public static final String PARAMETER_IS_NULL = "参数有null";
	
	//login
	public static final String SESSION_USER = "SESSION_USER";
	public static final String LOGIN_TIME = "LOGIN_TIME";
	
	//扇贝查词API的URL
	public static final String SHAN_BAY_SEARCH_URL = "https://api.shanbay.com/bdc/search/?word=";
	
	//存储单词发音文件的路径
	public static final String UK_AUDIO_PATH = "audio/uk/";
	
	public static final String US_AUDIO_PATH = "audio/us/";
	
	public static final String CHARSET = "UTF-8";
	
	//review
	//对单词的熟悉程度
	public static final byte VERY_STRANGE = -2;	//非常陌生的
	public static final byte STRANGE = -1;		//陌生的
	public static final byte ORDINARY = 1;		//普通的
	public static final byte FAMILIAR = 2;		//熟悉的
	public static final byte VERY_FAMILIAR = 3;	//非常熟悉的
}
