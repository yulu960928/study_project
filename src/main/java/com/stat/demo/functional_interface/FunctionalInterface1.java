package com.stat.demo.functional_interface;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@FunctionalInterface
public interface FunctionalInterface1 {
    void method1();
}

@FunctionalInterface
interface FunctionalInterface2{
    void method2(int a);
}

interface FunctionalInterface3{
    int method3(int a,int b);
}

class Test{
    public static void main(String[] args) {
        FunctionalInterface1 f1 = () -> {
            System.out.println("这是方法1");
        };
        //f1.method1();

        FunctionalInterface2 f2 = (a) -> {
            System.out.println(a);
        };
        //f2.method2(100);

        FunctionalInterface3 f3 = (a,b)->{
            return a+b;
        };
        //System.out.println(f3.method3(1,3));

    }
}
