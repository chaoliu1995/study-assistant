package com.chaoliu1995.english.model;

public class ShanBayResult {
	private String msg;			//返回信息
	private String status_code;	//返回码
	private Word data;		//返回数据
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public Word getData() {
		return data;
	}
	public void setData(Word data) {
		this.data = data;
	}
	
}
