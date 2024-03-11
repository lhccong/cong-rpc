package com.cong.rpc.core.loadbalancer;


import com.cong.rpc.core.spi.SpiLoader;

/**
 * 负载均衡器工厂（工厂模式，用于获取负载均衡器对象）
 *
 * @author cong
 * @date 2024/03/11
 */
public class LoadBalancerFactory {

    static {
        SpiLoader.load(LoadBalancer.class);
    }

    /**
     * 获取实例
     *
     * @param key 钥匙
     * @return {@link LoadBalancer}
     */
    public static LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }

}