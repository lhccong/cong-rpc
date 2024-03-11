package com.cong.rpc.core.loadbalancer;

import com.cong.rpc.core.model.ServiceMetaInfo;
import net.openhft.hashing.LongHashFunction;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 一致哈希负载均衡器
 *
 * @author cong
 * @date 2024/03/11
 */
public class ConsistentHashLoadBalancer implements LoadBalancer {
    /**
     * 一致性 Hash 环，存放虚拟节点
     */
    private final TreeMap<Long, ServiceMetaInfo> virtualNodes = new TreeMap<>();

    /**
     * 虚拟节点数
     */
    private static final int VIRTUAL_NODE_NUM = 100;
    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()) {
            return null;
        }
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                long hash = getHash(serviceMetaInfo.toString() + "#" + i);
                virtualNodes.put(hash, serviceMetaInfo);
            }
        }

        // 获取调用请求的 hash 值
        long hash = getHash(requestParams);

        // 选择最接近且大于等于调用请求 hash 值的虚拟节点
        Map.Entry<Long, ServiceMetaInfo> entry = virtualNodes.ceilingEntry(hash);
        if (entry == null) {
            // 如果没有大于等于调用请求 hash 值的虚拟节点，则返回环首部的节点
            entry = virtualNodes.firstEntry();
        }
        return entry.getValue();
    }

    /**
     * 获取哈希
     * Hash 算法，MurmurHash 算法实现
     *
     * @param key 钥匙
     * @return long
     */
    private  long getHash(Object key) {
        // 获取 MurmurHash3 的实例
        LongHashFunction murmur3 = LongHashFunction.murmur_3();

        // 计算输入字符串的哈希值
        return murmur3.hashChars(key.toString());
    }


}
