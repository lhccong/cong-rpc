package com.cong.rpc.core.serializer;

import com.cong.rpc.core.serializer.fastjson.FastJsonSerializer;
import com.cong.rpc.core.serializer.hessian.HessianSerializer;
import com.cong.rpc.core.serializer.jdk.JdkSerializer;
import com.cong.rpc.core.serializer.kryo.KryoSerializer;
import com.cong.rpc.core.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化器工厂（用于获取序列化器对象）
 *
 * @author cong
 * @date 2024/03/08
 */
public class SerializerFactory {
    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key 钥匙
     * @return {@link Serializer}
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }

}