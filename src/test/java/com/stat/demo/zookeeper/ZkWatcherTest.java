package com.stat.demo.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.RetryOneTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZkWatcherTest {

    private CuratorFramework client;

    @Before
    public void testConnect() {
        /**
         * 参数：客户端地址:端口号
         *      会话超时时间
         *      连接超时时间
         *      重试策略
         */
        //方法2  指定namespace参数后，所以得节点操作都是在此路径下的
        RetryPolicy retryPolicy = new RetryOneTime(10);
        client = CuratorFrameworkFactory.builder().connectString("localhost:2181")
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .namespace("yl").build();
        client.start();
    }

    /**
     * 注册监听器：
     * zk提供了3中watcher:NodeCache; PathChildrenCache; TreeCache
     */
    @Test
    public void test1NodeCache() throws Exception {
        //监听某一个节点
        //1.创建NodeCache对象
        NodeCache nodeCache = new NodeCache(client, "/app1");
        //2.注册监听
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("节点变化了");
                //获取修改后的节点数据
                byte[] data = nodeCache.getCurrentData().getData();
                System.out.println("变化后的节点数据为：" + new String(data));
            }
        });
        //3.开启监听。参数如果设置为true,则开启监听时，加载缓冲数据
        nodeCache.start(true);

        while (true) {
            //保持本次连接不断开
        }
    }

    @Test
    public void test2PathChildrenCache() throws Exception {
        //监听某个节点的所有子节点们
        //1.创建PathChildrenCache对象
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/app1", true);
        //2.注册监听
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("子节点变化了~");
                System.out.println(event);

                //获取变化的类型
                PathChildrenCacheEvent.Type type = event.getType();
                //需求：只有类型为update时，才操作，并获取更新后的数据
                if (PathChildrenCacheEvent.Type.CHILD_UPDATED == type) {
                    System.out.println("子节点数据更新了");
                    //获取新数据
                    byte[] data = event.getData().getData();
                    System.out.println("新数据为：" + new String(data));
                }

            }
        });
        //3.开启监听
        pathChildrenCache.start();

        while (true) {
            //保持本次连接不断开
        }
    }

    @Test
    public void test3TreeCache() throws Exception {
        //监听某颗树上的节点变化，包括自己和儿子们
        //1.创建TreeCache对象
        TreeCache treeCache = new TreeCache(client,"/app1");
        //2.注册监听
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("节点变化了");
                System.out.println(event);
            }
        });
        //3.开启监听
        treeCache.start();
        while (true){

        }

    }


    @After
    public void close() {
        if (null == client) {
            client.close();
        }
    }

}
