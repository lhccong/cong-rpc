package com.cong.rpc.core.serializer;

import com.cong.rpc.core.serializer.fastjson.FastJsonSerializer;
import com.cong.rpc.core.serializer.hessian.HessianSerializer;
import com.cong.rpc.core.serializer.jdk.JdkSerializer;
import com.cong.rpc.core.serializer.kryo.KryoSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化器工厂（用于获取序列化器对象）
 *
 * @author cong
 * @date 2024/03/08
 */
public class SerializerFactory {

    /**
     * 序列化映射（用于实现单例）
     */
    private static final Map<String, Serializer> KEY_SERIALIZER_MAP = new HashMap<String, Serializer>() {{
        put(SerializerKeys.JDK, new JdkSerializer());
        put(SerializerKeys.JSON, new FastJsonSerializer());
        put(SerializerKeys.KRYO, new KryoSerializer());
        put(SerializerKeys.HESSIAN, new HessianSerializer());
    }};

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = KEY_SERIALIZER_MAP.get("jdk");

    /**
     * 获取实例
     *
     * @param key 钥匙
     * @return {@link Serializer}
     */
    public static Serializer getInstance(String key) {
        return KEY_SERIALIZER_MAP.getOrDefault(key, DEFAULT_SERIALIZER);
    }

}