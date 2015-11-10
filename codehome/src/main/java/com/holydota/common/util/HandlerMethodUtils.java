package com.holydota.common.util;

import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;

/**
 * spring mvc handleMehtod 工具 <br>
 */
public final class HandlerMethodUtils {
    /**
     * 拦截器中使用,获得handler <br>
    
     * @param handler
     * @return
     */
    public static final HandlerMethod getHandleMethod(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            return handlerMethod;
        }
        return null;
    }

    /**
     * 获得处理请求的控制器 <br>
     * 
     * @param handlerMethod
     * @return
     */
    public static final Method getHanderMethodExecutor(HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        return method;
    }
}
