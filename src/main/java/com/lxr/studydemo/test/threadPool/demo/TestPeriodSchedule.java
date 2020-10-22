package com.lxr.studydemo.test.threadPool.demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class TestPeriodSchedule {

	public static void main(String[] args) {
		AtomicInteger execNum = new AtomicInteger(0);
		AtomicInteger threadNum = new AtomicInteger(0);
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2, r -> {
			return new Thread(r, "[Thread----" + threadNum.incrementAndGet() + "]");
		});
		pool.scheduleAtFixedRate(() -> childSchedule(execNum), 1,60*1000, TimeUnit.MILLISECONDS);
	}

	private static void childSchedule(AtomicInteger execNum) {
		AtomicInteger threadNum = new AtomicInteger(0);
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2, r -> {
			return new Thread(r, "[ChildThread----" + threadNum.incrementAndGet() + "]");
		});
		//每天16:15:20开始执行,16:15:50结束, 每隔10秒执行一次
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime execTime = now.withHour(16).withMinute(15).withSecond(20).withNano(0);
		if (execTime.isBefore(now)) {
			execTime = execTime.plusDays(1);
		}
		long initialDelay = Duration.between(now, execTime).toMillis();
		long period = 10 * 1000;
		System.out.println(LocalDateTime.now() + "--------now:" + now);
		System.out.println(LocalDateTime.now() + "--------execTime:" + execTime);
		System.out.println(LocalDateTime.now() + "--------initialDelay:" + initialDelay);
		pool.scheduleAtFixedRate(() -> {
			LocalDateTime scheduleNow = LocalDateTime.now();
			LocalDateTime stopTime = now.withHour(16).withMinute(15).withSecond(50).withNano(0);
			if (scheduleNow.isAfter(stopTime)) {
				pool.shutdown();
			}
			System.out.println(LocalDateTime.now() + "我是" + Thread.currentThread().getName() + "========共执行了" + execNum.incrementAndGet() + "次");
		}, initialDelay, period, TimeUnit.MILLISECONDS);
	}
}
