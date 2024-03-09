package com.cong.rpc.core.cache;


import com.cong.rpc.core.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册表服务缓存
 * 注册中心服务本地缓存
 *
 * @author 86188
 * @date 2024/03/09
 */
public class RegistryServiceCache {

    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     * 写缓存
     *
     * @param newServiceCache
     * @return
     */
    public void writeCache(List<ServiceMetaInfo> newServiceCache) {
        this.serviceCache = newServiceCache;
    }

    /**
     * 读缓存
     *
     * @return
     */
    public List<ServiceMetaInfo> readCache() {
        return this.serviceCache;
    }

    /**
     * 清空缓存
     */
    public void clearCache() {
        this.serviceCache = null;
    }
}