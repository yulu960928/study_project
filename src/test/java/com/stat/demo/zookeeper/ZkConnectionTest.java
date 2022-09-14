package com.stat.demo.zookeeper;

import com.stat.demo.BaseTest;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ZkConnectionTest {
    private CuratorFramework client;

    @Before
    public void testConnect() {
        /**
         * 参数：客户端地址:端口号
         *      会话超时时间
         *      连接超时时间
         *      重试策略
         */
        //方法1
        RetryPolicy retryPolicy = new RetryOneTime(10);
//        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", 3*1000, 3*1000, retryPolicy);
//        client.start();

        //方法2  指定namespace参数后，所以得节点操作都是在此路径下的
        client = CuratorFrameworkFactory.builder().connectString("localhost:2181")
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .namespace("yl").build();
        client.start();
    }

    /**
     * 创建节点：持久、临时、顺序的
     *  1.创建节点不带数据的
     *  2.创建节点带数据的
     *  3.创建其他类型的数据（临时，顺序等）
     *  4.创建多级节点
     * @throws Exception
     */
    @Test
    public void create1() throws Exception {
        //只创建节点，不存数据。此时该节点下默认存入数据为：客户端的ip地址
        String string = client.create().forPath("/app1");
        System.out.println(string);

    }

    @Test
    public void create2() throws Exception {
        //创建节点，并存数据
        String string2 = client.create().forPath("/app2","name".getBytes());
        System.out.println(string2);
    }

    @Test
    public void create3() throws Exception {
        //创建其他类型的节点:此处为临时节点
        String string3 = client.create().withMode(CreateMode.EPHEMERAL).forPath("/app3");
        System.out.println(string3);
    }

    @Test
    public void create4() throws Exception {
        //创建多级节点: 在父节点不存在时创建父节点
        String string4 = client.create().creatingParentsIfNeeded().forPath("/app3/p1");
        System.out.println(string4);
    }

    @Test
    public void get1() throws Exception {
        //查询节点数据
        byte[] string5 = client.getData().forPath("/app1");
        System.out.println(Arrays.toString(string5));
        System.out.println(new String(string5));
    }

    @Test
    public void get2() throws Exception {
        //查询子节点
        List<String> string6 = client.getChildren().forPath("/app3");
        List<String> string7 = client.getChildren().forPath("/");// app1,app2,app3,app4,此时"/"代表/yl节点,因为上面指定了
        System.out.println(string6);//p1
    }


    @Test
    public void get3() throws Exception {
        //查询节点的状态信息
        Stat status = new Stat();
        client.getData().storingStatIn(status).forPath("/app3");
        //指定一个空Stat,节点状态查询出来后会存储到stat中去，打印出即可
        System.out.println(status);
    }

    @Test
    public void set1() throws Exception {
        //修改节点数据
        client.setData().forPath("/app1", "hehe".getBytes());
    }

    @Test
    public void set2() throws Exception {
        //带版本号的修改节点数据。用于多客户端修改服务器数据的时候

        //查看服务端当前版本号
        Stat status = new Stat();
        client.getData().storingStatIn(status).forPath("/app1");
        int version = status.getVersion();
        //带着版本号去修改服务端数据: 如果在获取版本号和修改数据之间有其他客户端修改了数据的话，那此次version就不一致，则修改失败
        client.setData().withVersion(version).forPath("/app1", "hihi".getBytes());

    }

    @Test
    public void delete1() throws Exception {
        //删除单个节点
        client.delete().forPath("/app1");
    }

    @Test
    public void delete2() throws Exception {
        //删除带有子节点的节点
        client.delete().deletingChildrenIfNeeded().forPath("/app3");
    }

    @Test
    public void delete3() throws Exception {
        //删除带有子节点的节点
        client.delete().deletingChildrenIfNeeded().forPath("/app3");
    }

    @Test
    public void delete4() throws Exception {
        //必须成功的删除,防止网络抖动
        client.delete().guaranteed().forPath("/app2");
    }

    @Test
    public void delete5() throws Exception {
        //带有回调函数的删除
        client.delete().guaranteed().inBackground((client,event)->{
            System.out.println("我被删除了~");
            System.out.println(client);
            System.out.println(event);
        }).forPath("/app2");
    }
    

    @After
    public void close(){
        if(null!=client){
            client.close();
        }
    }
}
