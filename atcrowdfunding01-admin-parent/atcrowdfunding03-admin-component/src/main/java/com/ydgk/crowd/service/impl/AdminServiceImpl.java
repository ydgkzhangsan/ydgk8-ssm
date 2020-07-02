package com.ydgk.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydgk.crowd.entity.Admin;
import com.ydgk.crowd.entity.AdminExample;
import com.ydgk.crowd.mapper.AdminMapper;
import com.ydgk.crowd.service.api.AdminService;
import com.ydgk.ssm.constant.CrowdConstant;
import com.ydgk.ssm.exceptions.AccountNameAlreadyInUser;
import com.ydgk.ssm.exceptions.LoginFailedException;
import com.ydgk.ssm.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public PageInfo<Admin> getAdminsByKeyWord(Integer pageNum, Integer pageSize, String keyWord) {
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> admins = adminMapper.selectByKeyWord(keyWord);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins, 5);
        return adminPageInfo;
    }

    @Override
    public void removeAdminById(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public void saveAdmin(Admin admin) {
        // 校验账号是否存在
        // 1、 根据loginAcct属性查询数据库，如果能查到Admin信息，说明loginAcct存在，
        List<Admin> admins = validateLoginAcctIsAlready(admin);
        if(admins != null && admins.size() > 0){
            throw new AccountNameAlreadyInUser(CrowdConstant.MESSAGE_ACCOUNT_NAME_ALREADY_IN_USER);
        }
        // 2、如果没有就继续执行新增操作
        String pwd = CrowdUtil.md5("123456");
        admin.setUserPswd(pwd);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createTime = format.format(new Date());
        admin.setCreateTime(createTime);
        adminMapper.insert(admin);
    }

    private List<Admin> validateLoginAcctIsAlready(Admin admin) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(admin.getLoginAcct());
        return adminMapper.selectByExample(adminExample);
    }


    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void editAdmin(Admin admin, String originalLoginAcct) {
        // 判断账号是否被修改
        if(!Objects.equals(admin.getLoginAcct().trim(),originalLoginAcct)){
            // 如果修改过，需要判断数据库中是否存在这个账号
            List<Admin> admins = validateLoginAcctIsAlready(admin);
            if(admins != null && admins.size() > 0){
                throw new AccountNameAlreadyInUser(CrowdConstant.MESSAGE_ACCOUNT_NAME_ALREADY_IN_USER_EDIT);
            }
        }
        // 如果没有修改，则可以直接修改用户信息
        adminMapper.updateByPrimaryKey(admin);
    }
}
