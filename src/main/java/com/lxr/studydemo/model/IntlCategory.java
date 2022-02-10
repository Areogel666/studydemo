package com.lxr.studydemo.model;

import lombok.Data;

@Data
public class IntlCategory {

    private int categoryid ;
    private CategoryName categoryname;

    @Data
    public class CategoryName {
        private String zh_CN;

    }
}
