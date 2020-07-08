package com.ydgk.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-08 16:53
 */
@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    此方法是由SpringSecurity调用的方法，会自动传入一个String类型的参数，参数是用户的登录名
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.使用 SQL 语句根据用户名查询用户对象
        String sql = "SELECT id,loginacct,userpswd,username,email,createtime FROM t_admin WHERE loginacct = ?";

        //2.获取查询结果
        Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql, username);

        //3.获取用户名、密码数据
        String loginacct = resultMap.get("loginacct").toString();
        String userpswd = resultMap.get("userpswd").toString();

        //4.创建权限列表   现在用户的角色信息还是模型信息
        List<GrantedAuthority> list = AuthorityUtils.createAuthorityList("ADMIN","USER");

        //5.创建用户对象
        User user = new User(loginacct, userpswd, list);
        return user;
    }

}
