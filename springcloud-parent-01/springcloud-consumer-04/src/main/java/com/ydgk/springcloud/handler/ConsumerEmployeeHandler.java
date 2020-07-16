package com.ydgk.springcloud.handler;

import com.ydgk.springcloud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-16 10:33
 */
@RestController  // 不一定非得需要返回json数据，这里是为了方便能直接看到json数据就使用RestController
public class ConsumerEmployeeHandler {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/get/employee")
    public Employee getEmployee(){

        //1、 声明远程服务的主机地址
//        String host = "http://localhost:1000";
        String host = "http://provider-01";
        //2、 声明需要访问的请求地址
        String url = "/provider/employee/get/remote";
        //3、 通过RestTemplate调用远程接口，拿到数据
        Employee employee = restTemplate.getForObject(host + url, Employee.class);
        return employee;
    }

}
