package com.chaoliu1995.english.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.util.Consts;


/** 
* @Author: ChaoLiu
* @Description: 校验是否登录
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午9:12:40
*/
@WebFilter(filterName="loginFilter", urlPatterns="*.do")
public class LoginFilter implements Filter {
	
	@Override
	public void destroy() {
		
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
		List<String> pathList = notNeedSessionCheck();
		chain.doFilter(request, response);
		/*if (!pathList.contains(servletPath) && user == null) {
			request.getRequestDispatcher("/login/page.do").forward(request, response);
		}else{
			httpServletResponse.setCharacterEncoding(Consts.CHARSET);
			chain.doFilter(request, response);
		}*/
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		
	}

	private List<String> notNeedSessionCheck() {
		String[] paths = new String[] { "/login/commit.do","/login/out.do","/login/page.do" };
		return Arrays.asList(paths);
	}
}
