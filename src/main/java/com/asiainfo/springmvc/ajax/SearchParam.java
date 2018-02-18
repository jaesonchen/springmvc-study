package com.asiainfo.springmvc.ajax;

import java.io.Serializable;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月23日  下午9:56:28
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class SearchParam implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String address;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "SearchParam [username=" + username + ", address=" + address + "]";
	}
}
