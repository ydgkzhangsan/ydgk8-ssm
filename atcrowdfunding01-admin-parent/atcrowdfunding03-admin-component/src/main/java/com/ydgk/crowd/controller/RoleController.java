package com.ydgk.crowd.controller;

import com.github.pagehelper.PageInfo;
import com.ydgk.crowd.entity.Role;
import com.ydgk.crowd.service.api.RoleService;
import com.ydgk.ssm.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-03 9:44
 */
@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/get/role-list.json")
    public ResultEntity<PageInfo<Role>> getRoleInfo(Integer pageNum,
                                                    Integer pageSize,
                                                    String keyWord){
        try {
            PageInfo<Role> rolePageInfo = roleService.getRoleByKeyWord(pageNum, pageSize, keyWord);
            return ResultEntity.successWithData(rolePageInfo);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed("获取数据失败!");
        }
    }
}
