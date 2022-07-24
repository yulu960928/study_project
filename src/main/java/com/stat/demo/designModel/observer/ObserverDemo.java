package com.stat.demo.designModel.observer;

import java.util.Arrays;
import java.util.List;

public class ObserverDemo {
    public static void main(String[] args) {
        Subject subject = new Subject(Arrays.asList(new Aobserver(), new Bobserver()));
        subject.notifyAll("我结婚了，来参加我的婚礼！");
    }

}

class Subject {
    //主体，被观察者

    /**
     * 维护了一个观察者列表
     */
    List<Observers> oberverList;

    public Subject(List<Observers> oberverList) {
        this.oberverList = oberverList;
    }

    //通知方法
    public void notifyAll(String msg) {
        if (oberverList == null) {
            return;
        }
        for (Observers observers : oberverList) {
            observers.doObserve(msg);
        }
    }
}

//被观察者，doObserve是被通知时执行的方法
abstract class Observers {
    public void doObserve(String msg) {
        System.out.println(this.getClass().getSimpleName() + " received message: " + msg);
    }
}


class Aobserver extends Observers {

}

class Bobserver extends Observers {

}
