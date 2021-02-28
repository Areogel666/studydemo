package com.lxr.studydemo.test.multiThread.demo;

import com.lxr.studydemo.test.multiThread.entity.Empty;
import org.openjdk.jol.info.ClassLayout;

/**
 * synchronized锁
 * 对象头锁标识示例
 */
public class SyncObjHeadDemo {

    /**
     * -XX:BiasedLockingStartupDelay=0 偏向锁延时=0
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Empty empty = new Empty();
//        ClassLayout layout = ClassLayout.parseInstance(empty);
//        System.out.println(layout.toPrintable());
        System.out.println(ClassLayout.parseInstance(empty).toPrintable());
        empty.hashCode();
        Thread t1 = new Thread(() -> printObjLayout(empty));
        Thread t2 = new Thread(() -> printObjLayout(empty));

        t1.start();
        t1.join();
        t2.start();
        t2.join();
//        System.out.println(layout.toPrintable());
        System.out.println(ClassLayout.parseInstance(empty).toPrintable());
    }


    public static void printObjLayout(Object obj) {
        synchronized (obj) {
            System.out.println("线程" + Thread.currentThread().getName() + ":" + ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}
