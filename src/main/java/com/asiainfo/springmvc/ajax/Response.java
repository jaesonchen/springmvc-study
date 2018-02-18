package com.asiainfo.springmvc.ajax;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月23日  下午9:51:01
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonView(Views.Public.class)
	private String result;
	
	@JsonView(Views.Public.class)
	private String message;
	
	@JsonView(Views.Public.class)
	private List<?> data;
	
	@JsonView(Views.Private.class)
	private String desc;

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Response [result=" + result + ", message=" + message + ", data=" + data + ", desc=" + desc + "]";
	}
}
