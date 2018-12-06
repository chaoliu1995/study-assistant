package com.chaoliu1995.english.base;

import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.util.Consts;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class BaseController {

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	/**
	 * 获取当前登录用户的id
	 * @return
	 */
	protected Integer getUserId(){
		return ((User)session.getAttribute(Consts.SESSION_USER)).getId();
	}

	/**
	 * 获取当前登录用户
	 * @return
	 */
	protected User getUser(){
		return (User) session.getAttribute(Consts.SESSION_USER);
	}
}
