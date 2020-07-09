package com.ydgk.crowd.config;

import com.ydgk.crowd.entity.Admin;
import com.ydgk.crowd.entity.Auth;
import com.ydgk.crowd.entity.Role;
import com.ydgk.crowd.service.api.AdminService;
import com.ydgk.crowd.service.api.AuthService;
import com.ydgk.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-09 11:31
 */
@Component
public class CrowUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String loginAcct) throws UsernameNotFoundException {

        // 1、 根据用户的登录账号查询数据库得到登录的密码
        Admin admin = adminService.getAdminByLoginAcct(loginAcct);

        // 2、 需要封装一个 authorities 对象   这个对象就是用户对应的角色和权限信息
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        // 3、 获取用户的角色和权限信息
        // 4、 获取用户角色信息
        List<Role> roleList = roleService.getAssignedList(admin.getId());
        // 5、 获取权限信息
        List<Auth> authList = authService.getAuthListByAdminId(admin.getId());

        // 6、 将角色信息封装到 authorities 中
        if(roleList != null && roleList.size() > 0){
            for(Role role : roleList){
                // 获取角色的名字
                String roleName = role.getRoleName();
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);
                authorities.add(authority);
            }
        }

        // 7、 将权限信息封装到 authorities 中
        if(authList != null && authList.size() > 0){
            for(Auth auth : authList){
                // 获取角色的名字
                String authName = auth.getName();
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authName);
                authorities.add(authority);
            }
        }

        // 创建一个 CrowdUserDetails 类，扩展 User 类，将原始的 admin 对象封装好
        CrowdUserDetails userDetails = new CrowdUserDetails(admin, authorities);

        // 最终返回 CrowdUserDetails 对象
        return userDetails;
    }

}
