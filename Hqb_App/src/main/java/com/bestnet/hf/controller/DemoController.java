package com.bestnet.hf.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.bestnet.hf.bean.UserDemoBean;
import com.bestnet.hf.services.Demo2Service;
import com.bestnet.hf.services.DemoService;
import com.bestnet.hf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @Autowired
    private Demo2Service demo2Service;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/addUserDemo")
    public void addUserDemo(){
        UserDemoBean userDemoBean = new UserDemoBean();
        userDemoBean.setName("李寒蕾");
        userDemoBean.setAge(26);
        Integer num = demo2Service.addUserDemo(userDemoBean);
        System.out.println("添加的条数："+num);
    }

    @RequestMapping("/qryUser/{id}")
    @ResponseBody
    public UserDemoBean qryUser(@PathVariable("id") int id){
        return demo2Service.qryUser(id);
    }

    @RequestMapping("/qryUserList")
    @ResponseBody
    public List<UserDemoBean> qryUserList(){
        return demo2Service.qryUserList();
    }

    @RequestMapping("/qryUserListByPage/{currentPage}")
    @ResponseBody
    public Object qryUserListByPage(@PathVariable("currentPage") int currentPage){
        int pageSize = 2;
        return demo2Service.qryUserListByPage(currentPage,pageSize);
    }


    @RequestMapping("/redis")
    @ResponseBody
    public String qryRedis(){
        return redisUtil.get("hzg").toString();
    }

    //分布式事务测试
    @RequestMapping("/test")
    public void addUserBy2Demo(){
        UserDemoBean userDemoBean = new UserDemoBean();
        userDemoBean.setName("李寒蕾");
        userDemoBean.setAge(26);
        demoService.addUserDemo(userDemoBean);
        UserDemoBean userDemoBean2 = new UserDemoBean();
        userDemoBean2.setName("胡志刚");
        userDemoBean2.setAge(27);
        demo2Service.addUserDemo(userDemoBean2);
        int a=1/0;
    }
}
