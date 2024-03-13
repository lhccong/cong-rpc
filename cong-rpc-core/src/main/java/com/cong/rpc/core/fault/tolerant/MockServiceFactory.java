package com.cong.rpc.core.fault.tolerant;

import com.cong.rpc.core.spi.SpiLoader;

/**
 * 模拟服务工厂
 *
 * @author cong
 * @date 2024/03/13
 */
public class MockServiceFactory {

    static {
        SpiLoader.load(MockService.class);
    }


    /**
     * 获取实例
     *
     * @param key 钥匙
     * @return {@link MockService}
     */
    public static MockService getInstance(String key) {
        return SpiLoader.getInstance(MockService.class, key);
    }

}