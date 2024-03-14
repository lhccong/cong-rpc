package com.crpc.cong.example.springboot.provider;

import com.cong.example.model.User;
import com.cong.example.service.UserService;
import com.crpc.cong.rpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 * @author cong
 * @date 2024/03/14
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}