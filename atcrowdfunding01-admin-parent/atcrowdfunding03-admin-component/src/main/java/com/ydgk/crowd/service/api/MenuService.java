package com.ydgk.crowd.service.api;

import com.ydgk.crowd.entity.Menu;

import java.util.List;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-06 10:39
 */
public interface MenuService {

    List<Menu> getAll();

    void saveMenu(Menu menu);

    void deleteMenuById(Integer id);

    void editMenu(Menu menu);
}
