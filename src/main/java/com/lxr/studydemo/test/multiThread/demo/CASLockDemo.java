package com.lxr.studydemo.test.multiThread.demo;

import com.lxr.studydemo.test.multiThread.entity.CASLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试同步锁
 */
public class CASLockDemo {

    private static volatile int total = 11000;
    private static Object lock = new Object();

    public static void main(String[] args) {

        CASLock casLock = new CASLock();
        AtomicInteger atomicTotal = new AtomicInteger(total);
        List<Thread> tList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            Thread t = new Thread(() -> testNormalLock());
//            Thread t = new Thread(() -> testSynLock());
//            Thread t = new Thread(() -> testCASLock(casLock));
            Thread t = new Thread(() -> testCAS(atomicTotal));
            tList.add(t);
        }

        for (Thread t : tList) {
            t.start();
        }

        for (Thread t : tList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        try {
//            Thread.currentThread().sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("剩余：" + total);
    }

    private static void testNormalLock() {
        for (int j = 0; j < 1000; j++) {
            total--;
        }
    }

    private static void testSynLock() {
        synchronized (lock) {
            for (int j = 0; j < 1000; j++) {
                total--;
            }
        }
    }

    private static void testCASLock(CASLock casLock) {
        casLock.lock();
        for (int j = 0; j < 1000; j++) {
            total--;
        }
        casLock.unlock();
    }

    private static void testCAS(AtomicInteger atomicTotal) {
        for (int j = 0; j < 1000; j++) {
            while (true) {
                int expected = atomicTotal.get();
                int update = expected - 1;
                if (atomicTotal.compareAndSet(expected, update)) {
                    total = atomicTotal.get();
                    break;
                }
            }
        }

    }
}
