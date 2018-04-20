package com.chaoliu1995.english.entity.shanbay;

import com.chaoliu1995.english.util.Consts;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="word")
public class TabWord implements java.io.Serializable {
	
	private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ukAudio;	//英式发音文件的url
	private String usAudio;	//美式发音文件的url
	private String audioName;	//发音文件的名称
	private String contentType;	//类型（词汇或是其他什么）
	private String cnDefinition;	//中文释义
	private String enDefinition;	//英文释义
	private Integer contentId;	//单词ID
	private Integer hasAudio;	//是否有语音
	private String content;		//单词内容
	private String usPronunciation;	//音标(美)
	private String ukPronunciation;	//音标(英)
	private Integer memoryTotal;
	
	public TabWord(){}
	
	public TabWord(String content){
		this.content = content;
	}
	
}
