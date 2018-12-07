package com.chaoliu1995.english.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/7 14:23
 */
public class HttpServletRequestUtils {

    private HttpServletRequestUtils(){}

    /**
     * 判断请求是否是ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
