package com.ydgk.spring.config;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-08 10:36
 */
@Configuration
/*
@EnableWebSecurity : 启用 webSecurity

后面再学习SpringBoot时会出现很多的@EnableXxxxXxx 注解，都是表示启用xxx功能
 */
@EnableWebSecurity
/*
WebSecurityConfigurerAdapter 类： 是 WebSecurity 配置的适配器
    继承这个类表示可以在这个类中配置 WebSecurity
 */
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AppUserDetailsService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 创建 PersistentTokenRepository 对象
        // 实际上是创建 PersistentTokenRepository 实现类的对象 JdbcTokenRepositoryImpl
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);

        http.authorizeRequests() // 此方法表示 请求认证
            .antMatchers("/index.jsp","/layui/**") // 表示使用ant风格的地址匹配路径
            .permitAll() // 表示将上面匹配的请求放行
            .antMatchers("/level1/**")
            .hasRole("武当高手")
            .antMatchers("/level2/**")
            .hasRole("绝世高手")
            .antMatchers("/level3/**")
            .hasRole("绝顶高手")
             // 对于权限控制需要将权限设置的相关内容放入 anyRequest() 方法之前
            .anyRequest() // 对于没有设置的请求
            .authenticated() // 都需要进行认证
            .and()
            .formLogin()   // 表示将为登录的请求发送给一个表单页
            .loginPage("/index.jsp")   // 指定用户的认证页面
            .loginProcessingUrl("/do/login.html")  // 指定登录表单登录的地址
            .usernameParameter("loginAcct") // 定制登录表单页的用户名请求参数
            .passwordParameter("loginPswd") // 定制登录表单页的密码请求参数
            .defaultSuccessUrl("/main.html") // 设置如果校验成功之后去的页面
            //.and().csrf().disable() // 禁用 CSRF
            .and()
            .logout() // 开启退出功能
            .logoutUrl("/do/logout.html") // 指定退出之后的页面地址
            // 如果没有指定退出成功页，SpringSecurity会自动的回到登录页。
            .logoutSuccessUrl("/index.jsp") // 指定退出之后去到哪个页面
//            .addLogoutHandler(new LogoutHandler() { // 指定用于处理退出的处理器，退出时会执行
//                @Override
//                public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
//                }
//            })
//            // 用来处理退出成功之后的处理器
//            .logoutSuccessHandler(new LogoutSuccessHandler() {
//                @Override
//                public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                    httpServletResponse.getWriter().write("abcd");
//                }
//            })
            .and().exceptionHandling()//异常处理
//            .accessDeniedPage("/to/no/auth/page.html") // 没有权限显示的页面
            .accessDeniedHandler(new AccessDeniedHandler() {
                @Override
                public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                    httpServletRequest.setAttribute("message",e.getMessage());
                    httpServletRequest.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(httpServletRequest,httpServletResponse);
                }
            })
            .and()
            .rememberMe() // 表示开启 remember-me 功能
            .rememberMeParameter("rmbMe") // 定制 remember-me 的参数
            .tokenRepository(repository)  //启 用 remember-me 数据库版
            ;
    }

    /*
    这个方法可以用来指定认证的信息
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()       // 表示在内存中进行认证
//                .withUser("tom")  // 在内存中添加一个用户
//                .password("123123")         // 指定用户的密码
//                .roles("武当高手","绝世高手")          // 指定用户的角色
//                .and()
//                .withUser("jerry") // 再添加一个新的用户
//                .password("123123")         // 指定用户的密码
//                .authorities("丐帮帮主")    // 指定用户的权限
//                ;
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}
