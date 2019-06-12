package com.bestnet.hf.controller;

import com.bestnet.hf.bean.UserDemoBean;
import com.bestnet.hf.services.DemoFeignService;
import com.bestnet.hf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoFeignService demoFeignService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/addUserDemo")
    public void addUserDemo(){
        UserDemoBean userDemoBean = new UserDemoBean();
        userDemoBean.setName("胡志刚");
        userDemoBean.setAge(27);
        Integer num = demoFeignService.addUserDemo(userDemoBean);
        System.out.println("添加的条数："+num);
    }

    @RequestMapping("/redis")
    @ResponseBody
    public String qryRedis(){
        return redisUtil.get("hzg").toString();
    }
}
