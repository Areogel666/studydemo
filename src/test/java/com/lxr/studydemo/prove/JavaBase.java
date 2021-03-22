package com.lxr.studydemo.prove;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName JavaBase
 * @Description TODO
 * @Author Areogel
 * @Date 2021/3/22 13:32
 * @Version 1.0
 */
public class JavaBase {

    class User {
        String name;
        int age;

        public User (String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void print() {
            System.out.println(this.age);
        }
    }

    /**
     * 测试equals
     * @description
     * @author Areogel
     * @date 2021/3/10 12:02
     */
    @Test
    public void testEquals() {
        Integer a = 128;
        Integer b = 128;
        Integer a1 = -128;
        Integer b1 = -128;
        String c = "abc";
        String d = "abc";
        String e = "155";
        String f = "155";
        System.out.println(a == b);
        System.out.println(a1 == b1);
        System.out.println(c == d);
        System.out.println(e == f);
    }

    /**
     * 测试Stream ListToMap报错
     * @description
     * @author Areogel
     * @date 2021/3/10 12:03
     */
    @Test
    public void testStreamError() {
        ArrayList<User> list = new ArrayList<>();
        User jack1 = new User("jack", 20);
        User jack2 = new User("jack", 24);
        list.add(jack1);
        list.add(jack2);
        Map<String, Integer> collect1 = list.stream().collect(Collectors.toMap(user -> user.name, user -> user.age, Math :: max));
        //throwingMerger()  java.lang.IllegalStateException: Duplicate key 20
        Map<String, Integer> collect = list.stream().collect(Collectors.toMap(user -> user.name, user -> user.age));
        System.out.println(collect.size());
    }
}
