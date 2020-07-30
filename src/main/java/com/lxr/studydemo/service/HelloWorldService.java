package com.lxr.studydemo.service;

import com.lxr.studydemo.dao.HelloWorldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    @Autowired
    private HelloWorldMapper mapper;

    public String helloWorld() {
        return "helloWorld";
    }

    public String helloWorld(String code) {
        return mapper.getCodeName(code);
    }
}
