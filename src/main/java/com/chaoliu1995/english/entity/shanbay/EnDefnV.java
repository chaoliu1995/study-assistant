package com.chaoliu1995.english.entity.shanbay;

import com.chaoliu1995.english.util.Consts;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class EnDefnV implements java.io.Serializable {
	
	private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer wordId;
	
	private String v;
	
}
