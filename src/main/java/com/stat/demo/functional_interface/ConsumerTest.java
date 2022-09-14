package com.stat.demo.functional_interface;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ConsumerTest {

    public static void main(String[] args) {
        /*
          Consumer<T>消费型接口，方法void accept(T t).
         */
        Consumer<Integer> consumer = (a) -> {
            System.out.println("Consumer Interface,the param is " + a);
        };
        consumer.accept(4);

        /*
            Supplier<T>供给型接口 方法 T get()
         */
        Supplier<String> string = () -> "hello Supplier";
        string.get();

        /*
            Function<T,R>函数型接口：方法 R apply(T t)
         */
        Function<Integer, String> function = (a) -> String.valueOf(a);
        System.out.println(function.apply(5555));

        /*
            Predicate<T>断定型接口，方法 boolean test(T t)
         */
        Predicate<String> predicate = (a) -> a.length() > 5;
        System.out.println(predicate.test("568快"));

    }

}


