package com.ydgk.crowd.service.impl;

import com.ydgk.crowd.entity.Admin;
import com.ydgk.crowd.mapper.AdminMapper;
import com.ydgk.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-28 16:43
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> getAdminList() {
        List<Admin> admins = adminMapper.selectByExample(null);
        return admins;
    }

    @Override
    public Integer updateAdmin(Admin admin) {
        int i = adminMapper.updateByPrimaryKey(admin);
        String str = null;
        str.length();
        return i;
    }
}
