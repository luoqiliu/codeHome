package com.holydota.common.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.holydota.common.interceptor.count.CountTime;
import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;
import com.holydota.common.util.HandlerMethodUtils;

/**
 * 性能统计,统计所有加了CountTime和耗时大于500毫秒的请求 <br>
 * 
 */
public class CountTimeInterceptor implements HandlerInterceptor {

    private ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<Long>();

    private ILog              logger               = LogFactory.getLog("countLogger");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = HandlerMethodUtils.getHandleMethod(handler);
        if (handlerMethod != null) {
            startTimeThreadLocal.set(System.currentTimeMillis());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
                                                                                                                       throws Exception {

        HandlerMethod handlerMethod = HandlerMethodUtils.getHandleMethod(handler);
        if (handlerMethod != null) {
            Method method = handlerMethod.getMethod();
            CountTime annotation = method.getAnnotation(CountTime.class);
            if (annotation == null) {
                annotation = handlerMethod.getBeanType().getAnnotation(CountTime.class);
            }

            if (annotation != null) {
                printCostInfo(handlerMethod, true, annotation.maxMilles());
            } else {
                printCostInfo(handlerMethod, false, 500);
            }
        }

    }

    /**
     * 打印时间统计信息 <br>
     * @param handlerMethod
     * @param forcePrint
     *            强制打印
     * @param maxMilles
     *            最大响应时间(毫秒)
     */
    private void printCostInfo(HandlerMethod handlerMethod, boolean forcePrint, int maxMilles) {
        long startTime = startTimeThreadLocal.get();
        double cost = System.currentTimeMillis() - startTime;

        StringBuilder sb = new StringBuilder();
        Method executor = HandlerMethodUtils.getHanderMethodExecutor(handlerMethod);

        boolean outofTime = cost > maxMilles;
        if (forcePrint || outofTime) {

            sb.append(executor.getName().toString());
            sb.append(" cost:\t");
            sb.append(cost);
            sb.append(" ms");

            if (outofTime) {
                logger.warn(sb.toString());
            } else {
                logger.info(sb.toString());
            }
        }
    }
}
