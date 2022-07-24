package com.stat.demo.designModel.observer;

import java.util.Arrays;
import java.util.List;

/**
 * A的变动需要通知其他人的时候，观察者模式
 */
public class ObserveMain {
    public static void main(String[] args) {
        Yulu yulu = new Yulu(Arrays.asList(new A(), new B()));
        yulu.notifyAll("来参加我婚礼");
    }
}

class Yulu {
    List<Observer> observerList;

    public Yulu(List<Observer> observerList) {
        this.observerList = observerList;
    }

    public void notifyAll(String msg) {
        if (observerList == null) {
            return;
        }
        for (Observer observer : observerList) {
            observer.notify(msg);
        }
    }
}

abstract class Observer {

    public void notify(String msg) {
        System.out.println(this.getClass().getSimpleName() + "received msg=" + msg);
    }
}

class A extends Observer {

}

class B extends Observer {

}