package com.chaoliu1995.english.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.service.LoginService;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.StringUtils;

/** 
* @Author: ChaoLiu
* @Description: 登录控制层
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午6:14:52
*/
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/page")
	public String index(){
		return checkPlatForm("login");
	}
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/commit",method=RequestMethod.POST,produces="text/json;charset=utf-8")
	@ResponseBody
	public String login(User user){
		Map<String,String> resultMap = getResultMap();
		if(!checkUser(user)){
			resultMap.put(Consts.MESSAGE, "请将数据填写完整");
		}
		Long currentTimeMillis = (Long)session.getAttribute(Consts.LOGIN_TIME);
		if(currentTimeMillis != null && (System.currentTimeMillis() - currentTimeMillis) < 120000){
			resultMap.put(Consts.MESSAGE, "两次登录请求之间的间隔不得小于两分钟");
			return toJson(resultMap);
		}
		resultMap = loginService.login(user,resultMap);
		if(resultMap.get(Consts.STATUS).equals(Consts.SUCCESS)){
			session.setAttribute(Consts.SESSION_USER, user);
			session.setMaxInactiveInterval(36000);
			return toJson(resultMap);
		}else{
			session.setAttribute(Consts.LOGIN_TIME, System.currentTimeMillis());
			return toJson(resultMap);
		}
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/out")
	public String loginOut(){
		if(session != null){
			session.invalidate();
		}
		return checkPlatForm("login");
	}
	
	/**
	 * 校验用户信息是否完整
	 * @param user
	 * @return
	 */
	private boolean checkUser(User user){
		if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
			return false;	
		}else{
			return true;
		}
	}
}
