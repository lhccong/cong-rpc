package com.crpc.cong.rpc.springboot.starter.bootstrap;

import com.cong.rpc.core.proxy.ServiceProxyFactory;
import com.crpc.cong.rpc.springboot.starter.annotation.RpcReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * Rpc 服务消费者启动
 *
 * @author cong
 * @date 2024/03/14
 */
@Slf4j
public class RpcConsumerBootstrap implements BeanPostProcessor {

    /**
     * 初始化后后处理
     * Bean 初始化后执行，注入服务
     *
     * @param bean     豆
     * @param beanName Bean 名称
     * @return {@link Object}
     * @throws BeansException Beans 异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        // 遍历对象的所有属性
        Field[] declaredFields = beanClass.getDeclaredFields();
        for (Field field : declaredFields) {
            RpcReference rpcReference = field.getAnnotation(RpcReference.class);
            if (rpcReference != null) {
                // 为属性生成代理对象
                Class<?> interfaceClass = rpcReference.interfaceClass();
                if (interfaceClass == void.class) {
                    interfaceClass = field.getType();
                }
                field.setAccessible(true);
                ServiceProxyFactory serviceProxyFactory = new ServiceProxyFactory();
                // 动态代理
                Object proxyObject = serviceProxyFactory.getProxy(interfaceClass);
                try {
                    field.set(bean, proxyObject);
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("为字段注入代理对象失败", e);
                }
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

}