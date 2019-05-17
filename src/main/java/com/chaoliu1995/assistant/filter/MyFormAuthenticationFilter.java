package com.chaoliu1995.assistant.filter;

import com.chaoliu1995.assistant.dto.ResultDTO;
import com.chaoliu1995.assistant.util.Consts;
import com.chaoliu1995.assistant.util.HttpServletRequestUtils;
import com.chaoliu1995.assistant.util.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Writer;

/**
 * @Author: ChaoLiu
 * @Description: 继承shiro的登录验证过滤器，重写onAccessDenied方法
 * @Date: 2018/11/8 14:04
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if(this.isLoginRequest(request, response)) {
            if(this.isLoginSubmission(request, response)) {
                return this.executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            if(HttpServletRequestUtils.isAjax(request)){
                //如果时ajax请求时，返回JSON，提示会话超时
                ResultDTO<Object> resultDTO = new ResultDTO<Object>();
                resultDTO.setStatus(Consts.ERROR);
                resultDTO.setMessage("登录状态已失效，请重新登录！");
                Writer writer = response.getWriter();
                writer.write(StringUtils.toJson(resultDTO));
                writer.close();
            }else{
                this.saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }

}
