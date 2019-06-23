package com.bestnet.hf.services.manager;

import com.bestnet.hf.bean.HfUserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 说明：登录、注册 远程调用
 *
 * 备注：传入对象使用POST方法，接收参数必须加@RequestBody注解；传入多个非对象参数，必须使用@RequestParam接收
 *
 * 作者：hzg
 *
 * 时间：2019-06-23
 *
 * */
@FeignClient(value = "hqbService")
public interface UserService {
    @RequestMapping(value="/hqbService/user/checkMobileNum",method = RequestMethod.POST)
    public HfUserBean checkMobileNum(@RequestBody HfUserBean hfUserBean);

    @RequestMapping(value="/hqbService/user/insertUser",method = RequestMethod.POST)
    public int insertUser(@RequestBody HfUserBean hfUserBean);
}
