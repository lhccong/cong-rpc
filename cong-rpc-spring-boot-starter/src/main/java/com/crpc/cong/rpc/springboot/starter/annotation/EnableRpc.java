package com.crpc.cong.rpc.springboot.starter.annotation;


import java.lang.annotation.*;

/**
 * 启用 Rpc 注解
 *
 * @author cong
 * @date 2024/03/14
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableRpc {

    /**
     * 需要服务器
     * 需要启动 server
     *
     * @return boolean
     */
    boolean needServer() default true;
}