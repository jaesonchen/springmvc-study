package com.asiainfo.springmvc.ajax;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月23日  下午9:42:50
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonView(Views.Public.class)
	private String username;
	
	private String password;
	
	@JsonView(Views.Public.class)
	private String address;
	
	public User() {}
	public User(String username, String password, String address) {
		this.username = username;
		this.password = password;
		this.address = address;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", address=" + address + "]";
	}
}
