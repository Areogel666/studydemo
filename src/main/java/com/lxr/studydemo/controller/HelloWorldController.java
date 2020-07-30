package com.lxr.studydemo.controller;

import com.lxr.studydemo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldService service;

    @RequestMapping("/world")
    public String helloWorld(){
        String str = service.helloWorld();
        System.out.println(str);
        return str;
    }
}
