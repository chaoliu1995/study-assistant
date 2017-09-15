package com.chaoliu1995.english.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.model.ShanBayResult;
import com.chaoliu1995.english.service.TabWordService;
import com.chaoliu1995.english.util.Constants;
import com.chaoliu1995.english.util.HttpUtils;
import com.chaoliu1995.english.util.StringUtils;
import com.chaoliu1995.english.util.TerminalUtils;
import com.google.gson.Gson;

@Controller
public class SearchController extends BaseController {
	
	@Autowired
	private TabWordService tabWordService;
	
	@RequestMapping("/index")
	public String page(){
		String userAgent = request.getHeader( "USER-AGENT" ).toLowerCase();
		if(!StringUtils.isEmpty(userAgent)){
			if(TerminalUtils.checkMobile(userAgent)){
				return "mobile/search";
			}
		}
		return "search";
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	@ResponseBody
	public String send(@RequestParam("word")String word) {
		Gson gson = new Gson();
		try {
			String str = HttpUtils.sendGetRequest(Constants.SHAN_BAY_SEARCH_URL+word,Constants.CHARSET);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> resultMap = new HashMap<String,String>();
			resultMap.put("status", "error");
			resultMap.put("info", "未知错误");
			return gson.toJson(resultMap);
		}
	}
	
	@RequestMapping(value = "/saveWord",method = RequestMethod.POST)
	@ResponseBody
	public void save(@RequestParam("word")String word) throws IOException {
		if(StringUtils.isEmpty(word)){
			return;
		}
		String str = HttpUtils.sendGetRequest(Constants.SHAN_BAY_SEARCH_URL+word,Constants.CHARSET);
		System.out.println(str);
		Gson gson = new Gson();
		ShanBayResult shanbay = gson.fromJson(str,ShanBayResult.class);
		System.out.println();
		tabWordService.saveWord(shanbay,session.getServletContext().getRealPath("/"));
		
	}
	
}
