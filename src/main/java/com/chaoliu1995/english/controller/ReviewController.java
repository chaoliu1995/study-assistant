package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.service.TabWordService;
import com.chaoliu1995.english.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/** 
* @Author: LiuChao
* @Description: 单词复习相关操作
* @Email: chaoliu1995@QQ.com
* @CreateDate: 2017年10月17日 下午1:28:38
*/
@Controller
@RequestMapping("/review")
public class ReviewController extends BaseController {
	
	@Autowired
	private TabWordService tabWordService;
	
	@RequestMapping("/page")
	public String page(){
		model.addAttribute("word", tabWordService.getTabWordByOperateTotalOrderEsc());
		return checkPlatForm("review");
	}
	
	@RequestMapping(value="/getWord",produces = Consts.PRODUCES)
	@ResponseBody
	public String getWord(){
		TabWord word = tabWordService.getTabWordByOperateTotalOrderEsc();
		if(word == null){
			return Consts.EMPTY_STRING;
		}
		return toJson(word);
	}
	
	@RequestMapping("/memory")
	@ResponseBody
	public String memory(@RequestParam("wordId")Integer wordId,@RequestParam("memoryStatus")byte memoryStatus){
		Map<String,String> resultMap = getResultMap();
		if(wordId == null){
			resultMap.put(Consts.MESSAGE, Consts.PARAMETER_IS_NULL);
			return toJson(resultMap);
		}
		switch(memoryStatus){
			case Consts.ORDINARY:
				tabWordService.memory(wordId,Consts.ORDINARY);
				break;
			case Consts.FAMILIAR:
				tabWordService.memory(wordId,Consts.FAMILIAR);
				break;
			case Consts.VERY_FAMILIAR:
				tabWordService.memory(wordId,Consts.VERY_FAMILIAR);
				break;
			case Consts.STRANGE:
				tabWordService.memory(wordId,Consts.STRANGE);
				break;
			case Consts.VERY_STRANGE:
				tabWordService.memory(wordId,Consts.VERY_STRANGE);
				break;
		}
		resultMap.put(Consts.STATUS, Consts.SUCCESS);
		return toJson(resultMap);
	}
	
	@RequestMapping(value="/listWord",produces = "text/html;charset=UTF-8")
	public String listWord(Integer currentPage,Integer limit){
		model.addAttribute("pager",tabWordService.listTabWordForPager(currentPage, limit,new TabWord()));
		return "wordList";
	}
}
