package com.chaoliu1995.english.entity.shanbay;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.chaoliu1995.english.util.Constants;

public class EnDefnInterj implements java.io.Serializable {
	
private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer wordId;
	
	private String interj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWordId() {
		return wordId;
	}

	public void setWordId(Integer wordId) {
		this.wordId = wordId;
	}

	public String getInterj() {
		return interj;
	}

	public void setInterj(String interj) {
		this.interj = interj;
	}
	
}
