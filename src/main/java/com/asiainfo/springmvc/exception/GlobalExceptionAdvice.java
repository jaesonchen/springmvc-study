package com.asiainfo.springmvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年2月2日  下午5:38:22
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler({RuntimeException.class})
    public String globalHandler(RuntimeException ex) {
        
        ex.printStackTrace();
        return "error";
    }
}
