package com.ydgk.springcloud.handler;

import com.ydgk.springcloud.entities.Employee;
import com.ydgk.springcloud.romote.Provider01Remote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-16 15:13
 */
@RestController
public class EmployeeHandler {

    @Autowired
    private Provider01Remote remote;

    @RequestMapping("/get/employee/by/feign")
    public Employee getEmployee(){
        return remote.getEmployee();
    }

}
