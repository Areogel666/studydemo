package com.lxr.studydemo.test.multiThread.entity;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CASLock {

    //锁状态
    private volatile int status = 0;

    //对象偏移量
    private static long offset;

    //获取unsafe类
    private static final Unsafe unsafe = getUnsafe();

    static {
        try {
            offset = unsafe.objectFieldOffset
                    (CASLock.class.getDeclaredField("status"));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Error(ex);
        }
    }

    /**
     * 操作加锁
     */
    public void lock() {
        while (!compareAndSet(0, 1)) {

        }
    }

    /**
     * 解锁
     */
    public void unlock() {
        status = 0;
    }

    /**
     * Cas操作
     *
     * @param expected
     * @param update
     * @return
     */
    private boolean compareAndSet(int expected, int update) {
        return unsafe.compareAndSwapInt(this, offset, expected, update);
    }

    /**
     * 获得unsafe对象
     *
     * @return
     */
    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
