package com.cong.example.provider;


import com.cong.example.service.UserService;
import com.cong.rpc.core.bootstrap.ProviderBootstrap;
import com.cong.rpc.core.model.ServiceRegisterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供程序示例
 * 服务提供者示例
 *
 * @author 86188
 * @date 2024/03/10
 */
public class ProviderExample {

    public static void main(String[] args) {
        // 要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}