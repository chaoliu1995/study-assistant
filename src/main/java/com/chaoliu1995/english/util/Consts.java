package com.chaoliu1995.english.util;

/**
 * 项目中的一些常量
 */
public class Consts {

	private Consts(){}

	public static final long SERIAL_VERSION_UID = -6192949389726527999L;
	
	public static final String EMPTY_STRING = "";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String PARAMETER_IS_NULL = "参数有null";
	
	//login
	public static final String SESSION_USER = "SESSION_USER";
	public static final String VERITY_CODE = "verityCode";
    public static final String PREVIOU_VERITY_CODE = "previouVerityCode";
	
	//扇贝查词API的URL
	public static final String SHAN_BAY_SEARCH_URL = "https://api.shanbay.com/bdc/search/?word=";
	
	//存储单词发音文件的路径
	public static final String UK_AUDIO_PATH = "audio/uk/";
	public static final String US_AUDIO_PATH = "audio/us/";
	
	public static final String CHARSET = "UTF-8";

	public static final Byte ONE = 1;

	public static final String USER_WORD_QUEUE = "userWord.queue";

	public static final Byte BOOK_STATUS_DEFAULT = ONE;
}
