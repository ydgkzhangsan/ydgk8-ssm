package com.ydgk.springboot.controller;

import com.ydgk.springboot.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-15 10:13
 */
@RestController
public class TestController {

    @Autowired
    private Person person;

    @RequestMapping("/testYml")
    public String contextLoads() {
        System.out.println(person);
        return "success";
    }

}
