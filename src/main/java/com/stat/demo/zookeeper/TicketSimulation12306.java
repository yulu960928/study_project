package com.stat.demo.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryOneTime;

import java.util.concurrent.TimeUnit;

public class TicketSimulation12306 implements Runnable {

    private int tickets = 10;

    private InterProcessMutex lock;

    public TicketSimulation12306() {
        RetryPolicy retryPolicy = new RetryOneTime(10);

        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181")
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        lock = new InterProcessMutex(client, "/lock");
    }

    @Override
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
