package com.lxr.studydemo;

import com.lxr.studydemo.spring.WebConfig;
import com.lxr.studydemo.spring.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class StudydemoApplicationTests {

    @Test
    void contextLoads() {
//        // 返回 IOC 容器，使用注解配置，传入配置类
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//        User user = context.getBean(User.class);
//        // 关闭 IOC 容器
//        context.close();
    }

}
