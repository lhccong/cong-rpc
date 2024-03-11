package com.cong.rpc.core.loadbalancer;

/**
 * 负载均衡器键名常量
 *
 * @author cong
 * @date 2024/03/11
 */
public interface LoadBalancerKeys {

    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";

    /**
     * 随机
     */
    String RANDOM = "random";

    /**
     * 一致哈希
     */
    String CONSISTENT_HASH = "consistentHash";

}