package com.cong.rpc.core.loadbalancer;

import com.cong.rpc.core.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * 负载均衡器（消费端使用）
 * @author liuhuaicong
 */
public interface LoadBalancer {

    /**
     * 选择服务调用
     *
     * @param requestParams       请求参数
     * @param serviceMetaInfoList 可用服务列表
     * @return {@link ServiceMetaInfo}
     */
    ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);
}