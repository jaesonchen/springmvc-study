package com.asiainfo.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年2月2日  下午3:53:34
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class StopWatchHandlerInterceptor extends HandlerInterceptorAdapter {

    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        startTimeThreadLocal.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        System.out.println(
                String.format("%s consume %d millis", request.getRequestURI(), 
                        System.currentTimeMillis() - startTimeThreadLocal.get()));
    }
}
