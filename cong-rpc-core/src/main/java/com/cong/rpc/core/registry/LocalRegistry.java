package com.cong.rpc.core.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册中心
 *
 * @author cong
 * @date 2024/03/07
 */
public class LocalRegistry {

    /**
     * 注册信息存储
     */
    private static final Map<String, Class<?>> MAP = new ConcurrentHashMap<>();

    /**
     * 注册
     * 注册服务
     *
     * @param serviceName 服务名称
     * @param implClass   IMPL 类
     */
    public static void register(String serviceName, Class<?> implClass) {
        MAP.put(serviceName, implClass);
    }

    /**
     * 获取服务
     *
     * @param serviceName 服务名称
     * @return {@link Class}<{@link ?}>
     */
    public static Class<?> get(String serviceName) {
        return MAP.get(serviceName);
    }

    /**
     * 删除服务
     *
     * @param serviceName 服务名称
     */
    public static void remove(String serviceName) {
        MAP.remove(serviceName);
    }
}
