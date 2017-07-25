package com.chaoliu1995.english.model;

/**
 * 单词例句
 * @author Administrator
 *
 */
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
	
	public Pronunciations getPronunciations() {
		return pronunciations;
	}
	public void setPronunciations(Pronunciations pronunciations) {
		this.pronunciations = pronunciations;
	}
	public En_definitions getEn_definitions() {
		return en_definitions;
	}
	public void setEn_definitions(En_definitions en_definitions) {
		this.en_definitions = en_definitions;
	}
	public Audio_addresses getAudio_addresses() {
		return audio_addresses;
	}
	public void setAudio_addresses(Audio_addresses audio_addresses) {
		this.audio_addresses = audio_addresses;
	}
	public String getUk_audio() {
		return uk_audio;
	}
	public void setUk_audio(String uk_audio) {
		this.uk_audio = uk_audio;
	}
	public int getConent_id() {
		return conent_id;
	}
	public void setConent_id(int conent_id) {
		this.conent_id = conent_id;
	}
	public String getAudio_name() {
		return audio_name;
	}
	public void setAudio_name(String audio_name) {
		this.audio_name = audio_name;
	}
	public En_definition getEn_definition() {
		return en_definition;
	}
	public void setEn_definition(En_definition en_definition) {
		this.en_definition = en_definition;
	}
	public Cn_definition getCn_definition() {
		return cn_definition;
	}
	public void setCn_definition(Cn_definition cn_definition) {
		this.cn_definition = cn_definition;
	}
	public int getNum_sense() {
		return num_sense;
	}
	public void setNum_sense(int num_sense) {
		this.num_sense = num_sense;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public int getSense_id() {
		return sense_id;
	}
	public void setSense_id(int sense_id) {
		this.sense_id = sense_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isHas_audio() {
		return has_audio;
	}
	public void setHas_audio(boolean has_audio) {
		this.has_audio = has_audio;
	}
	public int getObject_id() {
		return object_id;
	}
	public void setObject_id(int object_id) {
		this.object_id = object_id;
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
	public String getUs_audio() {
		return us_audio;
	}
	public void setUs_audio(String us_audio) {
		this.us_audio = us_audio;
	}
	
}
