package com.stat.demo.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryOneTime;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yulu
 */
public class TicketClientTest {

    private int tickets = 100;

    private InterProcessMutex lock;

    public static void main(String[] args) {
        TicketClientTest client = new TicketClientTest();
        ExecutorService executorService = createExecutor();
        for (int i = 0; i < 10; i++) {
//            executorService.execute(()-> System.out.println(Thread.currentThread().getName()+":1111"));
            executorService.execute(client::run);
        }
    }

    public TicketClientTest() {
        RetryPolicy retryPolicy = new RetryOneTime(10);

        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181")
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        lock = new InterProcessMutex(client, "/lock");
    }

    /**
     * 通用线程池
     * 超过处理能力，抛错RejectedExecutionException给业务线程处理
     */
    public static ExecutorService createExecutor() {
        return new ThreadPoolExecutor(10,
                10,
                100,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100),
                new NamedThreadFactory("客户端"),
                new ThreadPoolExecutor.AbortPolicy());
    }


    private static class NamedThreadFactory implements ThreadFactory {

        private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        private final String mPrefix;

        private final boolean mDaemon;

        private final ThreadGroup mGroup;

        public NamedThreadFactory() {
            this("pool-" + POOL_SEQ.getAndIncrement(), false);
        }

        public NamedThreadFactory(String prefix) {
            this(prefix, false);
        }

        public NamedThreadFactory(String prefix, boolean daemon) {
            mPrefix = prefix + "-thread-";
            mDaemon = daemon;
            SecurityManager s = System.getSecurityManager();
            mGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
        }

        @Override
        public Thread newThread(Runnable runnable) {
            String name = mPrefix + mThreadNum.getAndIncrement();
            Thread ret = new Thread(mGroup, runnable, name, 0);
            ret.setDaemon(mDaemon);
            return ret;
        }

        public ThreadGroup getThreadGroup() {
            return mGroup;
        }
    }

    public void run() {
        while (true) {
            try {
                lock.acquire(3, TimeUnit.SECONDS);
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + tickets);
                    tickets--;
                } else {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
