package com.ydgk.crowd.controller;

import com.ydgk.crowd.entity.Admin;
import com.ydgk.crowd.entity.ResultEntity;
import com.ydgk.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-29 15:02
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/test.html")
    public String test(){
        return "target";
    }

    @RequestMapping("/aaa.html")
    public String aaa(){
        return "target";
    }

    @ResponseBody
    @RequestMapping("/testAjax.json")
    public List<Admin> getAdminList(){
        return adminService.getAdminList();
    }

    @ResponseBody
    @RequestMapping("/testJson1.json")
    public String testJson1(@RequestParam("empIdList[]") List<Integer> ids){
        // 如何接受ajax请求发送过来的数据
        for(Integer id : ids){
            System.out.println("id = " + id);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/testJson2.json")
    public ResultEntity<String> testJson2(@RequestBody List<Integer> ids){
        for(Integer id : ids){
            System.out.println("id = " + id);
        }
        //ResultEntity<String> entity = new ResultEntity<>("success",null,"success");
        return ResultEntity.successWithData("SUCCESS");
    }

    // 需要规范每一次 ajax 请求返回的数据的格式。      前后端绝对分离   api 接口的形式

}
