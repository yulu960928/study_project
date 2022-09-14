package com.stat.demo.designModel.strategy;

public class SubStrategy extends AbstractStrategy {
    @Override
    public int doOperation(int a, int b) {
        return a-b;
    }

    @Override
    public int getType() {
        return 2;
    }
}
