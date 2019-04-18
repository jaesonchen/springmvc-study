package com.asiainfo.springmvc.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class DateFormatter implements Formatter<Date> {

    private String pattern = "yyyy-MM-dd HH:mm:ss";

    public DateFormatter() {}
    public DateFormatter(String pattern) {
        this.pattern = pattern;
    }
    
    @Override
    public String print(Date object, Locale locale) {
        return new SimpleDateFormat(this.pattern).format(object);
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(this.pattern);
        try {
            return df.parse(text);
        } catch (ParseException ex) {
            throw new IllegalArgumentException(String.format("类型转换失败，需要格式%s，但格式是[%s]", this.pattern, text)); 
        }
    }
}
