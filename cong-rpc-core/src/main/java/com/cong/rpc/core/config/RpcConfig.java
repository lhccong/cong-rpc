package com.cong.rpc.core.config;

import com.cong.rpc.core.fault.retry.RetryStrategyKeys;
import com.cong.rpc.core.fault.tolerant.MockServiceKeys;
import com.cong.rpc.core.fault.tolerant.TolerantStrategyKeys;
import com.cong.rpc.core.loadbalancer.LoadBalancerKeys;
import com.cong.rpc.core.serializer.SerializerKeys;
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

    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;

    /**
     * 负载均衡器
     */
    private String loadBalancer = LoadBalancerKeys.ROUND_ROBIN;

    /**
     * 注册表配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();
    /**
     * 重试策略
     */
    private String retryStrategy = RetryStrategyKeys.NO;
    /**
     * 容错策略
     */
    private String tolerantStrategy = TolerantStrategyKeys.FAIL_OVER;
    /**
     * 模拟服务
     */
    private String mockService = MockServiceKeys.DEFAULT;
    /**
     * 模拟数据
     */
    private Object mockData = null;

}