package com.crpc.cong.rpc.springboot.starter.annotation;


import com.crpc.cong.rpc.springboot.starter.bootstrap.RpcConsumerBootstrap;
import com.crpc.cong.rpc.springboot.starter.bootstrap.RpcInitBootstrap;
import com.crpc.cong.rpc.springboot.starter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 Rpc 注解
 *
 * @author cong
 * @date 2024/03/14
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * 需要服务器
     * 需要启动 server
     *
     * @return boolean
     */
    boolean needServer() default true;
}