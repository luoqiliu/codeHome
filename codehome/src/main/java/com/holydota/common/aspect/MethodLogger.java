package com.holydota.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;

@Aspect
public class MethodLogger {
    private ILog logger = LogFactory.getLog("mathLogger");

    @Around("execution(* *(..)) && @annotation(Loggable)")
    public Object around(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            logger.error("", e);
        }
        logger.info(String.format("#%s(%s): %s in %[msec]s", MethodSignature.class.cast(point.getSignature())
            .getMethod().getName(), point.getArgs(), result, System.currentTimeMillis() - start));
        return result;
    }
}