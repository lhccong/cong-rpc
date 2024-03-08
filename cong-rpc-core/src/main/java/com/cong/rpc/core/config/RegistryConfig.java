package com.cong.rpc.core.config;

import lombok.Data;

/**
 * 注册表配置
 * RPC 框架注册中心配置
 *
 * @author 86188
 * @date 2024/03/08
 */
@Data
public class RegistryConfig {

    /**
     * 注册中心类别
     */
    private String registry = "etcd";

    /**
     * 注册中心地址
     */
    private String address = "http://localhost:2380";

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间（单位毫秒）
     */
    private Long timeout = 10000L;
}