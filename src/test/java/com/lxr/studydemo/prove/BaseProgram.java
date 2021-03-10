package com.lxr.studydemo.prove;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @ClassName BaseProgram
 * @Description TODO
 * @Author Areogel
 * @Date 2021/3/9 9:45
 * @Version 1.0
 */
public class BaseProgram {

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

    int count = 0;

    /**
     * 测试顺序打印数字
     * @description
     * @author Areogel
     * @date 2021/3/10 12:03
     */
    @Test
    public void testThreadPrintNumber() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (this) {
                printNumber();
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (this) {
                printNumber();
            }
        });
        Thread t3 = new Thread(() -> {
            synchronized (this) {
                printNumber();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    private void printNumber() {
        while (count < 10000) {
            System.out.println(count++);
        }
    }

    volatile int count2 = 0;
    User user = new User("user0", 0);
    @Test
    public void testThreadPrintNumber2() throws InterruptedException {
        //常量类型值传递，每个线程之间不存在共享变量，加volatile没用
//        Thread t1 = new Thread(() -> printNumber2(count2));
//        Thread t2 = new Thread(() -> printNumber2(count2));
//        Thread t3 = new Thread(() -> printNumber2(count2));
//        Thread t1 = new Thread(() -> printNumber3());  //打印到10001
//        Thread t2 = new Thread(() -> printNumber3());
//        Thread t3 = new Thread(() -> printNumber3());

//        Thread t1 = new Thread(() -> printNumber4(user)); //打印到10001
//        Thread t2 = new Thread(() -> printNumber4(user));
//        Thread t3 = new Thread(() -> printNumber4(user));
        Thread t1 = new Thread(() -> printNumber5()); //非原子
        Thread t2 = new Thread(() -> printNumber5());
        Thread t3 = new Thread(() -> printNumber5());
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    private void printNumber2(Integer count) {
        while (count < 10000) {
            synchronized (this) {
                System.out.println(count++);
            }
        }
    }

    private void printNumber3() {
        while (count2 < 10000) {
            synchronized (this) {
                System.out.println(count2++);
            }
        }
    }

    private void printNumber4(User user) {
        while (user.age < 10000) {
            synchronized (this) {
                System.out.println(user.age++);
            }
        }
    }

    private void printNumber5() {
        while (count2 < 10000) {
            System.out.println(count2++);
        }
    }

    /**
     * 测试交替打印ABC
     * @description
     * @author Areogel
     * @date 2021/3/10 12:06
     */
    @Test
    public void testThreadPrintABC() throws InterruptedException {
//        Thread t1 = new Thread(() -> printABC1("A", 0));
//        Thread t2 = new Thread(() -> printABC1("B", 1));
//        Thread t3 = new Thread(() -> printABC1("C", 2));
//        Thread t1 = new Thread(() -> printABC2("A", 0));
//        Thread t2 = new Thread(() -> printABC2("B", 1));
//        Thread t3 = new Thread(() -> printABC2("C", 2));
        Condition[] conditions = new Condition[]{lock.newCondition(),lock.newCondition(),lock.newCondition()};
        Thread t1 = new Thread(() -> printABC3("A", 0, conditions));
        Thread t2 = new Thread(() -> printABC3("B", 1, conditions));
        Thread t3 = new Thread(() -> printABC3("C", 2, conditions));
//        Semaphore semaphoreA = new Semaphore(1);
//        Semaphore semaphoreB = new Semaphore(0);
//        Semaphore semaphoreC = new Semaphore(0);
//        Thread t1 = new Thread(() -> printABC4("A", semaphoreA, semaphoreB));
//        Thread t2 = new Thread(() -> printABC4("B", semaphoreB, semaphoreC));
//        Thread t3 = new Thread(() -> printABC4("C", semaphoreC, semaphoreA));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    private void printABC1(String cur, int residual) {
        while (count / 3 < 10) {
            synchronized (this) {
                if (count / 3 < 10 && count % 3 == residual) {
                    System.out.println(cur);
                    count++;
                }
            }
        }

    }

    private void printABC2(String cur, int residual) {
        while (count / 3 < 10) {
            synchronized (this) {
                if (count % 3 != residual) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(cur);
                    count++;
                    this.notifyAll();
                }
            }
        }

    }

    ReentrantLock lock = new ReentrantLock();
    private void printABC3(String cur, int residual, Condition[] condition) {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (count % 3 != residual) condition[residual].await();
                System.out.println(cur);
                condition[++count % 3].signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    private void printABC4(String cur, Semaphore current, Semaphore next) {
        for (int i = 0; i < 10; i++) {
            try {
                current.acquire();
                System.out.println(cur);
                next.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
