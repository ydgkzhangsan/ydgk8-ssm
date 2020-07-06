package com.ydgk.crowd.controller;

import com.ydgk.crowd.entity.Menu;
import com.ydgk.crowd.service.api.MenuService;
import com.ydgk.ssm.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-06 10:38
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("/edit.json")
    public ResultEntity edit(Menu menu){
        try {
            menuService.editMenu(menu);
            return ResultEntity.successWithoutData();
        } catch ( Exception e ){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/delete.json")
    public ResultEntity delete(Integer id){
        try {
            menuService.deleteMenuById(id);
            return ResultEntity.successWithoutData();
        } catch ( Exception e ){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/save.json")
    public ResultEntity save(Menu menu){
        try {
            menuService.saveMenu(menu);
            return ResultEntity.successWithoutData();
        } catch ( Exception e ){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("getAll.json")
    public ResultEntity getAll(){
        try{
            // 获取所有数据
            List<Menu> menus = menuService.getAll();
            // 遍历所有的菜单数据
            // 将菜单数据保存在 Map 中
            HashMap<Integer, Menu> menuMap = new HashMap<>();
            for(Menu menu : menus){
                menuMap.put(menu.getId(),menu);
            }

            Menu root = null;// 父节点
            // 构建 菜单之间的关系
            for(Menu menu : menus){
                if(menu.getpId() == null){
                    root = menu;
                    continue;
                }
                // 对于非根节点
                Menu menuParent = menuMap.get(menu.getpId());
                menuParent.getChildren().add(menu);
            }
            return ResultEntity.successWithData(root);
        } catch(Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }
    public ResultEntity getAll1(){

        // 获取所有数据
        List<Menu> menus = menuService.getAll();

        // 组建父子关系
        // 1、 遍历所有的菜单
        Menu root = null; // 根节点信息保存在 root 中
        for(Menu menu : menus){
            // 2、 查找菜单的根节点  pid=null
            if(menu.getpId() == null){
                root = menu;
                continue;
            }
            // 3、 为每一个根节点设置子节点的数据
            // 如果不是根节点，必然此节点会有父节点
            // 查找父节点，并将这个节点设置为父节点的children属性
            for(Menu menuParent : menus){
                // 判断 menuParent 的 id 属性 是否等于 menu 的 pId 属性 ， 如果一致，找到它的爸爸了
                if(Objects.equals(menuParent.getId(),menu.getpId())){
                    // 将 menu 添加到 menuParent 的 children 属性中
                    menuParent.getChildren().add(menu);
                    break;
                }
            }

        }
        // 4、最终返回根节点即可
        return ResultEntity.successWithData(root);
    }
}
