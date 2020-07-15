package com.ydgk.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-15 14:31
 */
@Controller
public class DispatcherController {

    ///to/success/page
    @GetMapping("/to/success/page")
    public String toSuccess(Map map){
        map.put("home","我爱我家1！");
        return "success";
    }

}
