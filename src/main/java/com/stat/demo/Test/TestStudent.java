package com.stat.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestStudent {

    public static void main(String[] args) {
        Student student = new Student("yulu",24,new Address("CN","JS"));
        String string = JSON.toJSONString(student);
//        Student<Address> addressStudent = JSON.parseObject(string, new TypeReference<Student<Address>>() {});
//        System.out.println(addressStudent.getAddress());

        String[] showNo = {"123","345","456"," "};
        List<String> collect = Arrays.stream(showNo).filter(StringUtils::isNoneBlank).collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }
    }

}
