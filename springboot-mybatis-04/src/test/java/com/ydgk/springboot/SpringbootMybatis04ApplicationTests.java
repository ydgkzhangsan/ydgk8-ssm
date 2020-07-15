package com.ydgk.springboot;

import com.ydgk.springboot.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMybatis04ApplicationTests {

	@Autowired
	private CustomerMapper customerMapper;

	@Test
	void contextLoads() {
		System.out.println(customerMapper.getAll());
	}

}
