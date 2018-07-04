package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.service.LoginService;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 
* @Author: ChaoLiu
* @Description: 登录
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午6:14:52
*/
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/commit", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<Object> login(@RequestBody User user){
        ResultDTO<Object> resultDTO = ResultDTO.init();
		if(!checkUser(user)){
			resultDTO.setMessage("请将数据填写完整");
			return resultDTO;
		}
		Long currentTimeMillis = (Long)session.getAttribute(Consts.LOGIN_TIME);
		if(currentTimeMillis != null && (System.currentTimeMillis() - currentTimeMillis) < 60000){
            resultDTO.setMessage("两次登录请求之间的间隔不得小于一分钟");
            return resultDTO;
		}
		loginService.login(user,resultDTO);
		if(resultDTO.getStatus().equals(Consts.SUCCESS)){
			session.setAttribute(Consts.SESSION_USER, user);
			session.setMaxInactiveInterval(36000);
			return resultDTO;
		}else{
			session.setAttribute(Consts.LOGIN_TIME, System.currentTimeMillis());
			return resultDTO;
		}
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/out")
	public ResultDTO<Object> loginOut(){
		ResultDTO<Object> resultDTO = ResultDTO.init();
		if(session != null){
			session.invalidate();
		}else{
			resultDTO.setMessage("当前用户未登录");
			return resultDTO;
		}
		resultDTO.setStatus(Consts.SUCCESS);
		return resultDTO;
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
