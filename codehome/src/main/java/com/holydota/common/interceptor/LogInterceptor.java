package com.holydota.common.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;
import com.holydota.common.util.CodecUtil;

public class LogInterceptor implements HandlerInterceptor {

    private ILog logger = LogFactory.getLog("requestLogger");

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        Map param = request.getParameterMap();
        String paramJson = mapper.writeValueAsString(param);
        String sign = CodecUtil.getInstance().useSha1().encodeStr(servletPath + (new Date()).getTime());
        request.setAttribute("sign", sign);
        logger.info("start request! servletPath=[" + servletPath + "],param=[" + paramJson + "],sign=[" + sign + "]");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
                                                                                                                       throws Exception {
        String sign = (String) request.getAttribute("sign");
        logger.info("finish request! sign=[" + sign + "]");
    }
}
