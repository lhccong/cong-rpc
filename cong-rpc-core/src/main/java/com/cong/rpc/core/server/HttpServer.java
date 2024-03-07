package com.cong.rpc.core.server;

/**
 * HTTP 服务器接口
 *
 * @author cong
 * @date 2024/03/07
 */
public interface HttpServer {

    /**
     * 启动服务器
     *
     * @param port 端口
     */
    void doStart(int port);
}
