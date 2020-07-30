package com.lxr.studydemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface HelloWorldMapper {

    String getCodeName(String code);
}
