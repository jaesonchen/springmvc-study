package com.asiainfo.springmvc.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月26日  下午10:35:15
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Controller
public class ConverterController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	//converter1?username=jaesonchen&phone.areaCode=021&phone.phoneNumber=10001111
	@RequestMapping("/converter1")
	@ResponseBody
	public User converter1(User user, BindingResult result) {
		
		logger.debug("converter1() is executed, user={}", user);
		
		if (result.hasErrors()) {
			logger.debug("error={}", result.getErrorCount());
		} else {
			logger.debug("username={}, phone={}", user.getUsername(), user.getPhone());
		}
	    return user;
	}
	
	//converter2?userid=1002&username=czq&phone=010-1231234&date=2017-05-22 18:30:20
	@RequestMapping("/converter2")
	@ResponseBody
	public User converter2(User user) {
		
		logger.debug("converter2() is executed, user={}", user);
	    return user;
	}
}
