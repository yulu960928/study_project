package com.stat.demo.JsonTest;

import com.alibaba.fastjson.JSON;

import java.util.Objects;

public class Test1 {
    public static void main(String[] args) {
        Person person = new Person("yulu", 23);
        Children children = JSON.parseObject(JSON.toJSONString(person), Children.class);
//        System.out.println(children.toString());
//        System.out.println(children.getAddress());
        System.out.println(Objects.equals(children.getAddress(),"aaa"));
    }
}
