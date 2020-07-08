package com.ydgk.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-08 10:00
 */
/*
@Configuration: 注解修饰的类是一个配置类
    配置类： 相当于以前的 applicationContext.xml   dispatcherServlet-servlet.xml
            Spring的配置文件， Spring配置文件中可以完成的工作，在配置类中都可以完成

    xml中配置Admin对应的bean
        <bean id="admin" class="com.ydgk.spring.Admin"></bean>
 */
@Configuration
public class AnnotationConfig {

    /*
    <bean id="admin" class="com.ydgk.spring.Admin" scope=""></bean>
     @Bean 注解相当于 bean 节点
        方法名就是放入IOC容器中bean的id名
        返回值即放入IOC容器中bean的类型
     */
    //@Scope()
    @Bean
    public Admin admin1(){
        return new Admin();
    }

}
