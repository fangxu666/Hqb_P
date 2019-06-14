package com.bestnet.hf.services;

import com.bestnet.hf.bean.UserDemoBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "payService")
public interface DemoService {
    @RequestMapping(value="/payService/demo2/addUserDemo",method = RequestMethod.POST)
    public Integer addUserDemo(@RequestBody UserDemoBean userDemoBean);
}
