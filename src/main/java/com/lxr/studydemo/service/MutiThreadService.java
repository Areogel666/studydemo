package com.lxr.studydemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class MutiThreadService {

    @Autowired
    private HelloWorldService helloService;

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    @Test
    public void testThreadPool() {


        executor.scheduleAtFixedRate(() -> {
            helloService.helloWorld("liuxiangran");
        }, 0, 10, TimeUnit.MILLISECONDS);
    }

}
