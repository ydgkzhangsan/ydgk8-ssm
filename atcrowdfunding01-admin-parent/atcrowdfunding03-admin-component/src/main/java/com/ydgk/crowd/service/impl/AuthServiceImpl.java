package com.ydgk.crowd.service.impl;

import com.ydgk.crowd.entity.Auth;
import com.ydgk.crowd.mapper.AuthMapper;
import com.ydgk.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-07 14:44
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;


    @Override
    public List<Auth> getAll() {
        return authMapper.selectByExample(null);
    }

    @Override
    public List<Integer> getAuthIdByRoleId(Integer roleId) {
        return authMapper.getAuthIdByRoleId(roleId);
    }

    @Override
    public void saveRoleAuth(Map<String, List<Integer>> map) {
        // 先通过roleId将以前的权限删除，然后在插入新的数据
        authMapper.deleteOldAuth(map.get("roleId").get(0));
        authMapper.insertRoleAssignAuth(map.get("roleId").get(0),map.get("authIds"));
    }

    @Override
    public List<Auth> getAuthListByAdminId(Integer id) {
        return authMapper.selectAuthListByAdminId(id);
    }
}
