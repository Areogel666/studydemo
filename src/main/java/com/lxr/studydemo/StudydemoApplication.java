package com.lxr.studydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@MapperScan("com.lxr.studydemo.dao")
public class StudydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudydemoApplication.class, args);
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("", "");
    }

}
