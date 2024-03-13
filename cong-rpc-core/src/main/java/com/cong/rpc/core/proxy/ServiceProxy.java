package com.cong.rpc.core.proxy;

import cn.hutool.core.collection.CollUtil;
import com.cong.rpc.core.RpcApplication;
import com.cong.rpc.core.config.RpcConfig;
import com.cong.rpc.core.constant.RpcConstant;
import com.cong.rpc.core.fault.retry.RetryStrategy;
import com.cong.rpc.core.fault.retry.RetryStrategyFactory;
import com.cong.rpc.core.fault.tolerant.TolerantStrategy;
import com.cong.rpc.core.fault.tolerant.TolerantStrategyFactory;
import com.cong.rpc.core.loadbalancer.LoadBalancer;
import com.cong.rpc.core.loadbalancer.LoadBalancerFactory;
import com.cong.rpc.core.model.RpcRequest;
import com.cong.rpc.core.model.RpcResponse;
import com.cong.rpc.core.model.ServiceMetaInfo;
import com.cong.rpc.core.registry.Registry;
import com.cong.rpc.core.registry.RegistryFactory;
import com.cong.rpc.core.server.tcp.VertxTcpClient;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务代理（JDK 动态代理）
 *
 * @author cong
 * @date 2024/03/07
 */
@Slf4j
public class ServiceProxy implements InvocationHandler {

    /**
     * 调用
     * 调用代理
     *
     * @param proxy  代理
     * @param method 方法
     * @param args   参数
     * @return {@link Object}
     * @throws Throwable 可投掷
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        // 从注册中心获取服务提供者请求地址
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
        if (CollUtil.isEmpty(serviceMetaInfoList)) {
            throw new RuntimeException("暂无服务地址");
        }
        // 负载均衡
        LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
        // 将调用方法名（请求路径）作为负载均衡参数
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("methodName", rpcRequest.getMethodName());
        ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        // 发送 rpc 请求
        // 使用重试机制
        RpcResponse rpcResponse;
        try {
            RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
            rpcResponse = retryStrategy.doRetry(() ->
                    VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo)
            );
        } catch (Exception e) {
            // 容错机制
            HashMap<String, Object> map = new HashMap<>();
            map.put("serviceList", serviceMetaInfoList);
            //排查在外的服务
            map.put("errorService", selectedServiceMetaInfo);
            //传递rpcRequest
            map.put("rpcRequest", rpcRequest);
            TolerantStrategy tolerantStrategy = TolerantStrategyFactory.getInstance(rpcConfig.getTolerantStrategy());
            rpcResponse = tolerantStrategy.doTolerant(map, e);
        }

        return rpcResponse.getData();

    }
}
