package com.asiainfo.springmvc.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月26日  下午10:48:25
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Component
public class DateConverter implements Converter<String, Date> {

	private String pattern = "yyyy-MM-dd HH:mm:ss";

	public DateConverter() {}
	public DateConverter(String pattern) {
		this.pattern = pattern;
	}
	
	/* 
	 * @Description: TODO
	 * @param source
	 * @return
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Date convert(String source) {

		if (StringUtils.isEmpty(source)) {
			return null;
		}
		
		DateFormat df = new SimpleDateFormat(this.pattern);
		try {
			return df.parse(source);
		} catch (ParseException ex) {
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式%s，但格式是[%s]", this.pattern, source)); 
		}
	}
}
