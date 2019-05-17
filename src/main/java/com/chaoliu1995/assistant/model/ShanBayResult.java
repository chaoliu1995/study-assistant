package com.chaoliu1995.assistant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ShanBayResult {
	private String msg;			//返回信息
	private String status_code;	//返回码
	private Word data;		//返回数据
}
