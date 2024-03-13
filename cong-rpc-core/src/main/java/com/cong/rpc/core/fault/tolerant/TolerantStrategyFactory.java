package com.cong.rpc.core.fault.tolerant;

import com.cong.rpc.core.spi.SpiLoader;

/**
 * 容错策略工厂（工厂模式，用于获取容错策略对象）
 *
 * @author cong
 * @date 2024/03/13
 */
public class TolerantStrategyFactory {

    static {
        SpiLoader.load(TolerantStrategy.class);
    }


    /**
     * 获取实例
     *
     * @param key 钥匙
     * @return {@link TolerantStrategy}
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }

}