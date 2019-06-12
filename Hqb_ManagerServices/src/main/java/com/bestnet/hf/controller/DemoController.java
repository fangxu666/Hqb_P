package com.bestnet.hf.controller;

import com.bestnet.hf.bean.UserDemoBean;
import com.bestnet.hf.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：feign调用接口时，POST可以传对象，接收必须使用@RequestBody；GET不可传递对象，接收单个参数使用@RequestParam
 *
 * 作者：hzg
 *
 * 时间：2019-06-11
 *
 * */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/addUserDemo")
    public Integer addUserDemo(@RequestBody  UserDemoBean userDemoBean){
        return demoService.addUserDemo(userDemoBean);
    }
}
