package com.chaoliu1995.english.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单词例句
 * @author Administrator
 *
 */
@NoArgsConstructor
@Data
public class Word {
	private Pronunciations pronunciations;		//单词音标
	private En_definitions en_definitions;		//英文释义
	private Audio_addresses audio_addresses;	//发音url地址
	private String uk_audio;	//发音
	private int conent_id;		//单词ID
	private String audio_name;	//发音文件的名称
	private En_definition en_definition;
	private Cn_definition cn_definition;
	private int num_sense;
	private String content_type;
	private int sense_id;
	private int id;
	private String definition;	//中文释义
	private int content_id;
	private String url;			//请求这个单词的url
	private boolean has_audio;	//是否有语音
	private int object_id;
	private String content;		//单词内容
	private String pron;		//音标？？
	private String pronunciation;	//发音
	private String audio; 		//发音的地址
	private String us_audio;	//发音的地址
	
}
