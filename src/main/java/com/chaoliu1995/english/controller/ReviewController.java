package com.chaoliu1995.english.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chaoliu1995.english.base.BaseController;

/** 
* @Author: LiuChao
* @Description: 单词复习相关操作
* @Email: chaoliu1995@QQ.com
* @CreateDate: 2017年10月17日 下午1:28:38
*/
@Controller
@RequestMapping("/review")
public class ReviewController extends BaseController {
	
	@RequestMapping("/getWord")
	public String getWord(){
		return checkPlatForm("review");
	}
	
	@RequestMapping("/memory")
	public String memory(@RequestParam("wordId")Integer wordId,@RequestParam("remember")boolean remember){
		return "";
	}
	
}
