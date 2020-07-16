package com.ydgk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-16 10:20
 */
// 主启动类
//@EnableEurekaClient // 表示启用 Eureka 客户端
//@EnableDiscoveryClient
@SpringBootApplication
public class ApplicationProviderMain {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationProviderMain.class,args);
    }

}
