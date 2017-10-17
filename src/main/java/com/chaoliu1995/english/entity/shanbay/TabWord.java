package com.chaoliu1995.english.entity.shanbay;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chaoliu1995.english.util.Constants;

import lombok.Data;

@Data
@Table(name="word")
public class TabWord implements java.io.Serializable {
	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ukAudio;	//发音url
	private Integer conentId;		//单词ID
	private String audioName;	//发音文件的名称
	private Integer numSense;
	private String contentType;
	private Integer senseId;
	private String definition;	//中文释义
	private Integer contentId;
	private String url;			//请求这个单词的url
	private Integer hasAudio;	//是否有语音
	private Integer objectId;
	private String content;		//单词内容
	private String pron;		//音标？？
	private String pronunciation;	//发音
	private String audio; 		//发音的地址
	private String usAudio;	//发音的地址
	private Integer operateTotal;
	
	public TabWord(){}
	
	public TabWord(String content){
		this.content = content;
	}
	
}
