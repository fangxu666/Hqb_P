package com.bestnet.hf.services.imp;

import com.bestnet.hf.bean.HfUserBean;
import com.bestnet.hf.mapper.UserMapper;
import com.bestnet.hf.services.manager.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：登录、注册服务端
 *
 * 作者：hzg
 *
 * 时间：2019-06-23
 *
 * */

@RestController
@RequestMapping("/user")
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/checkMobileNum")
    public HfUserBean checkMobileNum(HfUserBean hfUserBean){
        return userMapper.selectOne(hfUserBean);
    }

    @RequestMapping("/insertUser")
    public int insertUser(HfUserBean hfUserBean){
        return userMapper.insert(hfUserBean);
    }
}
