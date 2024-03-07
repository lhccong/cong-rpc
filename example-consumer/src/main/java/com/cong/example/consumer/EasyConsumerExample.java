package com.cong.example.consumer;


import com.cong.example.model.User;
import com.cong.example.service.UserService;
import com.cong.rpc.core.proxy.jdk.ServiceProxyFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 简易服务消费者示例
 *
 * @author cong
 * @date 2024/03/07
 */
@Slf4j
public class EasyConsumerExample {

    public static void main(String[] args) {
        ServiceProxyFactory serviceProxyFactory = new ServiceProxyFactory();
        // 动态代理
        UserService userService = serviceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("我是聪！！！！");
        // 调用
        for (int i = 0; i < 10; i++) {
            User newUser = userService.getUser(user);
            if (newUser != null) {
                System.out.println("我是name啊"+newUser.getName());
            } else {
                System.out.println("user == null");
            }
        }
        long number = userService.getNumber();
        System.out.println(number);

    }
}
