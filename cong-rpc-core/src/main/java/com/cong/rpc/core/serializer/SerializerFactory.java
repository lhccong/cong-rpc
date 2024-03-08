package com.cong.rpc.core.serializer;

import com.cong.rpc.core.serializer.jdk.JdkSerializer;
import com.cong.rpc.core.spi.SpiLoader;

/**
 * 序列化器工厂（用于获取序列化器对象）
 *
 * @author cong
 * @date 2024/03/08
 */
public class SerializerFactory {
    /**
     * 默认序列化器
     */
    private static  Serializer DEFAULT_SERIALIZER ;




    /**
     * 获取实例
     *
     * @param key 钥匙
     * @return {@link Serializer}
     */
    public static Serializer getInstance(String key) {
        if (DEFAULT_SERIALIZER == null) {
            synchronized (SerializerFactory.class) {
                if (DEFAULT_SERIALIZER == null) {
                    SpiLoader.load(Serializer.class);
                    DEFAULT_SERIALIZER = new JdkSerializer();
                }
            }
        }
        return SpiLoader.getInstance(Serializer.class, key);
    }

}