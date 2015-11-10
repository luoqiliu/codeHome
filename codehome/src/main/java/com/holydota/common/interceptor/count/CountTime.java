package com.holydota.common.interceptor.count;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CountTime {

    /**
     * 最大响应时间,默认5秒 <br>
     * <br>
     * 
     * @return
     */
    int maxMilles() default 5000;
}
