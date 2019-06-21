package com.chaoliu1995.assistant.controller;

import com.chaoliu1995.assistant.config.MemoryStorage;
import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.util.Consts;
import com.chaoliu1995.assistant.util.StringUtils;
import com.chaoliu1995.assistant.util.VerifyCodeUtils;
import io.nayuki.qrcodegen.QrCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/10/29 17:53
 */
@Api(tags = "登录", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@Controller
public class LoginController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemoryStorage memoryStorage;

    @ApiOperation(value="登录")
    @PostMapping("/login")
    @ResponseBody
    public BaseResult login(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult){
        BaseResult baseResult = new BaseResult();
        if (bindingResult.hasErrors()){
            baseResult.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return baseResult;
        }
        if(session.getAttribute(Consts.VERITY_CODE) == null){
            session.setAttribute(Consts.VERITY_CODE,Consts.EMPTY_STRING);
        }
        if(session.getAttribute(Consts.VERITY_CODE).toString().equalsIgnoreCase(session.getAttribute(Consts.PREVIOU_VERITY_CODE).toString())) {
            baseResult.setMessage("每个验证码只可以被使用一次，请重新请求验证码后再进行登录");
            return baseResult;
        }
        if(!loginDTO.getVerifyCode().equalsIgnoreCase(session.getAttribute(Consts.VERITY_CODE).toString())) {
            baseResult.setMessage("验证码错误");
            session.setAttribute(Consts.PREVIOU_VERITY_CODE, session.getAttribute(Consts.VERITY_CODE));
            return baseResult;
        }
        UsernamePasswordToken upToken = new UsernamePasswordToken(loginDTO.getUsername(),loginDTO.getPassword());
        upToken.setRememberMe(loginDTO.getRememberMe());
        try {
            subject.login(upToken);
        }catch (AccountException e){
            e.printStackTrace();
            baseResult.setMessage(e.getMessage());
            return baseResult;
        }
        subject.isPermitted(loginDTO.getUsername());
        baseResult.setStatus(Consts.SUCCESS);
        return baseResult;
    }

    @ApiOperation(value="登录验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "width", value = "宽度", required = false, dataType = "integer"),
            @ApiImplicitParam(name = "height", value = "高度", required = false, dataType = "integer")
    })
    @GetMapping(value = "/login/verifyCode", produces = MediaType.IMAGE_JPEG_VALUE)
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

    @ApiOperation(value="查询登录状态")
    @PostMapping("/login/status")
    @ResponseBody
    public ResultDTO<Boolean> status(){
        ResultDTO<Boolean> resultDTO = new ResultDTO<>();
        if(session.getAttribute(Consts.SESSION_USER) == null){
            resultDTO.setData(new Boolean(false));
        }else{
            resultDTO.setData(new Boolean(true));
        }
        resultDTO.setStatus(Consts.SUCCESS);
        return resultDTO;
    }

    @ApiOperation(value="退出登录")
    @PostMapping("/login/out")
    @ResponseBody
    public BaseResult loginOut(){
        BaseResult result = new BaseResult();
        if(session.getAttribute(Consts.SESSION_USER) != null){
            subject.logout();
        }
        result.setStatus(Consts.SUCCESS);
        return result;
    }

    @ApiOperation(value="微信小程序登录")
    @PostMapping("/login/wechat/miniProgram")
    @ResponseBody
    public ResultDTO<Boolean> miniProgramLogin(@RequestBody WeChatMiniProgramLoginDTO loginDTO){
        ResultDTO<Boolean> resultDTO = new ResultDTO<>();
        if(StringUtils.isEmpty(loginDTO.getJsCode())){
            resultDTO.setMessage(Consts.PARAMETER_IS_NULL);
            return resultDTO;
        }
        logger.debug("js_code: "+loginDTO.getJsCode());
        UsernamePasswordToken upToken = new UsernamePasswordToken(loginDTO.getJsCode(),Consts.MINI_PROGRAM);
        try{
            subject.login(upToken);
        }catch (AccountException e){
            e.printStackTrace();
            resultDTO.setMessage(e.getMessage());
            return resultDTO;
        }
        subject.isPermitted(loginDTO.getJsCode());
        resultDTO.setStatus(Consts.SUCCESS);
        return resultDTO;
    }

    @ApiOperation(value="获取登录二维码")
    @GetMapping(value = "/login/qrCode")
    @ResponseBody
    public void qrCode() throws IOException{
        if(session.getAttribute(Consts.SESSION_USER) != null){
            return;
        }
        if(session.getAttribute(Consts.LOGIN_CODE) != null){
            String tempCode = (String)session.getAttribute(Consts.LOGIN_CODE);
            memoryStorage.remove(tempCode);
        }
        String loginCode = UUID.randomUUID().toString();
        session.setAttribute(Consts.LOGIN_CODE,loginCode);
        memoryStorage.put(loginCode,null);
        QrCode qrCode = QrCode.encodeText(loginCode, QrCode.Ecc.MEDIUM);
        BufferedImage img = qrCode.toImage(6, 2);
        ImageIO.write(img, "png",response.getOutputStream());
    }

    @ApiOperation(value="扫码登录")
    @PostMapping("/login/scanCode")
    @ResponseBody
    public BaseResult scanCode(@RequestBody ScanCodeLoginDTO loginDTO){
        BaseResult result = new BaseResult();
        if(StringUtils.isEmpty(loginDTO.getLoginCode())){
            result.setMessage("参数不完整");
            return result;
        }
        if(session.getAttribute(Consts.SESSION_USER) == null){
            result.setMessage("只有登录后才可以调用此接口");
            return result;
        }

        if(!memoryStorage.containsKey(loginDTO.getLoginCode())){
            result.setMessage("无效的登录码");
            return result;
        }
        memoryStorage.put(loginDTO.getLoginCode(),getUserId());
        result.setStatus(Consts.SUCCESS);
        return result;
    }

    @ApiOperation(value="检测是否已扫码登录")
    @PostMapping("/login/scanCode/doLogin")
    @ResponseBody
    public ResultDTO<Boolean> scanCodeLogin(){
        ResultDTO result = new ResultDTO();
        result.setData(false);
        result.setStatus(Consts.SUCCESS);
        if(session.getAttribute(Consts.SESSION_USER) != null){
            result.setData(true);
            return result;
        }
        String loginCode = (String)session.getAttribute(Consts.LOGIN_CODE);
        if(loginCode == null){
            return result;
        }
        if(memoryStorage.getUserId(loginCode) != null){
            UsernamePasswordToken upToken = new UsernamePasswordToken(memoryStorage.getUserId(loginCode).toString(),Consts.SCAN_CODE);
            try{
                subject.login(upToken);
            }catch (AccountException e){
                e.printStackTrace();
                result.setMessage(e.getMessage());
                result.setStatus(Consts.ERROR);
                return result;
            }
            subject.isPermitted(loginCode);
            result.setData(true);
            return result;
        }
        return result;
    }
}
