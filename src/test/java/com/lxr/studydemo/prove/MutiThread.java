package com.lxr.studydemo.prove;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MutiThread
 * @Description TODO
 * @Author Areogel
 * @Date 2021/3/22 13:33
 * @Version 1.0
 */
@Log4j2
public class MutiThread {

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

    int count = 0;
    volatile int count2 = 0;

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

    User user = new User("user0", 0);
    @Test
    public void testThreadPrintNumber2() throws InterruptedException {
        //常量类型值传递，每个线程之间不存在共享变量，加volatile没用
//        Thread t1 = new Thread(() -> printNumber2(count2)); //打印3遍0-9999
//        Thread t2 = new Thread(() -> printNumber2(count2));
//        Thread t3 = new Thread(() -> printNumber2(count2));
//        Thread t1 = new Thread(() -> printNumber31());  //打印到9999
//        Thread t2 = new Thread(() -> printNumber31());
//        Thread t3 = new Thread(() -> printNumber31());

//        Thread t1 = new Thread(() -> printNumber4(user)); //引用类型传递，打印到10001
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

    private void printNumber31() {
        while (count2 < 10000) {
            synchronized (this) {
                if (count2 < 10000) {
                    System.out.println(count2++);
                }
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
     * 测试3个线程交替打印ABC
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

    /**
     * 测试死锁
     * @description
     * @param
     * @return
     */
    @Test
    public void testDeathLock() throws InterruptedException {
        //两个线程持有
        Object a = new Object();
        Object b = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("do A->B");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (b) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println("do B->A");
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
    }

    /**
     * 测试内存溢出
     * @param
     * @return
     */
    @Test
    public void testOutOfMemory() throws InterruptedException {
        List<String[]> list = new ArrayList<>();
        while (true) {
            list.add(new String[102400]);
            TimeUnit.MILLISECONDS.sleep(100);
            log.debug("print");
        }
    }

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50, new ThreadPoolExecutor.DiscardOldestPolicy());

    /**
     * 测试内存泄漏
     * 不断往线程池放入任务
     * @param
     * @return
     */
    @Test
    public void testMemoryLeak() throws InterruptedException {
        executor.setMaximumPoolSize(50);
        while (true) {
            calc();
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    private void calc() {
        List<User> list = getAllUser();
        list.forEach(user -> {
            executor.scheduleWithFixedDelay(() -> user.print(), 1, 1, TimeUnit.SECONDS);
        });
    }

    private List<User> getAllUser() {
        //模拟数据库查询
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User("name", 1);
            list.add(user);
        }
        return list;
    }
}
