package com.asiainfo.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年2月2日  下午4:13:27
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class RequestLogHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println(request.getRequestURI() + " called!");
        return true;
    }
}
