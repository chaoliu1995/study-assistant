package com.chaoliu1995.english.entity.shanbay;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chaoliu1995.english.util.Constants;

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
	
	public TabWord(){}
	
	public TabWord(String content){
		this.content = content;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUkAudio() {
		return ukAudio;
	}
	public void setUkAudio(String ukAudio) {
		this.ukAudio = ukAudio;
	}
	public Integer getConentId() {
		return conentId;
	}
	public void setConentId(Integer conentId) {
		this.conentId = conentId;
	}
	public String getAudioName() {
		return audioName;
	}
	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}
	public Integer getNumSense() {
		return numSense;
	}
	public void setNumSense(Integer numSense) {
		this.numSense = numSense;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Integer getSenseId() {
		return senseId;
	}
	public void setSenseId(Integer senseId) {
		this.senseId = senseId;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getHasAudio() {
		return hasAudio;
	}
	public void setHasAudio(Integer hasAudio) {
		this.hasAudio = hasAudio;
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPron() {
		return pron;
	}
	public void setPron(String pron) {
		this.pron = pron;
	}
	public String getPronunciation() {
		return pronunciation;
	}
	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getUsAudio() {
		return usAudio;
	}
	public void setUsAudio(String usAudio) {
		this.usAudio = usAudio;
	}
	
}
