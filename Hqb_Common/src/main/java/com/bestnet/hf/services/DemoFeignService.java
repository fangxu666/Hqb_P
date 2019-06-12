package com.bestnet.hf.services;

import com.bestnet.hf.bean.UserDemoBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 说明：测试接口
 *
 * 作者：hzg
 *
 * 时间：2019-06-11
 *
 * */
@FeignClient(value = "hqbService")
public interface DemoFeignService {
    @RequestMapping(value="/hqbService/demo/addUserDemo",method = RequestMethod.POST)
    public Integer addUserDemo(UserDemoBean userDemoBean);
}
