package com.holydota.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.holydota.common.interceptor.auth.AuthPassport;
import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;
import com.holydota.service.LoginService;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private LoginService loginService;

    private ILog         logger = LogFactory.getLog("authLogger");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            if (authPassport == null || authPassport.validate() == false) {
                return true;
            } else {
                //如果请求为空或者idx和token异常,基本可以肯定是错误调用或者是攻击
                if (request == null || StringUtils.isBlank(request.getParameter("idx"))
                    || StringUtils.isBlank(request.getParameter("token"))) {
                    logger.warn("user auth fail,maybe idx or token is null");
                    //返回到登录界面
                    response.sendRedirect(request.getContextPath() + "/user/login");
                    return false;
                }
                String idx = request.getParameter("idx");
                String token = request.getParameter("token");
                if (loginService.checkLogin(idx, token)) {
                    logger.info("user auth success,idx=[" + idx + "], token=[" + token + "]");
                    return true;
                } else {
                    logger.info("user auth fail,idx=[" + idx + "], token=[" + token + "]");
                    //返回到登录界面
                    response.sendRedirect(request.getContextPath() + "/user/login");
                    return false;
                }
            }
        }

        return true;

    }
}
