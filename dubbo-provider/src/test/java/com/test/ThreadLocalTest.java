package com.test;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

@Slf4j
public class ThreadLocalTest {

    public static final ThreadLocal<Integer> tl = new ThreadLocal<>();
    public static final ThreadLocal<Integer> itl = new InheritableThreadLocal<>();
    public static final ThreadLocal<Integer> ttl = new TransmittableThreadLocal<>();

    private static ExecutorService parentExecutors = new ThreadPoolExecutor(10,10,0, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));

    private static ExecutorService childExecutors = Executors.newFixedThreadPool(5);
//    private static ExecutorService childExecutors = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(5)); // 使用ttl线程池，该框架的使用，请查阅官方文档。

    @Test
    public void testTL() throws IOException {
        tl.set(0);
        new Thread(() -> log.info(tl.get()+""), "thread-1").start();
        new Thread(() -> {
            tl.set(2);
            log.info(tl.get().toString());
        }, "thread-2").start();
        System.in.read();
    }

    @Test
    public void testItl() {
        for (int i = 0; i < 10; i++) {
            parentExecutors.submit(new ControlThread(i));
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        childExecutors.shutdown();
        parentExecutors.shutdown();
    }

    static class ControlThread implements Runnable {
        private int i;

        public ControlThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + ":" + i);
            tl.set(i);
            childExecutors.submit(new BusinessTask(Thread.currentThread().getName(), i));
        }
    }

    static class BusinessTask implements Runnable {
        private String parentThreadName;
        private int expectValue;

        public BusinessTask(String parentThreadName, int expectValue) {
            this.parentThreadName = parentThreadName;
            this.expectValue = expectValue;
        }

        @Override
        public void run() {
            log.info("父线程:" + parentThreadName + ",期望值=" + expectValue + ",实际值=" + tl.get());
        }
    }
}
