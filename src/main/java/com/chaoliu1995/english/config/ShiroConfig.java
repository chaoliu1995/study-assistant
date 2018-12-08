package com.chaoliu1995.english.config;

import com.chaoliu1995.english.filter.MyFormAuthenticationFilter;
import com.chaoliu1995.english.service.realm.MyShiroRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/7 14:16
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        return new MyShiroRealm();
    }

    @Bean
    public SimpleCookie sessionIdCookie(){
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName("shiroid");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(18000);
        return cookie;
    }

    @Bean
    public SimpleCookie rememberMeCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(259200);   //三十天
        return cookie;
    }

    @Bean
    public RememberMeManager rememberMeManager(SimpleCookie rememberMeCookie){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie);
        cookieRememberMeManager.setCipherKey("j7hs53deg#h39".getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager(SimpleCookie sessionIdCookie){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setGlobalSessionTimeout(21600000);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(
            MyShiroRealm myShiroRealm,
            DefaultWebSessionManager sessionManager,
            EhCacheManager cachaManager, RememberMeManager rememberMeManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(cachaManager);
        securityManager.setRealm(myShiroRealm);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;

    }

    @Bean
    public EhCacheManager cachaManager(){
        EhCacheManager cachaManager = new EhCacheManager();
        cachaManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cachaManager;
    }

    @Bean(name = "shiroFilterBean")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/");
        Map<String ,String> filterMap = new HashMap<String ,String>();
        filterMap.put("/login/**","anon");
        filterMap.put("/login","anon");
        filterMap.put("/static/**","anon");

        //swagger
        filterMap.put("/swagger-ui.html","anon");
        filterMap.put("/api","anon");
        filterMap.put("/api/**","anon");
        filterMap.put("/v2/api-docs","anon");
        filterMap.put("/swagger-resources/**","anon");
        filterMap.put("/webjars/springfox-swagger-ui/**","anon");

        filterMap.put("/**","authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        MyFormAuthenticationFilter myFormAuthenticationFilter = new MyFormAuthenticationFilter();
        shiroFilter.getFilters().put("authc",myFormAuthenticationFilter);
        return shiroFilter;

    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }
}
