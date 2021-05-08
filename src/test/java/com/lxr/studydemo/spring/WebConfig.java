package com.lxr.studydemo.spring;

import com.lxr.studydemo.spring.pojo.MyBeanPostProcessor;
import com.lxr.studydemo.spring.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.lxr.studydemo.spring")
public class WebConfig {

//    @Bean(name = "myUser")
//    public User user1() {
//        return new User("Tom", 18);
//    }
//
//    @Bean
//    public User user2() {
//        return new User("Tom", 19);
//    }

}
