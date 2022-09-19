package com.stat.demo.zookeeper;

public class Test12306 {
    public static void main(String[] args) {
        TicketSimulation12306 ticketSimulation=new TicketSimulation12306();
        Thread t1 = new Thread(ticketSimulation,"携程");
        Thread t2 = new Thread(ticketSimulation,"飞猪");

        t1.start();
        t2.start();
    }
}
