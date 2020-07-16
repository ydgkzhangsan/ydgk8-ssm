package com.ydgk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-16 15:09
 */
@EnableFeignClients // 表示启用 Feign客户端功能
@SpringBootApplication
public class ApplicationFeignConsumerMain {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationFeignConsumerMain.class,args);
    }

}
