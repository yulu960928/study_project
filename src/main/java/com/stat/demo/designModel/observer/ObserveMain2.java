package com.stat.demo.designModel.observer;

import java.util.Arrays;
import java.util.List;

/**
 * A的变动需要通知其他人的时候，观察者模式
 */
public class ObserveMain2 {
    public static void main(String[] args) {
        Yulu2 yulu = new Yulu2(Arrays.asList(new C(), new D()));
        yulu.notifyAll("来参加我婚礼");
    }
}

class Yulu2 {

    List<Callable> callableList;

    public Yulu2(List<Callable> callableList) {
        this.callableList = callableList;
    }

    public void notifyAll(String msg) {
        if (callableList == null) {
            return;
        }
        for (Callable callable : callableList) {
            callable.call(msg);
        }
    }
}

/**
 * 通知能力
 */
interface Callable {

    void call(String msg);
}

class C implements Callable {

    @Override
    public void call(String msg) {
        System.out.println(this.getClass().getSimpleName() + " be called msg=" + msg);
    }
}

class D implements Callable {

    @Override
    public void call(String msg) {
        System.out.println(this.getClass().getSimpleName() + " be called msg=" + msg);
    }
}