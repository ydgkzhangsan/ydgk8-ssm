package com.ydgk.crowd.interceptors;

import com.google.gson.Gson;
import com.ydgk.ssm.constant.CrowdConstant;
import com.ydgk.ssm.exceptions.AccessForbiddenException;
import com.ydgk.ssm.util.CrowdUtil;
import com.ydgk.ssm.util.ResultEntity;
import org.springframework.stereotype.Component;
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
            // 判断请求是ajax请求还是普通请求
            if(CrowdUtil.judgeRequestType(request)){
                ResultEntity<Object> failed = ResultEntity.failed("请先登陆之后再访问！");
                Gson gson = new Gson();
                String jsonResult = gson.toJson(failed);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonResult);
                return false;
            }else {
                throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
            }
        }
        return true;
    }

}
