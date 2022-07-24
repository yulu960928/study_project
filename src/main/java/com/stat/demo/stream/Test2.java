package com.stat.demo.stream;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yulu
 */
public class Test2 {

    public static void main(String[] args) {
        List<Person> personList = Lists.newArrayList();
        personList.add(new Person("Tom", 8900, 10, "male", "New York"));
        personList.add(new Person("Jack", 7000, 20, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 50, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 22, "female", "New York"));
        personList.add(new Person("Owen", 9500, 15, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 89, "female", "New York"));

        //筛选集合中工资高于8000的人形成集合
        List<String> collect = personList.stream().filter(v -> v.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.println(collect);

        //获取员工工资最高的人
        Optional<Person> max = personList.stream().max(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int dis = o1.getSalary() - o2.getSalary();
                return dis < 0 ? -1 : (dis == 0 ? 0 : 1);
            }
        });
        personList.stream().max(Comparator.comparingInt()))
        System.out.println(max.get().getSalary());

    }


}

class Person {
    private String name;
    private int salary;
    private int age;
    private String sex;
    private String area;

    public Person(String name, int salary, int age, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
