package com.ydgk.crowd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-29 15:02
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @RequestMapping("/test.html")
    public String test(){
        return "target";
    }

}
