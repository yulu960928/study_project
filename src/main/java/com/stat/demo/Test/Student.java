package com.stat.demo.Test;

import com.alibaba.fastjson.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Student<T> implements Serializable {

    private static final long serialVersionUID = -142595744498660906L;

    private String name;
    private int age;
    private T address;
}
