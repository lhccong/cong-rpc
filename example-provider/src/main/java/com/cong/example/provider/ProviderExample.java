package com.cong.example.provider;


import com.cong.example.service.UserService;
import com.cong.rpc.core.RpcApplication;
import com.cong.rpc.core.config.RegistryConfig;
import com.cong.rpc.core.config.RpcConfig;
import com.cong.rpc.core.model.ServiceMetaInfo;
import com.cong.rpc.core.registry.LocalRegistry;
import com.cong.rpc.core.registry.Registry;
import com.cong.rpc.core.registry.RegistryFactory;
import com.cong.rpc.core.server.tcp.VertxTcpServer;

/**
 * 提供程序示例
 * 服务提供者示例
 *
 * @author 86188
 * @date 2024/03/10
 */
public class ProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 TCP 服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}