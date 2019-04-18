package com.asiainfo.springmvc.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Converter是一般工具，可以将一种类型转换成另一种类型，既可以用在web层，也可以用在其它层中。
 * 
 * @author       zq
 * @date         2017年5月26日  下午10:20:54
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Component
public class PhoneNumberConverter implements Converter<String, PhoneNumber> {

	final Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");
	
	/* 
	 * @Description: TODO
	 * @param source
	 * @return
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public PhoneNumber convert(String source) {
		
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		Matcher matcher = pattern.matcher(source);
		if (matcher.matches()) {
			PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setAreaCode(matcher.group(1));
            phoneNumber.setPhoneNumber(matcher.group(2));
            return phoneNumber;
		}
        throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", source));
	}
	
    public static void main(String[] args) {
        
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new PhoneNumberConverter());
        String phoneNumberStr = "010-12345678";
        PhoneNumber phoneNumber = conversionService.convert(phoneNumberStr, PhoneNumber.class);
        Assert.isTrue("010".equals(phoneNumber.getAreaCode()));
        Assert.isTrue(Boolean.valueOf(true).equals(conversionService.convert("1", Boolean.class)));
        Assert.isTrue(4 == conversionService.convert("1,2,3,4", java.util.List.class).size());
    }
}
