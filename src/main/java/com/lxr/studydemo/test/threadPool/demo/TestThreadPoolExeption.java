package com.lxr.studydemo.test.threadPool.demo;


import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * @ClassName TestThreadPoolExeption
 * @Description 测试线程池异常捕获
 * @Author Areogel
 * @Date 2020/10/22 11:50
 * @Version 1.0
 */
public class TestThreadPoolExeption {

    public static void main(String[] args) throws InterruptedException {
        catchSubmit();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("=============================");
        catchAfterExecute();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("=============================");
        catchUncaughtException();
    }

    /**
     * future.get()
     * @description 只有调用submit方法时，异常会被捕获存储至FutureTask对象的outcome字段中，通过get可以获得该异常
     * @param
     * @return
     * @author Areogel
     * @date 2020/10/22 14:32
     */
    public static void catchSubmit() {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<?> future = pool.submit(() -> 1 / 0);
        try {
            Object o = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * AfterExecute
     * @description 重写AfterExecute方法，适用于execute
     * @param
     * @return
     * @author Areogel
     * @date 2020/10/22 14:39
     */
    public static void catchAfterExecute() {
        ThreadFactory factory = new BasicThreadFactory.Builder()
                .namingPattern("task-scanner-executor-%d")
                .build();
        ExecutorService pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                30L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), factory) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if(null != t){
                    t.printStackTrace();
                }
            }
        };
        pool.execute(() -> System.out.println(1 / 0));
    }

    /**
     * UncaughtException
     * @description 当一个线程因为未捕获的异常而退出时，JVM会把这个事件报告给应用提供的UncaughtExceptionHandler异常处理器，只适用于execute
     * @param
     * @return 
     * @author Areogel
     * @date 2020/10/22 14:44
     */
    public static void catchUncaughtException() {
        ThreadFactory factory = new BasicThreadFactory.Builder()
                .namingPattern("task-scanner-executor-%d")
                .uncaughtExceptionHandler((t, e) -> {
                    if (e != null) {
                        e.printStackTrace();
                    }
                })
                .build();
        Executors.newCachedThreadPool(factory).execute(() -> System.out.println(1 / 0));
    }
}
