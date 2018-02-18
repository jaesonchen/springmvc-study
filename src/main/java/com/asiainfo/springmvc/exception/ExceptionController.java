package com.asiainfo.springmvc.exception;

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
 * @date         2018年2月2日  下午5:40:20
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Controller
public class ExceptionController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping(value = "/runtime", method = RequestMethod.GET)
    public String runtime(Map<String, Object> model) {

        logger.debug("exception() is executed!");
        throw new RuntimeException("nullpointer exception!");
    }
    
    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public String exception(Map<String, Object> model) throws Exception {

        logger.debug("exception() is executed!");
        throw new Exception("exception!");
    }
}
