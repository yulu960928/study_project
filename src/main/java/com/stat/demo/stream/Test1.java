package com.stat.demo.stream;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yulu
 */
public class Test1 {

    public static void main(String[] args) {
        /**
         * 流的创建
         * 1    list.stream()
         *      list.parallelStream()
         * 2    Arrays.stream(数组)
         * 3    Stream的静态方法：Stream.of(),Stream.iterate(),Stream.generate()
         */
        //List<String> list1 = Lists.newArrayList("a","b","c");
        //Stream<String> stream = list1.stream();
        //Stream<String> parallelStream = list1.parallelStream();

        //int[] array = {1,2,3};
        //Arrays.stream(array);

        /**
         * 遍历/匹配 foreach,find,match
         */
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        //list.stream().filter(x->x>6).forEach(System.out::println);

        //匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        System.out.println(findFirst.get());

        //是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x > 6);
        System.out.println(anyMatch);

        /**
         * 筛选 filter
         */
        list.stream().filter(x->x>7).forEach(System.out::println);

        /**
         * 聚合 max,min,count
         */
        //获取list中的最大值,2种方法  根据指定的Comparator判断最大值
        Optional<Integer> max = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(max.get());

        Optional<Integer> max1 = list.stream().max(Integer::compareTo);


    }
}
