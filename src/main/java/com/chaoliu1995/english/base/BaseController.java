package com.chaoliu1995.english.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.chaoliu1995.english.util.StringUtils;
import com.chaoliu1995.english.util.TerminalUtils;

public abstract class BaseController {

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;
	protected Model model;
	protected String basePath;
	protected String userAgent;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response,Model model){
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.model = model;
		if(StringUtils.isEmpty(basePath)){
			this.basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		}
		this.userAgent = request.getHeader( "USER-AGENT" ).toLowerCase();
		model.addAttribute("basePath", basePath);
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
}
