package com.ydgk.springcloud.romote;

import com.ydgk.springcloud.entities.Employee;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-16 15:00
 */
// 使用Feign将微服务和接口绑定
// 当调用这个接口中的方法时，实际上是调用这个指定的微服务的远程方法。
@FeignClient(value="provider-01")
public interface Provider01Remote {

    /*
    在这个接口中的方法必须和远程调用的方法保持一致：
        1、方法名一致
        2、返回值一致
        3、参数一致  (传入的参数，不包含原生的ServletAPI可以不一致)
        4、参数中包含的注解一致
        5、参数名保持一致
     */
    @RequestMapping("/provider/employee/get/remote")
    Employee getEmployee();

}
