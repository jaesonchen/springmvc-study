package com.asiainfo.springmvc.ajax;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月23日  下午9:54:07
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@RestController
public class AjaxController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static List<User> userList = new ArrayList<User>();
	{
		userList.add(new User("chenzq", "1234", "beijing haidian"));
		userList.add(new User("jaesonchen", "asdf", "beijing changping"));
		userList.add(new User("coolrice", "1234asdf", "beijing chaoyang"));
	}

	@JsonView(Views.Public.class)
	@RequestMapping(value = "/api/search")
	public Response search1(@RequestBody SearchParam param) {

		logger.debug("search1() is executed, param={}", param);
		
		Response response = new Response();
		if (null == param || StringUtils.isEmpty(param.getUsername()) && StringUtils.isEmpty(param.getAddress())) {
			response.setResult("201");
			response.setMessage("Search criteria is empty!");
		} else {
			response.setResult("200");
			response.setData(this.findUsers(param));
			response.setMessage(response.getData().isEmpty() ? "No user!" : "");
		}
		return response;
	}

	@JsonView(Views.Private.class)
	@RequestMapping(value = "/api/search/private")
	public Response search2(@RequestBody SearchParam param) {

		logger.debug("search2() is executed, param={}", param);
		
		Response response = new Response();
		if (null == param || StringUtils.isEmpty(param.getUsername()) && StringUtils.isEmpty(param.getAddress())) {
			response.setResult("201");
			response.setMessage("Search criteria is empty!");
		} else {
			response.setResult("200");
			response.setData(this.findUsers(param));
			response.setMessage(response.getData().isEmpty() ? "No user!" : "");
		}
		return response;
	}
	
	private List<User> findUsers(SearchParam param) {
		List<User> result = new ArrayList<User>();
		for (User user : userList) {
			if (this.search(user.getUsername(), param.getUsername()) && this.search(user.getAddress(), param.getAddress())) {
				result.add(user);
			}
		}
		return result;
	}
	
	private boolean search(String origin, String search) {
		return StringUtils.isEmpty(search) ? true : (origin.indexOf(search) != -1);
	}
}
