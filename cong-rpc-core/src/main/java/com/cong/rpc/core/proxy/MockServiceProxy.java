package com.cong.rpc.core.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mock 服务代理（JDK 动态代理）
 * @author liuhuaicong
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {

    /**
     * 调用
     * 调用代理
     *
     * @param proxy  代理
     * @param method 方法
     * @param args   参数
     * @return {@link Object}
     * @throws Throwable 可投掷
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据方法的返回值类型，生成特定的默认值对象
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        return getDefaultObject(methodReturnType);
    }

    /**
     * 获取默认对象
     * 生成指定类型的默认值对象（可自行完善默认值逻辑）
     *
     * @param type 类型
     * @return {@link Object}
     */
    private Object getDefaultObject(Class<?> type) {
        // 基本类型
        if (type.isPrimitive()) {
            if (type == boolean.class) {
                return false;
            } else if (type == short.class) {
                return (short) 0;
            } else if (type == int.class) {
                return 0;
            } else if (type == long.class) {
                return 0L;
            }
        }
        // 对象类型
        return null;
    }
}