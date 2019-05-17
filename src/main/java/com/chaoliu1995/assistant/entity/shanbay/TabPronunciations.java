package com.chaoliu1995.assistant.entity.shanbay;

import com.chaoliu1995.assistant.util.Consts;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="pronunciations")
public class TabPronunciations implements java.io.Serializable {

	private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer wordId;
	
	private String us;
	
	private String uk;
	
}
