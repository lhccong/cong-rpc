package com.cong.rpc.core.proxy.jdk;

import com.cong.rpc.core.proxy.ProxyFactory;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂（用于创建代理对象）
 *
 * @author cong
 * @date 2024/03/07
 */
public class ServiceProxyFactory implements ProxyFactory {

    /**
     * 获取代理
     * 根据服务类获取代理对象
     *
     * @param serviceClass 服务类
     * @return {@link T}
     */
    public <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }
}
