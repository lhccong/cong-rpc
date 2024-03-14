package com.cong.rpc.core.bootstrap;


import com.cong.rpc.core.RpcApplication;

/**
 * 服务消费者启动类（初始化）
 *
 * @author cong
 * @date 2024/03/14
 */
public class ConsumerBootstrap {
    private ConsumerBootstrap() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 初始化
     */
    public static void init() {
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }

}