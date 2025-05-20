package com.springboot.sample.interceptors;

import com.kit.common.util.common.gson.FormatUtils;
import com.springboot.sample.common.annotation.Anonymous;
import com.kit.common.util.http.NewResponseModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: kiturone
 * @data 2018/5/8
 * @Description: 登入拦截
 */
@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod c = (HandlerMethod) handler;
            Anonymous anonymous = c.getMethodAnnotation(Anonymous.class);

            /**允许匿名访问**/
            if (null != anonymous && anonymous.value()) {
                return true;
            }
            //判断是否存在token
            if (null == "token") {
                NewResponseModel responseModel = NewResponseModel.LoginError();
                response.getOutputStream().write(FormatUtils.obj2str(responseModel).getBytes());
                response.setContentType("application/json;charset=UTF-8");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                response.setHeader("Access-Control-Allow-Methods", "POST, GET");
                response.setHeader("Access-Control-Allow-Credentials", "true");

                return false;
            }
            return true;

        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}