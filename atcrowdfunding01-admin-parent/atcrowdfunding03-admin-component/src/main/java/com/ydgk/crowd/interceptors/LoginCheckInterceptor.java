package com.ydgk.crowd.interceptors;

import com.ydgk.ssm.constant.CrowdConstant;
import com.ydgk.ssm.exceptions.AccessForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-30 15:34
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 进行登录检查   检查session域对象中是否存在 admin 属性对应的值
        HttpSession session = request.getSession();
        // 尝试从Session中获取admin属性
        Object admin = session.getAttribute(CrowdConstant.ATTR_NAME_ADMIN_INFO);
        if(admin == null){
           throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        }
        return true;
    }

}
