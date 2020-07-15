package com.ydgk.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.ydgk.springboot.mapper"}) // 指定扫描Mapper接口实现类对象加入IOC容器
@SpringBootApplication
public class SpringbootMybatis04Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatis04Application.class, args);
	}

}