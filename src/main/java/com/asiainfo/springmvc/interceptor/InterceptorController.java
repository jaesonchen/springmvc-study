package com.asiainfo.springmvc.interceptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年2月2日  下午4:17:05
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Controller
public class InterceptorController {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        logger.debug("index() is executed!");
        model.put("title", "World");
        model.put("desc", "Spring mvc Hello world example!");
        return "index";
    }
    
    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public String monitor(Map<String, Object> model) {

        logger.debug("monitor() is executed!");
        return "index";
    }
}
