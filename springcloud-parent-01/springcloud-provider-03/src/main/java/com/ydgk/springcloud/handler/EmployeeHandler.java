package com.ydgk.springcloud.handler;

import com.ydgk.springcloud.entities.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-16 10:23
 */
@RestController // @ResponseBody 和 @Controller   生产者是由消费者调用   Http 形式的调用，所以传输数据只能使用json不能使用页面
public class EmployeeHandler {

    @RequestMapping("/provider/employee/get/remote")
    public Employee getEmployee(HttpServletRequest request){

        // 获取当前服务器使用的端口号
        int serverPort = request.getServerPort();

        // 将端口号信息添加到用户名后，便于等下实验查看
        return new Employee(555, "tom555"+serverPort, 555.55);

    }

}
