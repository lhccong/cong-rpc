package com.cong.example.provider;


import com.cong.example.model.User;
import com.cong.example.service.UserService;

/**
 * 用户服务实现类
 *
 */
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
