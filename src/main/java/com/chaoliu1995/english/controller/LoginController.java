package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.dto.LoginDTO;
import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.service.LoginService;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.StringUtils;
import com.chaoliu1995.english.util.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/** 
* @Author: ChaoLiu
* @Description: 登录
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午6:14:52
*/
@Api(description = "登录相关接口", basePath = "/login")
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	private LoginService loginService;

    @ApiOperation(value="登录信息提交", notes="")
	@RequestMapping(value="/commit", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
	public ResultDTO<Object> login(@RequestBody LoginDTO loginDTO){
        ResultDTO<Object> resultDTO = ResultDTO.init();
		if(!checkUser(loginDTO)){
			resultDTO.setMessage("请将参数填写完整");
			return resultDTO;
		}
        if(session.getAttribute(Consts.VERITY_CODE) == null){
            session.setAttribute(Consts.VERITY_CODE,Consts.EMPTY_STRING);
        }
        if(session.getAttribute(Consts.VERITY_CODE).toString().equalsIgnoreCase(session.getAttribute(Consts.PREVIOU_VERITY_CODE).toString())) {
            resultDTO.setMessage("每个验证码只可以被使用一次，请重新请求验证码后再进行登录");
            return resultDTO;
        }
        if(!loginDTO.getVerifyCode().equalsIgnoreCase(session.getAttribute(Consts.VERITY_CODE).toString())) {
            resultDTO.setMessage("验证码错误");
            session.setAttribute(Consts.PREVIOU_VERITY_CODE, session.getAttribute(Consts.VERITY_CODE));
            return resultDTO;
        }
		loginService.login(loginDTO,resultDTO);
        if(resultDTO.getStatus().equals(Consts.SUCCESS)){
            User user = new User();
            user.setUsername(loginDTO.getUsername());
            session.setAttribute(Consts.SESSION_USER, user);
        }
		return resultDTO;

	}

    @ApiOperation(value="登录验证码", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "width", value = "宽度", required = false, dataType = "integer"),
            @ApiImplicitParam(name = "height", value = "高度", required = false, dataType = "integer")
    })
    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public void verifyCode(Integer width,Integer height) throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        if(session.getAttribute(Consts.VERITY_CODE) == null){
            session.setAttribute(Consts.PREVIOU_VERITY_CODE,Consts.EMPTY_STRING);
        }else{
            session.setAttribute(Consts.PREVIOU_VERITY_CODE,session.getAttribute(Consts.VERITY_CODE));
        }
        session.setAttribute(Consts.VERITY_CODE,verifyCode);
        if(width == null || width <= 0 || width > 500){
            width = 226;
        }
        if(height == null || height <= 0 || height > 100){
            height = 40;
        }
        VerifyCodeUtils.outputImage(width,height,response.getOutputStream(),verifyCode);
    }

    @ApiOperation(value="退出登录", notes="")
	@RequestMapping(value = "/out", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
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

    @ApiOperation(value="查询登录状态", notes="")
	@RequestMapping(value = "/status", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
	public ResultDTO<Object> status(){
        ResultDTO<Object> resultDTO = ResultDTO.init();
        resultDTO.setStatus(Consts.SUCCESS);
        if(session.getAttribute(Consts.SESSION_USER) == null){
            resultDTO.setData(new Boolean(false));
            return resultDTO;
        }
        resultDTO.setData(new Boolean(true));
        return resultDTO;
	}
	
	/**
	 * 校验用户信息是否完整
	 * @param loginDTO
	 * @return
	 */
	private boolean checkUser(LoginDTO loginDTO){
		if(StringUtils.isEmpty(loginDTO.getUsername()) || StringUtils.isEmpty(loginDTO.getPassword()) || StringUtils.isEmpty(loginDTO.getVerifyCode())){
			return false;
		}else{
			return true;
		}
	}
}
