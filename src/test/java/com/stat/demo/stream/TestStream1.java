package com.stat.demo.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestStream1 {

    @Test
    public void testLists() {
        List<Long> lists = Lists.newArrayList(1111L, 2222L, 3333L);
        Map<Long, Boolean> collect = lists.stream().collect(Collectors.toMap(v -> v, v -> {
            String s = v + "aaa";
            return s.equals("1111aaa");
        }, (a, b) -> b, LinkedHashMap::new));
        System.err.println(JSON.toJSONString(collect));
    }

    @Test
    public void testMap() {
        Map<String, String> person = Maps.newHashMap();
        person.put("1", "jack");
        person.put("2", "rose");

        String name = Optional.ofNullable(person.get("3")).orElse("yl");
        System.out.println(name);
    }

    @Test
    public void testMap2() {
        Map<Integer, String> person = Maps.newHashMap();
        person.put(1, "jack");
        person.put(2, "rose");
        person.put(3, "tim");

        List<String> collect = person.entrySet().stream().filter(v -> v.getKey() >= 2).map(Map.Entry::getValue).collect(Collectors.toList());
        System.err.println(JSON.toJSONString(collect));
    }


}


