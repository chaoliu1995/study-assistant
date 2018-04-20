package com.chaoliu1995.english.base;

import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.StringUtils;
import com.chaoliu1995.english.util.TerminalUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;
	protected Model model;
	protected String userAgent;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response,Model model){
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.model = model;
	}
	
	/**
	 * 如果是移动平台，在返回的view前加 mobile/,返回移动端的视图
	 * @param view
	 * @return
	 */
	public String checkPlatForm(String view){
		if(!StringUtils.isEmpty(this.userAgent) && TerminalUtils.checkMobile(userAgent)){
			return "mobile/" + view;
		}
		return view;
	}
	
	/**
	 * 返回一个初始化的Map
	 * @return
	 */
	public Map<String,String> getResultMap(){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put(Consts.STATUS, Consts.ERROR);
		return resultMap;
	}
	
	/**
	 * 将对象转为JSON
	 * @param obj
	 * @return
	 */
	public String toJson(Object obj){
		return StringUtils.toJson(obj);
	}
}
