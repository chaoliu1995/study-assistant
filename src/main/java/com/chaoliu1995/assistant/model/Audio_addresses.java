package com.chaoliu1995.assistant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单词发音文件url地址
 * @author Administrator
 *
 */
@NoArgsConstructor
@Data
public class Audio_addresses {
	private String[] uk;
	private String[] us;
	
	public String[] getUk() {
		return uk;
	}
	public void setUk(String[] uk) {
		this.uk = uk;
	}
	public String[] getUs() {
		return us;
	}
	public void setUs(String[] us) {
		this.us = us;
	}
	
}
