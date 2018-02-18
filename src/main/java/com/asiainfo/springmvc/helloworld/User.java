package com.asiainfo.springmvc.helloworld;

import java.io.Serializable;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年2月2日  上午10:18:06
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class User implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private int age;
    
    public User() {
    }
    public User(String userId, String userName, int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", age=" + age + "]";
    }
}
