package com.ydgk.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-08 10:06
 */
public class TestConfiguration {

    public static void main(String[] args) {

//        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("");
        /*
        AnnotationConfigApplicationContext 表示从注解配置类中加载IOC容器对象
         */
        AnnotationConfigApplicationContext ioc =
                new AnnotationConfigApplicationContext(AnnotationConfig.class);

        Admin bean = (Admin) ioc.getBean("admin1");

        System.out.println(bean);
    }

}
