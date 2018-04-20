package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.model.ShanBayResult;
import com.chaoliu1995.english.service.TabWordService;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.HttpUtils;
import com.chaoliu1995.english.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
public class SearchController extends BaseController {
	
	@Autowired
	private TabWordService tabWordService;
	
	@RequestMapping("/index")
	public String page(){
		return checkPlatForm("search");
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.POST,produces = Consts.PRODUCES)
	@ResponseBody
	public String search(@RequestParam("word")String word) {
		if(StringUtils.isEmpty(word)){
			return Consts.EMPTY_STRING;
		}
		try {
			TabWord tabWord = tabWordService.getOne(new TabWord(word));
			if(tabWord == null){
				String result = HttpUtils.sendGetRequest(Consts.SHAN_BAY_SEARCH_URL+word,Consts.CHARSET);
				return result;
			}else{
				return toJson(tabWord);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> resultMap = getResultMap();
			resultMap.put(Consts.STATUS, Consts.ERROR);
			resultMap.put(Consts.MESSAGE, "未知错误");
			return toJson(resultMap);
		}
	}
	
	@RequestMapping(value = "/saveWord",method = RequestMethod.POST)
	@ResponseBody
	public void save(@RequestParam("word")String word) throws IOException {
		if(StringUtils.isEmpty(word)){
			return;
		}
		TabWord tabWord = tabWordService.getOne(new TabWord(word));
		if(tabWord != null){
			return;
		}
		String str = HttpUtils.sendGetRequest(Consts.SHAN_BAY_SEARCH_URL+word,Consts.CHARSET);
		ShanBayResult shanbay = StringUtils.getGson().fromJson(str,ShanBayResult.class);
		tabWordService.saveWord(shanbay,session.getServletContext().getRealPath("/"));
	}
	
}
