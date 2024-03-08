package com.cong.rpc.core.registry;


import com.cong.rpc.core.config.RegistryConfig;
import com.cong.rpc.core.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册表
 * 注册中心
 *
 * @author cong
 * @date 2024/03/08
 */
public interface Registry {

    /**
     * 初始化
     *
     * @param registryConfig 注册表配置
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务（服务端）
     *
     * @param serviceMetaInfo 服务元信息
     * @throws Exception 例外
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 联合国注册
     * 注销服务（服务端）
     *
     * @param serviceMetaInfo 服务元信息
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现
     * 服务发现（获取某服务的所有节点，消费端）
     *
     * @param serviceKey 服务键名
     * @return {@link List}<{@link ServiceMetaInfo}>
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();
}