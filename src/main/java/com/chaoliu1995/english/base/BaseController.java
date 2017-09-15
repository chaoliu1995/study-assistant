package com.chaoliu1995.english.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.chaoliu1995.english.util.StringUtils;

public class BaseController {

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;
	protected Model model;
	protected String basePath;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response,Model model){
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.model = model;
		if(StringUtils.isEmpty(basePath)){
			this.basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		}
		model.addAttribute("basePath", basePath);
	}
	
}
