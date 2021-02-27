package com.lxr.studydemo.exam;

import org.junit.jupiter.api.Test;

import java.util.*;

public class boss {

    @Test
    public void removeUser(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(14));
        userList.add(new User(15));
        userList.add(new User(21));
        userList.add(new User(18));
        userList.add(new User(14));
        userList.add(new User(29));
        User.remove(userList);
        userList.parallelStream().map(User::getAge).forEach(System.out::println);
    }


    static class User {
        private Integer age;

        public User(Integer age) {
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }

        //删除userList里年龄大于20的user对象
        public static void remove(List<User> userList){
            Iterator<User> userIterator = userList.iterator();
            while (userIterator.hasNext()) {
                User user = userIterator.next();
                if (user.getAge() > 20){
                    userIterator.remove();
                }
            }
        }
    }

    @Test
    public void testPrint(){
        int[] array = new Random().ints(1000,0,100000).toArray();
        print(array);
    }

    //取最小的10个数，按顺序打印
    public static void print(int[] array){
        Arrays.stream(array).sorted().limit(10).forEach(System.out::println);
    }

    //3个线程，B依赖A,C依赖B
    @Test
    public void testMultiThread(){

        Thread t1 = new Thread(() -> {
            System.out.println("Current Thread:----" + Thread.currentThread().getName());
        }, "A");
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Current Thread:----" + Thread.currentThread().getName());
        }, "B");
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Current Thread:----" + Thread.currentThread().getName());
        }, "C");
        t1.start();
        t2.start();
        t3.start();

    }
}
