package com.chaoliu1995.english.base;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class BaseController {

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;
	protected Model model;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response,Model model){
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.model = model;
	}
}
