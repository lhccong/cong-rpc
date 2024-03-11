package com.cong.rpc.core.loadbalancer;


import com.cong.rpc.core.model.ServiceMetaInfo;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询负载均衡器
 * @author liuhuaicong
 */
public class RoundRobinLoadBalancer implements LoadBalancer {

    /**
     * 当前轮询的下标
     */
    private final AtomicInteger currentIndex = new AtomicInteger(0);

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()) {
            return null;
        }
        // 只有一个服务，无需轮询
        int size = serviceMetaInfoList.size();
        if (size == 1) {
            return serviceMetaInfoList.get(0);
        }

        int index = currentIndex.getAndIncrement() % size;
        return serviceMetaInfoList.get(index);
    }
}