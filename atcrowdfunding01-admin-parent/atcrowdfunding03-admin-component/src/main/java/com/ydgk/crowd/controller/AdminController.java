package com.ydgk.crowd.controller;

import com.ydgk.crowd.entity.Admin;
import com.ydgk.crowd.service.api.AdminService;
import com.ydgk.ssm.constant.CrowdConstant;
import com.ydgk.ssm.exceptions.LoginFailedException;
import com.ydgk.ssm.util.CrowdUtil;
import com.ydgk.ssm.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.util.Password;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @RequestMapping("/do/doLogin.html")
    public String doLogin(String loginAcct, String userPswd, HttpSession session){
        // 1、接受前台表单传入的数据

        // 2、根据loginAcct 查询数据库拿到 Admin对象
        Admin admin = adminService.getAdminByLoginAcctAndPaswd(loginAcct, userPswd);

        // 3、将获取的admin存入Session域中
        session.setAttribute(CrowdConstant.ATTR_NAME_ADMIN_INFO,admin);
        return "redirect:/admin/to/main.html";
    }

    // 退出功能
    @RequestMapping("/do/doLogout.html")
    public String doLogout(HttpSession session){
        // 将 Session 中的数据清空
        session.invalidate();
        return "redirect:/admin/to/admin-login.html";
    }




    @RequestMapping("/test.html")
    public String test(){
        return "target";
    }

    @RequestMapping("/aaa.html")
    public String aaa(HttpServletRequest request){
        boolean b = CrowdUtil.judgeRequestType(request);

//        int a = 10 / 0;
        for(int i = 0 ; i < 10 ; i++){
            if(i == 5){
                throw new LoginFailedException("登录失败了，请确认账号或密码是否正确！");
            }
        }
        System.out.println("b = " + b);
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
    public ResultEntity<String> testJson2(@RequestBody List<Integer> ids,
                                          HttpServletRequest request){
        boolean b = CrowdUtil.judgeRequestType(request);
        System.out.println("b = " + b);
        for(Integer id : ids){
            System.out.println("id = " + id);
        }

        for(int i = 0 ; i < 10 ; i++){
            if(i == 5){
                throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
            }
        }
        //ResultEntity<String5> entity = new ResultEntity<>("success",null,"success");
        return ResultEntity.successWithData("SUCCESS");
    }
    // 需要规范每一次 ajax 请求返回的数据的格式。      前后端绝对分离   api 接口的形式

}
