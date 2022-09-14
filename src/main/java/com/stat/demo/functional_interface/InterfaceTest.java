package com.stat.demo.functional_interface;

/**
 * @author yulu
 */
public interface InterfaceTest {

    //接口中的属性默认使用public static final修饰
    String nickname="梦幻水晶";
    public static final int  salary=100;


    //静态方法： jdk1.8之后，接口中才可以有静态方法，1.8之前没有
    public static void method2(){}

    //抽象方法: public abstract可以省略。无方法体
    public abstract void method3();
    //抽象方法可以有参数
    void method4(String msg);

    //默认方法
    default void method5(){
        System.out.println("这是默认方法5！");
    }

    public static void main(String[] args) {
        //使用匿名对象的方法来调用。
        //匿名对象就是实例化一个对象，没有具体的引用，此匿名对象只能使用一次，不能重复使用。
        //创建方法：new 接口名() {重写并实现接口中的所有抽象方法}
        new InterfaceTest() {
            @Override
            public void method3() {
                System.out.println("这是方法3");
            }

            @Override
            public void method4(String msg) {
                System.out.println("这是方法4");
            }

        }.method5();
    }
}
