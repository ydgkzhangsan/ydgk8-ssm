package com.ydgk.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-14 14:48
 */
/*
相当于 @Controller 注解和 @ResponseBody 注解修饰
@ResponseBody 用来修饰类表示该类中所有方法都是返回json串
 */
//@Configuration
@SpringBootApplication
// @EnableAutoConfiguration   // 此注解只能扫描到当前类，不能扫描其他类及子包下面的类
public class SpringBootHelloWorld {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloWorld.class, args);
    }

}
