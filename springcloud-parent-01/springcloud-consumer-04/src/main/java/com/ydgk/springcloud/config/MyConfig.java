package com.ydgk.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-16 10:31
 */
@Configuration
public class MyConfig {

    // 提供RestTemplate
    @Bean
    @LoadBalanced  // 表示使用Ribbon的负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
