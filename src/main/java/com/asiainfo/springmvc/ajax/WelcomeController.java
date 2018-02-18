package com.asiainfo.springmvc.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月23日  下午10:18:28
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Controller
public class WelcomeController {
	
	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		return "ajax";
	}
}
