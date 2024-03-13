package com.cong.rpc.core.fault.tolerant;

import cn.hutool.core.collection.CollUtil;
import com.cong.rpc.core.loadbalancer.LoadBalancer;
import com.cong.rpc.core.loadbalancer.LoadBalancerFactory;
import com.cong.rpc.core.loadbalancer.LoadBalancerKeys;
import com.cong.rpc.core.model.RpcRequest;
import com.cong.rpc.core.model.RpcResponse;
import com.cong.rpc.core.model.ServiceMetaInfo;
import com.cong.rpc.core.server.tcp.VertxTcpClient;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 转移到其他服务节点 - 容错策略
 *
 * @author cong
 * @date 2024/03/13
 */
@Slf4j
public class FailOverTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) throws ExecutionException, InterruptedException {
        //可自行扩展，获取其他服务节点并调用
        List<ServiceMetaInfo> serviceMetaInfoList = (List<ServiceMetaInfo>) context.get("serviceList");
        ServiceMetaInfo errorService = (ServiceMetaInfo) context.get("errorService");
        RpcRequest rpcRequest = (RpcRequest) context.get("rpcRequest");
        // 从服务列表中移除错误服务
        serviceMetaInfoList.remove(errorService);
        // 重新调用其他服务
        if (CollUtil.isNotEmpty(serviceMetaInfoList)) {
            // 重新调用其他服务
            // 负载均衡
            // 将调用方法名（请求路径）作为负载均衡参数
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());
            // 负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(LoadBalancerKeys.ROUND_ROBIN);
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
           return VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo);
        }
        return null;
    }
}