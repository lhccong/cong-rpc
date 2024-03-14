package com.cong.rpc.core.fault.tolerant;

import com.cong.rpc.core.RpcApplication;
import com.cong.rpc.core.config.RpcConfig;
import com.cong.rpc.core.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 降级到其他服务 - 容错策略
 *
 * @author cong
 * @date 2024/03/13
 */
@Slf4j
public class FailBackTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // 可自行扩展，获取降级的服务并调用
        // 从注册中心获取服务提供者请求地址
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        MockService mockService = MockServiceFactory.getInstance(rpcConfig.getMockService());
        Object mock = mockService.mock();
        return RpcResponse.builder().data(mock).message("ok").build();
    }
}