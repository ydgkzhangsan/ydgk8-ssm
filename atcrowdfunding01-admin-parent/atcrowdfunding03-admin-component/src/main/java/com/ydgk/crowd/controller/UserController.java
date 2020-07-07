package com.ydgk.crowd.controller;

import com.github.pagehelper.PageInfo;
import com.ydgk.crowd.entity.Admin;
import com.ydgk.crowd.service.api.AdminService;
import com.ydgk.ssm.constant.CrowdConstant;
import com.ydgk.ssm.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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

    @RequestMapping("do/input.html")
    public String doInput(@Valid Admin admin, BindingResult result){
        if(result.hasErrors()){
            return "pages/admin-add";
        }
        adminService.saveAdmin(admin);
        return "redirect:/user/to/user.html";
    }

    @RequestMapping("to/edit/{adminId}/{pageNum}/{keyWord}.html")
    public String toEdit(
            @PathVariable("adminId")Integer adminId,
            @PathVariable("pageNum")Integer pageNum,
            @PathVariable("keyWord")String keyWord,
            Map map
    ){
        //根据adminId获取Admin信息，并将信息存入到模型中
        Admin admin = adminService.getAdminById(adminId);
        map.put(CrowdConstant.ATTR_NAME_ADMIN_INFO,admin);
        //?pageNum="+pageNum+"&keyWord="+keyWord
        map.put("pageNum",pageNum);
        map.put("keyWord",keyWord);
        return "pages/admin-edit";
    }

    @ModelAttribute
    public void getAdminById(@RequestParam(value = "id", required = false)Integer adminId,
                             Map map){
        if(adminId != null){
            // 表示是一个新增操作
            Admin admin = adminService.getAdminById(adminId);
            map.put(CrowdConstant.ATTR_NAME_ADMIN_INFO,admin);
        }
    }

    @RequestMapping("do/edit.html")
    public String doEdit(Admin admin,String originalLoginAcct,String pageNum,String keyWord){
        //执行更新操作
        adminService.editAdmin(admin,originalLoginAcct);
        return "redirect:/user/to/user.html?pageNum="+pageNum+"&keyWord="+keyWord;
    }

    @ResponseBody
    @RequestMapping("test.json")
    public String test(Integer id){
        System.out.println(id);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
