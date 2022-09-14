package com.stat.demo.designModel.strategy;

public abstract class AbstractStrategy implements Strategy {

    public void printMethod(){
        System.out.println("使用第"+getType()+"个方法");
    }

}
