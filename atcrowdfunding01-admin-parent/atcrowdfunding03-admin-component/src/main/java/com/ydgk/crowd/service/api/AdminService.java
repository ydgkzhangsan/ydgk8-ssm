package com.ydgk.crowd.service.api;

import com.ydgk.crowd.entity.Admin;

import java.util.List;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-28 16:42
 */
public interface AdminService {

    List<Admin> getAdminList();

    Integer updateAdmin(Admin admin);

}
