package com.chaoliu1995.assistant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 英文释义
 * @author Administrator
 *
 */
@NoArgsConstructor
@Data
public class En_definitions {
	private String[] adj;	//形容词
	private String[] n;		//名词
	private String[] adv;	//副词
	private String[] pron;	//代词
	private String[] num;	//数词
	private String[] v;		//动词
	private String[] art;	//冠词
	private String[] prep;	//介词
	private String[] conj;	//连词
	private String[] interj;//感叹词
}
