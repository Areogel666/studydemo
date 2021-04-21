package com.lxr.studydemo.spring;

import com.lxr.studydemo.spring.pojo.MyBeanPostProcessor;
import com.lxr.studydemo.spring.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName WebConfig
 * @Description TODO
 * @Author Areogel
 * @Date 2021/4/21 16:04
 * @Version 1.0
 */
@Configuration
public class WebConfig {

    @Bean(initMethod = "userInit", destroyMethod = "userDestory")
    public User user() {
        return new User();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor () {
        return new MyBeanPostProcessor();
    }
}
