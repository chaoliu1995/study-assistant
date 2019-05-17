package com.chaoliu1995.assistant.util;

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
	public static final String TIMEOUT = "timeout";
	
	//login
	public static final String SESSION_USER = "SESSION_USER";
	public static final String VERITY_CODE = "verityCode";
    public static final String PREVIOU_VERITY_CODE = "previouVerityCode";
	public static final String LOGIN_CODE = "loginCode";
	public static final String SCAN_CODE = "scanCode";
	public static final String CHARSET = "UTF-8";

	public static final String MINI_PROGRAM = "MiniProgram";
	public static final String WECHAT_MINI_PROGRAM_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
	public static final String QUESTION_MARK = "?";

	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int USER_STATUS_LOCK = TWO;



	//CommonSet typeKey
	public static final int MEMORY_CARD = 101;
	public static final int WORD_BOOK = 102;

	public static final String MINUTE = "MINUTE";
	public static final String HOUR = "HOUR";
	public static final String DAY = "DAY";

	//memory status
	public static final int MEMORY_STATUS_1 = 1;	//5分钟
	public static final int MEMORY_STATUS_2 = 2;	//30分钟
	public static final int MEMORY_STATUS_3 = 3;	//12小时
	public static final int MEMORY_STATUS_4 = 4;	//1天
	public static final int MEMORY_STATUS_5 = 5;	//2天
	public static final int MEMORY_STATUS_6 = 6;	//4天
	public static final int MEMORY_STATUS_7 = 7;	//7天
	public static final int MEMORY_STATUS_8 = 8;	//15天
	public static final int MEMORY_STATUS_9 = 9;	//30天
	public static final int MEMORY_STATUS_10 = 10;	//365



	//扇贝查词API的URL
	public static final String SHAN_BAY_SEARCH_URL = "https://api.shanbay.com/bdc/search/?word=";

	//存储单词发音文件的路径
	public static final String UK_AUDIO_PATH = "audio/uk/";
	public static final String US_AUDIO_PATH = "audio/us/";

	public static final String USER_WORD_QUEUE = "userWord.queue";

	public static final Integer ONE_THOUSAND = 1000;

	public static final String REVIEWING = "reviewing";
	public static final String ADDING = "adding";
}
