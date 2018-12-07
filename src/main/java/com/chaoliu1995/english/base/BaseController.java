package com.chaoliu1995.english.base;

import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.util.Consts;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected Session session;
	protected Subject subject;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		this.subject = SecurityUtils.getSubject();
		this.session = subject.getSession();
	}

	/**
	 * 获取当前登录用户
	 * @return
	 */
	protected User getUser(){
		return (User) session.getAttribute(Consts.SESSION_USER);
	}
}
