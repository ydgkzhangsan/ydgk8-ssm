package com.ydgk.springcloudeureka05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// @EnableEurekaServer 表示启用Eureka的服务端
@EnableEurekaServer
@SpringBootApplication
public class ApplicationEurekaServerMain {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationEurekaServerMain.class, args);
	}

}
