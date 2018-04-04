package com.asiainfo.springmvc.helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年5月22日  下午9:25:05
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Controller
//将当前controller中ModelMap的user属性自动存入session中
@SessionAttributes("user")
public class HelloController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
    @RequestMapping(value = "/sendsms", method = RequestMethod.POST)
    public Map<String, Object> post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Map<String, Object> result = new HashMap<>();
        InputStream is= null;
        BufferedReader reader = null;
        try {
            StringBuilder data = new StringBuilder();
            is = request.getInputStream();
            if (is != null) {
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                char[] buff = new char[128];
                int bytesRead = -1;
                while ((bytesRead = reader.read(buff)) > 0) {
                    data.append(buff, 0, bytesRead);
                }
            }
            System.out.println("接收到客户端的短信发送请求，报文：" + data.toString());
            result.put("status", 0);
            result.put("desc", "发送成功");
        } catch (Exception ex) {
            result.put("status", 1);
            result.put("desc", "发送失败");
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {}
            }
        }
        return result;
    }
	
    //返回视图名称
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        logger.debug("index() is executed!");

        model.put("title", "World");
        model.put("desc", "Spring mvc Hello world example!");

        return "index";
    }
    
    //返回视图模型
    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        logger.debug("hello() is executed - $name {}", name);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("title", name);
        model.addObject("desc", "Spring mvc Hello world example!");

        return model;
    }
    
    //指定content-type和accept
    @RequestMapping(value = "/consume", method = RequestMethod.GET, consumes="application/json", produces="application/json")
    public String consume() {
        
        logger.debug("consume() is executed!");
        return "index";
    }
    
    //无返回值
    @RequestMapping(value = "/void")
    public void withoutReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("withoutReturn() is executed!");
        response.getWriter().write("Hello World!!");
    }
    
	//在不给定参数注解的情况下:若要绑定的对象是简单类型，调用@RequestParam来处理的；若要绑定的对象是引用类型，调用@ModelAttribute来处理的。
	@RequestMapping(value = "/param")
	public String param(@RequestParam("name") String name, User user) {
	    
	    logger.debug("param() is executed - $name {}, $user {}", name, user);
	    return "index";
	}
	
    //设置参数缺省值，required默认为true
    //require=false时，如果request没有传入相应的参数，则会将null赋予userid，此时会报错并要求使用包装类型Integer
    @RequestMapping("/default")
    public String param1(@RequestParam(value="age", required=true, defaultValue="20") int age) {
        
        logger.debug("param1() is executed - $age {}", age);
        return "index";
    }
    
    //参数数组 url?role=admin&role=user
    @RequestMapping("/paramArray")
    public String param2(@RequestParam("role") String[] roleList) {
        
        logger.debug("param2() is executed - $role {}", Arrays.toString(roleList));
        return "index";
    }
    
    //Map参数 url?id=1001&name=chenzq
    @RequestMapping("/paramMap")
    public String param3(@RequestParam Map<String, Object> map) {
        
        logger.debug("param3() is executed - $map {}", Arrays.toString(map.entrySet().toArray()));
        return "index";
    }

    //@RequestHeader 注解，可以把Request请求header部分的值绑定到方法的参数上。
    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public String header(@RequestHeader("Accept-Encoding") String encoding, 
            @RequestHeader("Accept") String[] accept,
            @CookieValue(value="JSESSIONID", defaultValue="") String sessionId) {
        
        logger.debug("Accept-Encoding={}\nAccept={}\nJSESSIONID={}", encoding, Arrays.toString(accept), sessionId);
        return "index";
    }
    
    //@RequestBody通常用来处理Content-Type: 不是application/x-www-form-urlencoded编码的内容，例如application/json, application/xml等
    //它是通过使用HandlerAdapter 配置的HttpMessageConverters来解析post data body，然后绑定到相应的bean上的。
    @RequestMapping(value = "/requestbody", method = RequestMethod.POST)
    public void requestbody(@RequestBody User user, Writer writer) throws IOException {
        writer.write(user.toString());
    }
    
    //@SessionAttributes设置session属性
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam String userId, ModelMap model) {
        
        model.addAttribute("user", new User(userId, "chenzq", 30));
        return "index";
    }
    
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public String session(@ModelAttribute("user") User user) {
        
        logger.debug("session user={}", user);
        return "index";
    }
    
    //@ModelAttribute
    //用于方法上时：  通常用来在处理@RequestMapping的方法之前，为请求绑定需要从后台查询的model
    //这种方式实际的效果就是在调用@RequestMapping的方法之前，为request对象的model里put（“user”， User）
    @ModelAttribute
    public User prepare(@RequestParam(required=false) String userId) {
        
        logger.debug("prepare userId={}", userId);
        return new User(userId, "chenzq", 20);
    }
    
    //@ModelAttribute
    //用于参数上时： 用来通过名称对应，把相应名称的值绑定到注解的参数bean上；要绑定的值来源于：
    //@SessionAttributes 启用的attribute 对象上；
    //@ModelAttribute 用于方法上时指定的model对象；
    //上述两种情况都没有时，new一个需要绑定的bean对象；
    //然后在request中按名称对应的方式把值绑定到bean中。
    @RequestMapping(value="/attribute", method = RequestMethod.GET)
    public String attribute(@ModelAttribute User user) {
        
        logger.debug("attribute user={}", user);
        return "hello";
    }
    
    // "forward:/hello" => 转发到能够匹配 /hello 的 controller 上  
    @RequestMapping("/forward")
    public String forward() {
        return "forward:/hello";
    }
    
    @RequestMapping("/redirect")
    public ModelAndView redirect() {
        return new ModelAndView("redirect:/hello");
    }
}
