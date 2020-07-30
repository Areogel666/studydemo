package com.lxr.studydemo.service;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class MutiThreadService {

//    @Autowired
    private HelloWorldService helloService = new HelloWorldService();

    private static final int MAX_THREADS = 100;
    private CountDownLatch cdl = new CountDownLatch(MAX_THREADS);

    @Test
    public void testInterface() {

        for (int i = 0; i < MAX_THREADS; i++) {
            Thread t = new Thread(() -> {
                try {
                    cdl.countDown();
                    cdl.await(); //等待
                    String str = helloService.helloWorld();
                    System.out.println("====== " + str + "======");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }

    @Test
    @Ignore
    public void testThreadPool() {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        executor.scheduleAtFixedRate(() -> {
            helloService.helloWorld("liuxiangran");
        }, 0, 10, TimeUnit.MILLISECONDS);
    }

}
