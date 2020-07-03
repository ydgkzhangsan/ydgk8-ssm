package com.ydgk.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydgk.crowd.entity.Role;
import com.ydgk.crowd.mapper.RoleMapper;
import com.ydgk.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-03 9:44
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getRoleByKeyWord(Integer pageNum, Integer pageSize, String keyWord) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roles = roleMapper.selectRoleByKeyWord(keyWord);
        return new PageInfo<Role>(roles);
    }
}
