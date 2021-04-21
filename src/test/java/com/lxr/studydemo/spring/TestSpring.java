package com.lxr.studydemo.spring;

import com.lxr.studydemo.spring.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName TestSpring
 * @Description TODO
 * @Author Areogel
 * @Date 2021/4/21 16:12
 * @Version 1.0
 */
public class TestSpring {

    public static void main(String[] args) {
        // 返回 IOC 容器，使用注解配置，传入配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        // 1.测试初始化、销毁自定义方法
        User user = context.getBean(User.class);
        // 关闭 IOC 容器
        context.close();
        /*调用无参构造器创建User
        user 初始化之前调用postProcessBeforeInitialization
        ...User.postConstruct()...
        ...User.afterPropertiesSet()...
        ...User.userInit()...
        user 初始化之后调用postProcessAfterInitialization
        ...User.preDestroy()...
        ...User.destroy()...
        ...User.userDestory()...*/
    }
}
