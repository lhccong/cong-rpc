package com.cong.example.provider;


import com.cong.example.service.UserService;
import com.cong.rpc.core.registry.LocalRegistry;
import com.cong.rpc.core.server.HttpServer;
import com.cong.rpc.core.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 *
 * @author cong
 * @date 2024/03/07
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
