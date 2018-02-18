package com.asiainfo.springmvc.converter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月26日  下午10:14:19
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private PhoneNumber phone;
	private Date date;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public PhoneNumber getPhone() {
		return phone;
	}
	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", phone=" + phone + ", date=" + date + "]";
	}
}
