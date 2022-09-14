//package com.stat.demo;
//import org.apache.commons.lang3.StringUtils;
//import org.testng.annotations.Test;
//
//public class TryTest {
//
//    public void test1(){
//        int i = test3(10);
//        System.out.println(i);
//
//    }
//
//    public int test2(){
//        try{
//            int i=20/5;
//            return 1;
//        }catch (Exception e){
//            e.printStackTrace();
//            return 2;
//        }finally {
//            return 0;
//        }
//    }
//
//    public int test3(int number){
//        try {
//            ++number;
//            System.out.println("try was executed");
//            return number;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("catch was executed");
//            number += 10;
//            return number;
//        } finally{
//            System.out.println("finally was executed");
//
//        }
//
//    }
//
//    @Test
//    public void test4(){
//        long  value = 191980649542150036L;
//        Long valueB = new Long(191980649542150036L);
//        System.out.println(value==valueB);
//    }
//
//    @Test
//    public void test5(){
//        String str = "时间: 2022-08-10 22:23  ";
//        String s = StringUtils.substringAfter(str, "时间:").trim();
//        System.out.println(s);
//    }
//}
//
////number += 100;
////System.out.println("finally was executed" + "number = " + number);
////return number;