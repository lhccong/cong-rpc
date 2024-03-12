package com.cong.rpc.core.fault.retry;


import com.cong.rpc.core.spi.SpiLoader;

/**
 * 重试策略工厂（用于获取重试器对象）
 *
 * @author cong
 * @date 2024/03/12
 */
public class RetryStrategyFactory {

    private RetryStrategyFactory() {
        throw new IllegalStateException("Utility class");
    }

    static {
        SpiLoader.load(RetryStrategy.class);
    }


    /**
     * 获取实例
     *
     * @param key 钥匙
     * @return {@link RetryStrategy}
     */
    public static RetryStrategy getInstance(String key) {
        return SpiLoader.getInstance(RetryStrategy.class, key);
    }

}