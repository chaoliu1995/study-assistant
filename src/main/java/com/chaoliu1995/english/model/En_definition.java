package com.chaoliu1995.english.model;

/**
 * 单词的英文释义
 * @author Administrator
 *
 */
public class En_definition {
	private String pos;		//单词类型：名词、动词等
	private String defn;	//解释
	
	
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getDefn() {
		return defn;
	}
	public void setDefn(String defn) {
		this.defn = defn;
	}
}
