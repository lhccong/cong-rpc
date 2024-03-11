package com.cong.rpc.core.registry;


import com.cong.rpc.core.spi.SpiLoader;

/**
 * 注册表工厂
 * 注册中心工厂（用于获取注册中心对象）
 *
 * @author cong
 * @date 2024/03/08
 */
public class RegistryFactory {

    static {
        SpiLoader.load(Registry.class);
    }


    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }

}