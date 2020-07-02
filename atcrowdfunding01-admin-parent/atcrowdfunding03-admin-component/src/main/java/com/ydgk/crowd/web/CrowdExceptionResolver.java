package com.ydgk.crowd.web;

import com.google.gson.Gson;
import com.ydgk.ssm.constant.CrowdConstant;
import com.ydgk.ssm.exceptions.AccessForbiddenException;
import com.ydgk.ssm.exceptions.AccountNameAlreadyInUser;
import com.ydgk.ssm.exceptions.LoginFailedException;
import com.ydgk.ssm.util.CrowdUtil;
import com.ydgk.ssm.util.ResultEntity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-30 10:26
 */
@ControllerAdvice
public class CrowdExceptionResolver {

//    @ExceptionHandler(AccessForbiddenException.class)
//    public ModelAndView handlerAccessForbidden(HttpServletRequest request,
//                                           LoginFailedException exception,
//                                           HttpServletResponse response) throws IOException {
//        String viewName = "pages/admin-login";
//        return  commonExceptionResolver(viewName,request,response,exception);
//    }

    @ExceptionHandler(AccountNameAlreadyInUser.class)
    public ModelAndView handlerLoginFailed(HttpServletRequest request,
                                           AccountNameAlreadyInUser exception,
                                           HttpServletResponse response) throws IOException {
        String viewName = "";
        if(exception.getMessage().contains("修改")){
            viewName = "pages/admin-edit";
        } else {
            viewName = "pages/admin-add";
        }
        return  commonExceptionResolver(viewName,request,response,exception);
    }


    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView handlerLoginFailed(HttpServletRequest request,
                                           LoginFailedException exception,
                                           HttpServletResponse response) throws IOException {
        String viewName = "pages/admin-login";
        return  commonExceptionResolver(viewName,request,response,exception);
    }

    // 提取一个公共的异常处理方法
    private ModelAndView commonExceptionResolver(String viewName,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 Exception exception){
        // 1、使用写好的工具类判断请求类型是否是一个Ajax请求
        boolean judgeRequestType = CrowdUtil.judgeRequestType(request);
        // 判断请求的类型是什么类型
        if(judgeRequestType){
            // 表示是一个 ajax 请求
            // 如果是一个Ajax请求，获取异常消息，存入到 ResultEntity 中 ，并转换成json串，给出响应
            // 2、获取异常信息
            String message = exception.getMessage();

            // 3、创建一个ResultEntity对象
            ResultEntity<Object> failed = ResultEntity.failed(message);

            // 4、使用Gson进行解析ResultEntity对象拿到JSON串，写入响应中
            // 创建Gson对象
            Gson gson = new Gson();

            // 5、使用gson对象转换ResultEntity
            String jsonStr = gson.toJson(failed);

            // 6、 通过 Response对象将 jsonStr 写入响应中  获取writer对象
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 7、正式写入
            writer.write(jsonStr);
            // 8、设置响应类型
            response.setContentType("application/json");
            // 如果返回的是一个null SpringMVC会认为是自己给出了响应，不再使用SpringMVC提供的响应方法。
            return null;
        }
        // 表示不是一个 Ajax 请求
        // 9、需要将异常信息存入模型对象中，给出一个对应的错误视图
        // 创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        // 10、将错误消息传入模型中
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,exception);
        // 11、 设置响应的视图
        modelAndView.setViewName(viewName);
        // 12、返回ModelAndView对象
        return modelAndView;
    }
}
