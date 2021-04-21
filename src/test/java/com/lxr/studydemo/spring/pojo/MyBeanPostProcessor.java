package com.lxr.studydemo.spring.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @ClassName MyBeanPostProcessor
 * @Description TODO
 * @Author Areogel
 * @Date 2021/4/21 16:34
 * @Version 1.0
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " 初始化之前调用postProcessBeforeInitialization");
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " 初始化之后调用postProcessAfterInitialization");
        return bean;
    }
}
