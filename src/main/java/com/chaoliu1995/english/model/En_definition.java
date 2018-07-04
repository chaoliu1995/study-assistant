package com.chaoliu1995.english.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单词的英文释义
 * @author Administrator
 *
 */
@NoArgsConstructor
@Data
public class En_definition {
	private String pos;		//单词类型：名词、动词等
	private String defn;	//解释
}
