package com.lxr.studydemo.test.threadPool.demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class TestSchedule {

	public static void main(String[] args) {
		AtomicInteger threadNum = new AtomicInteger(0);
		AtomicInteger execNum = new AtomicInteger(0);
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2, r -> new Thread(r, "[Thread----" + threadNum.incrementAndGet() + "]"));
		//每天上午11点开始执行, 每隔10秒执行一次
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime execTime = now.withHour(11).withMinute(26).withSecond(50).withNano(0);
		if (execTime.isBefore(now)) {
			execTime = execTime.plusDays(1);
		}
		long initialDelay = Duration.between(now, execTime).toMillis();
		long period = 10 * 1000;
		System.out.println(LocalDateTime.now() + "--------now:" + now);
		System.out.println(LocalDateTime.now() + "--------execTime:" + execTime);
		System.out.println(LocalDateTime.now() + "--------initialDelay:" + initialDelay);
		pool.scheduleAtFixedRate(() -> {
			System.out.println(LocalDateTime.now() + "我是" + Thread.currentThread().getName() + "========共执行了" + execNum.incrementAndGet() + "次");
		}, initialDelay, period, TimeUnit.MILLISECONDS);
	}
}
