package com.ydgk.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-14 16:30
 */
@RestController
public class DispatcherController {

    @GetMapping("/test")
    public String testMethod(){
        return "1234~~~";
    }

}
