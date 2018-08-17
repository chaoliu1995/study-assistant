package com.chaoliu1995.english.filter;

import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/** 
* @Author: ChaoLiu
* @Description: 登录拦截器
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午9:12:40
*/
public class LoginFilter implements Filter {
	
	@Override
	public void destroy() {
		
	}

	private static List<String> urlList = new ArrayList<String>();

	static {
		urlList.add("/");
		urlList.add("/login/commit");
		urlList.add("/login/out");
		urlList.add("/login/status");
		urlList.add("/index.html");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		
		// 当前会话用户为空而且不是请求登录，退出登录，欢迎页面和根目录则退回到应用的根目录
		String servletPath = httpServletRequest.getServletPath();
		if (isBlock(servletPath) && user == null) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			Writer writer = response.getWriter();
			ResultDTO<Object> resultDTO = ResultDTO.init();
			resultDTO.setMessage("用户未登录");
			writer.write(StringUtils.toJson(resultDTO));
			writer.flush();
			writer.close();
		}else{
			httpServletResponse.setCharacterEncoding(Consts.CHARSET);
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		
	}

	private boolean isBlock(String url) {
		if(urlList.contains(url)){
			return false;
		}else if(url.indexOf("/static") != -1){
		    return false;
        }
		return true;
	}
}
