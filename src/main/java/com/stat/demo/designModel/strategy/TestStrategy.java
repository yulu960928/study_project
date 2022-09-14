package com.stat.demo.designModel.strategy;

public class TestStrategy {

    public static void main(String[] args) {
        Context context = new Context(new SubStrategy());
        int result = context.executeStrategy(3, 4);
        System.out.println(result);
    }

}
