package com.cong.rpc.core.server.tcp;

import com.cong.rpc.core.server.http.HttpServer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;
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

        server.connectHandler(socket -> {
            // 构造parser
            String testMessage = "Hello, server!Hello, server!Hello, server!Hello, server!";
            int messageLength = testMessage.getBytes().length;
            RecordParser parser = RecordParser.newFixed(messageLength);

            parser.setOutput(buffer -> {
                String str = new String(buffer.getBytes());
                System.out.println(str);
                if (testMessage.equals(str)) {
                    System.out.println("good");
                }
            });
            socket.handler(parser);
        });
        server.listen(port, result -> {
            if (result.succeeded()) {
                log.info("TCP server started on port " + port);
            } else {
                log.info("Failed to start TCP server: " + result.cause());
            }
        });

        // 启动 TCP 服务器并监听指定端口
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}