package com.ydgk.crowd.controller;

import com.github.pagehelper.PageInfo;
import com.ydgk.crowd.entity.Admin;
import com.ydgk.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-30 16:02
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/to/user.html")
    public String toUser(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize ,
            @RequestParam(value = "keyWord", required = false, defaultValue = "") String keyWord,
            ModelMap modelMap
    ){
        // 查询数据库，拿到数据
        PageInfo<Admin> adminPageInfo = adminService.getAdminsByKeyWord(pageNum,pageSize,keyWord);
        modelMap.addAttribute("adminPageInfo",adminPageInfo);
        return "pages/user";
    }

    @RequestMapping("do/delete/{adminId}/{pageNum}/{keyWord}.html")
    public String doDelete(
            @PathVariable("adminId")Integer adminId,
            @PathVariable("pageNum")Integer pageNum,
            @PathVariable("keyWord")String keyWord
    ){
        adminService.removeAdminById(adminId);
        return "redirect:/user/to/user.html?pageNum="+pageNum+"&keyWord="+keyWord;
    }



}
