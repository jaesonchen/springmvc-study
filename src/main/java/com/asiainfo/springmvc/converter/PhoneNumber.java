package com.asiainfo.springmvc.converter;

import java.io.Serializable;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月26日  下午10:14:52
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class PhoneNumber implements Serializable {

	private static final long serialVersionUID = 1L;
	private String areaCode;
	private String phoneNumber;
	
	public PhoneNumber() {}
	public PhoneNumber(String areaCode, String phoneNumber) {
		this.areaCode = areaCode;
		this.phoneNumber = phoneNumber;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(areaCode == null ? "" : areaCode).append(" - ")
			.append(phoneNumber == null ? "" : phoneNumber);
		return sb.toString();
	}
}
