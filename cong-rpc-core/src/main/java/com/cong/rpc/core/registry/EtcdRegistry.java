package com.cong.rpc.core.registry;

import com.cong.rpc.core.config.RegistryConfig;
import com.cong.rpc.core.model.ServiceMetaInfo;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;

import java.time.Duration;
import java.util.List;

/**
 * etcd 注册表
 *
 * @author cong
 * @date 2024/03/08
 */
public class EtcdRegistry implements Registry  {

    private Client client;

    private KV kvClient;

    /**
     * 根节点
     */
    private static final String ETCD_ROOT_PATH = "/rpc/";

    @Override
    public void init(RegistryConfig registryConfig) {
        client = Client.builder().endpoints(registryConfig.getAddress()).connectTimeout(Duration.ofMillis(registryConfig.getTimeout())).build();
        kvClient = client.getKVClient();
    }

    @Override
    public void register(ServiceMetaInfo serviceMetaInfo) throws Exception {

    }

    @Override
    public void unRegister(ServiceMetaInfo serviceMetaInfo) {

    }

    @Override
    public List<ServiceMetaInfo> serviceDiscovery(String serviceKey) {
        return null;
    }

    @Override
    public void destroy() {

    }
}