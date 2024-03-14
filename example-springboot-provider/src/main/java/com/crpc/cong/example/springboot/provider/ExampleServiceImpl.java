package com.crpc.cong.example.springboot.provider;

import com.cong.example.model.User;
import com.cong.example.service.UserService;
import com.crpc.cong.rpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

/**
 * 示例服务 impl
 *
 * @author cong
 * @date 2024/03/14
 */
@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public void test() {
        User user = new User();
        user.setName("我是聪~~~");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }

}