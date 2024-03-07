package com.cong.rpc.core.proxy;

public interface ProxyFactory {

    <T> T getProxy(Class<T> serviceClass);
}