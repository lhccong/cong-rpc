package com.cong.rpc.core.server;

import com.cong.rpc.core.server.http.HttpServer;
import com.cong.rpc.core.server.http.HttpServerHandler;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

/**
 * Vertx HTTP 服务器
 *
 * @author liuhuaicong
 */
@Slf4j
public class VertxHttpServer implements HttpServer {

    /**
     * 开始
     * 启动服务器
     *
     * @param port 港口
     */
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 HTTP 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        // 启动 HTTP 服务器并监听指定端口
        server.listen(port, result -> {
            if (!result.succeeded()) {
                // 启动失败
                log.error("Failed to start server: " + result.cause());
            }
            log.info("服务已启动：{}", port);

        });
    }
}
