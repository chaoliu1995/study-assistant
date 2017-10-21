package com.chaoliu1995.english.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.chaoliu1995.english.util.Consts;

import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @Author: ChaoLiu
* @Description: 
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午7:43:00
*/
@Data
@NoArgsConstructor
public class User implements Serializable {
	
	private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
}
