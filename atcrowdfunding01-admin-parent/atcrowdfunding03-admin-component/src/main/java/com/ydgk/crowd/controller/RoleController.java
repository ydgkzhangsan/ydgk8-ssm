package com.ydgk.crowd.controller;

import com.github.pagehelper.PageInfo;
import com.ydgk.crowd.entity.Role;
import com.ydgk.crowd.service.api.RoleService;
import com.ydgk.ssm.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    /*
    使用 @PreAuthorize 注解,为对应的方法设置权限
     */
    @PreAuthorize("hasRole('部长')")
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

    @ResponseBody
    @RequestMapping("/input.json")
    public ResultEntity input(Role role){
        try{
            roleService.saveRole(role);
            return ResultEntity.successWithoutData();
        } catch (Exception  e){
            e.printStackTrace();
            return ResultEntity.failed("插入角色信息失败，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("edit.json")
    public ResultEntity edit(Role role){
        try{
            roleService.updateRole(role);
            return ResultEntity.successWithoutData();
        }catch(Exception e){
            e.printStackTrace();
            return ResultEntity.failed("修改角色信息失败，请联系管理员");
        }
    }

    @ResponseBody
    @RequestMapping("delete.json")
    public ResultEntity delete(@RequestBody List<Integer> roleIds){
        try{
            roleService.deleteRoleByIds(roleIds);
            return ResultEntity.successWithoutData();
        } catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed("删除角色信息失败，请联系管理员！");
        }
    }

}
