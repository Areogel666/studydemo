package com.lxr.studydemo.spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class User {
    private String name;
    private int age;

}
