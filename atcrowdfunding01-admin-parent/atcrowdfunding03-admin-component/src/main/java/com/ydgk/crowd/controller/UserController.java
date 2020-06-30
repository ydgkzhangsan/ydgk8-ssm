package com.ydgk.crowd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-30 16:02
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/to/user.html")
    public String toUser(){
        return "pages/user";
    }

}
