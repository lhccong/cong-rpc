package com.crpc.cong.rpc.springboot.starter.bootstrap;

import com.cong.rpc.core.RpcApplication;
import com.cong.rpc.core.config.RpcConfig;
import com.cong.rpc.core.server.tcp.VertxTcpServer;
import com.crpc.cong.rpc.springboot.starter.annotation.EnableRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Objects;

/**
 * Rpc 框架启动
 *
 * @author cong
 * @date 2024/03/14
 */
@Slf4j
public class RpcInitBootstrap implements ImportBeanDefinitionRegistrar {

    /**
     * Spring 初始化时执行，初始化 RPC 框架
     *
     * @param importingClassMetadata 导入类元数据
     * @param registry               注册表
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 获取 EnableRpc 注解的属性值
        boolean needServer = (boolean) Objects.requireNonNull(importingClassMetadata.getAnnotationAttributes(EnableRpc.class.getName()))
                .get("needServer");

        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();

        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 启动服务器
        if (needServer) {
            VertxTcpServer vertxTcpServer = new VertxTcpServer();
            vertxTcpServer.doStart(rpcConfig.getServerPort());
        } else {
            log.info("不启动 server");
        }

    }
}