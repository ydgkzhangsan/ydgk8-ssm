package com.ydgk.crowd.service.impl;

import com.ydgk.crowd.entity.Menu;
import com.ydgk.crowd.mapper.MenuMapper;
import com.ydgk.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-06 10:40
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(null);
    }

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void deleteMenuById(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void editMenu(Menu menu) {
        // 注意： 调用修改时，一定要使用 updateByPrimaryKeySelective 方法
        menuMapper.updateByPrimaryKeySelective(menu);
    }
}
