package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.dto.BaseResult;
import com.chaoliu1995.english.dto.LoginDTO;
import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;

/** 
* @Author: ChaoLiu
* @Description: 登录
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午6:14:52
*/
@Api(description = "登录相关接口", basePath = "/login")
@Controller
public class LoginController extends BaseController {

    @ApiOperation(value="登录信息提交", notes="")
	@RequestMapping(value="/login", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
	public BaseResult login(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult){
        BaseResult result = new BaseResult();
        if (bindingResult.hasErrors()){
            result.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return result;
        }
        if(session.getAttribute(Consts.VERITY_CODE) == null){
            session.setAttribute(Consts.VERITY_CODE,Consts.EMPTY_STRING);
        }
        if(session.getAttribute(Consts.VERITY_CODE).toString().equalsIgnoreCase(session.getAttribute(Consts.PREVIOU_VERITY_CODE).toString())) {
            result.setMessage("每个验证码只可以被使用一次，请重新请求验证码后再进行登录");
            return result;
        }
        if(!loginDTO.getVerifyCode().equalsIgnoreCase(session.getAttribute(Consts.VERITY_CODE).toString())) {
            result.setMessage("验证码错误");
            session.setAttribute(Consts.PREVIOU_VERITY_CODE, session.getAttribute(Consts.VERITY_CODE));
            return result;
        }
        UsernamePasswordToken upToken = new UsernamePasswordToken(loginDTO.getUsername(),loginDTO.getPassword());
        upToken.setRememberMe(loginDTO.getRememberMe());
        try {
            subject.login(upToken);
        }catch (AccountException e){
            e.printStackTrace();
            result.setMessage(e.getMessage());
            return result;
        }
        subject.isPermitted(loginDTO.getUsername());
        result.setStatus(Consts.SUCCESS);
        return result;
	}

    @ApiOperation(value="登录验证码", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "width", value = "宽度", required = false, dataType = "integer"),
            @ApiImplicitParam(name = "height", value = "高度", required = false, dataType = "integer")
    })
    @RequestMapping(value = "/login/verifyCode", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
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

    @ApiOperation(value="查询登录状态", notes="")
	@RequestMapping(value = "/login/status", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
	public ResultDTO<Boolean> status(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(Consts.SUCCESS);
        if(session.getAttribute(Consts.SESSION_USER) == null){
            resultDTO.setData(new Boolean(false));
            return resultDTO;
        }
        resultDTO.setData(new Boolean(true));
        return resultDTO;
	}

    @ApiOperation(value="退出登录", notes="")
    @RequestMapping(value = "/login/out", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResult loginOut(){
        BaseResult result = new BaseResult();
        subject.logout();
        result.setStatus(Consts.SUCCESS);
        return result;
    }

}
