package com.cong.rpc.core.bootstrap;

import com.cong.rpc.core.RpcApplication;
import com.cong.rpc.core.config.RegistryConfig;
import com.cong.rpc.core.config.RpcConfig;
import com.cong.rpc.core.model.ServiceMetaInfo;
import com.cong.rpc.core.model.ServiceRegisterInfo;
import com.cong.rpc.core.registry.LocalRegistry;
import com.cong.rpc.core.registry.Registry;
import com.cong.rpc.core.registry.RegistryFactory;
import com.cong.rpc.core.server.tcp.VertxTcpServer;

import java.util.List;

/**
 * 提供程序引导程序
 *
 * @author cong
 * @date 2024/03/14
 */
public class ProviderBootstrap {
    private ProviderBootstrap() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * 初始化
     */
    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 注册服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            // 本地注册
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());

            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + " 服务注册失败", e);
            }
        }

        // 启动服务器
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }
}
