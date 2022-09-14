package com.stat.demo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiaofuqiang
 * @date 2021.11.30
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.stat.demo.*")
//@EnableApolloConfig
public class ApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
        System.out.println("##########################################################################");
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + "demo start!");
        System.out.println("##########################################################################");
    }
}
