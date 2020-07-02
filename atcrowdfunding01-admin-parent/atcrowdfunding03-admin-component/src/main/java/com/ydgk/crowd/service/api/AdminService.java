package com.ydgk.crowd.service.api;

import com.github.pagehelper.PageInfo;
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

    Admin getAdminByLoginAcctAndPaswd(String loginAcct, String userPswd);

    PageInfo<Admin> getAdminsByKeyWord(Integer pageNum, Integer pageSize, String keyWord);

    void removeAdminById(Integer adminId);

    void saveAdmin(Admin admin);

    Admin getAdminById(Integer adminId);

    void editAdmin(Admin admin, String originalLoginAcct);
}
