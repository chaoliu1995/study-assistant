package com.chaoliu1995.assistant.controller;

import com.chaoliu1995.assistant.entity.User;
import com.chaoliu1995.assistant.util.Consts;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/8 11:29
 */
public class BaseController {
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
