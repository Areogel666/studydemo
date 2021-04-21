package com.lxr.studydemo.spring.pojo;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName User
 * @Description TODO
 * @Author Areogel
 * @Date 2021/4/21 16:05
 * @Version 1.0
 */
@Data
public class User implements InitializingBean, DisposableBean {
    private String name;
    private int age;

    public User() {
        System.out.println("调用无参构造器创建User");
    }

    public void userInit() {
        System.out.println("...User.userInit()...");
    }

    public void userDestory() {
        System.out.println("...User.userDestory()...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("...User.destroy()...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("...User.afterPropertiesSet()...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("...User.postConstruct()...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("...User.preDestroy()...");
    }
}
