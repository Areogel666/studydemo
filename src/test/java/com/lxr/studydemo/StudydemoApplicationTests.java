package com.lxr.studydemo;

import com.lxr.studydemo.spring.WebConfig;
import com.lxr.studydemo.spring.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootTest
class StudydemoApplicationTests {

    @Test
    void contextLoads() {
        // 返回 IOC 容器，使用注解配置，传入配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        User user = context.getBean(User.class);
//        User user = (User)context.getBean("myUser");
        System.out.println(user); //打印User，发现该user对象是通过无参构造获得的
        String[] beanNames = context.getBeanDefinitionNames(); //获得全部组件名称
        Arrays.stream(beanNames).forEach(System.out :: println);
        // 关闭 IOC 容器
//        context.close();
    }

}
