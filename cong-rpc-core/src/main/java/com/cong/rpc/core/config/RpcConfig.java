package com.cong.rpc.core.config;

import lombok.Data;

/**
 * RPC 框架配置
 * @author liuhuaicong
 */
@Data
public class RpcConfig {
    /**
     * 模拟调用
     */
    private boolean mock = false;

    /**
     * 名称
     */
    private String name = "cong-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";
    
    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;

}