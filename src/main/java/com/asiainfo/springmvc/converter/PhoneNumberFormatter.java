package com.asiainfo.springmvc.converter;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年2月2日  下午3:26:45
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Component
public class PhoneNumberFormatter implements Formatter<PhoneNumber> {

    Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");
    
    /* 
     * TODO
     * @param object
     * @param locale
     * @return
     * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
     */
    @Override
    public String print(PhoneNumber object, Locale locale) {

        if (object == null) {
            return "";
        }
        return new StringBuilder().append(object.getAreaCode()).append("-")
                                  .append(object.getPhoneNumber()).toString();
    }

    /* 
     * TODO
     * @param text
     * @param locale
     * @return
     * @throws ParseException
     * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
     */
    @Override
    public PhoneNumber parse(String text, Locale locale) throws ParseException {

        if (StringUtils.isEmpty(text)) {
            return null;
        }
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setAreaCode(matcher.group(1));
            phoneNumber.setPhoneNumber(matcher.group(2));
            return phoneNumber;
        }
        throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", text));
    }

    public static void main(String[] args) {
        
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addFormatter(new PhoneNumberFormatter());
        PhoneNumber phoneNumber = new PhoneNumber("010", "12345678");
        Assert.isTrue("010-12345678".equals(conversionService.convert(phoneNumber, String.class)));
        Assert.isTrue("010".equals(conversionService.convert("010-12345678", PhoneNumber.class).getAreaCode()));
    }
}
