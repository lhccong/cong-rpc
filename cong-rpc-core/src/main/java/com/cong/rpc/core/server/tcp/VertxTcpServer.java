package com.cong.rpc.core.server.tcp;

import com.cong.rpc.core.server.http.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VertxTcpServer implements HttpServer {

    private byte[] handleRequest(byte[] requestData) {
        // 在这里编写处理请求的逻辑，根据 requestData 构造响应数据并返回
        // 这里只是一个示例，实际逻辑需要根据具体的业务需求来实现
        return "Hello, client!".getBytes();
    }

    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
//        server.connectHandler(new TcpServerHandler());
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                String testMessage = "Hello, server!Hello, server!Hello, server!Hello, server!";
                int messageLength = testMessage.getBytes().length;
                if (buffer.getBytes().length < messageLength) {
                    System.out.println("半包, length = " + buffer.getBytes().length);
                    return;
                }
                if (buffer.getBytes().length > messageLength) {
                    System.out.println("粘包, length = " + buffer.getBytes().length);
                    return;
                }
                String str = new String(buffer.getBytes(0, messageLength));
                System.out.println(str);
                if (testMessage.equals(str)) {
                    System.out.println("good");
                }
            });
        });

        // 启动 TCP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                log.info("TCP server started on port " + port);
            } else {
                log.info("Failed to start TCP server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}