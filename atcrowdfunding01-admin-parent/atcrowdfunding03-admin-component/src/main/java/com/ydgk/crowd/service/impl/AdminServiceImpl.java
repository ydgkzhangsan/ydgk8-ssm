package com.ydgk.crowd.service.impl;

import com.ydgk.crowd.entity.Admin;
import com.ydgk.crowd.entity.AdminExample;
import com.ydgk.crowd.mapper.AdminMapper;
import com.ydgk.crowd.service.api.AdminService;
import com.ydgk.ssm.constant.CrowdConstant;
import com.ydgk.ssm.exceptions.LoginFailedException;
import com.ydgk.ssm.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

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

    @Override
    public Admin getAdminByLoginAcctAndPaswd(String loginAcct, String userPswd) {
        //1、 根据账号查询数据库获取Admin信息
        // ②. 获取 AdminExample 对象
        AdminExample adminExample = new AdminExample();
        // 根据 AdminExample 获取 Criteria 对象，
        AdminExample.Criteria criteria = adminExample.createCriteria();
        // ③. 将loginAcct封装到 Criteria 中
        criteria.andLoginAcctEqualTo(loginAcct);
        // ①. 调用 AdminMapper 接口中的 selectByExample 方法
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        // ④. 拿到 Admin 结果
        if (admins == null || admins.size() < 1){
            // 抛出登录失败的异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        Admin admin = admins.get(0);

        //2、 进行密码的校验, 校验不成功直接抛出 LoginFailedException
        // 将前台表单传入的 userPaswd 加密
        userPswd = CrowdUtil.md5(userPswd);
        // 将加密之后的文本和admin对象中的userPaswd进行比较，校验不成功直接抛出 LoginFailedException
        if(!Objects.equals(userPswd,admin.getUserPswd())){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        //3、 如果校验成功，将获取到的 Admin 返回
        return admin;
    }
}
