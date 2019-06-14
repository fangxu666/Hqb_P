package com.bestnet.hf.services.imp;

import com.bestnet.hf.bean.UserDemoBean;
import com.bestnet.hf.mapper.DemoMapper;
import com.bestnet.hf.services.Demo2Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo2")
public class Demo2ServiceImp implements Demo2Service {
    @Autowired
    private DemoMapper demoMapper;

    @RequestMapping("/addUserDemo")
    public Integer addUserDemo(@RequestBody UserDemoBean userDemoBean) {
        System.out.println("In PayServices,addUserDemo...");
        return demoMapper.insert(userDemoBean);
    }

    //TODO HystrixCommand服务熔断测试，需要细化
    @RequestMapping("/qryUser")
    @HystrixCommand(fallbackMethod = "hystrixQryUser")
    public UserDemoBean qryUser(int id) {
        if(id != 0)throw new RuntimeException("手动抛出错误");
        return demoMapper.selectById(id);
    }

    @RequestMapping("/qryUserList")
    public List<UserDemoBean> qryUserList() {
        return demoMapper.qryUserList();
    }

    @RequestMapping("/qryUserListByPage")
    public Object qryUserListByPage(int currentPage,int pageSize) {
        PageHelper.startPage(currentPage, pageSize);//设置分页参数
        List<UserDemoBean> list = demoMapper.qryUserList();
        PageInfo<UserDemoBean> pageInfo = new PageInfo<UserDemoBean>(list);
        return pageInfo;
    }

    public UserDemoBean hystrixQryUser(int id){
        UserDemoBean userDemoBean = new UserDemoBean();
        userDemoBean.setId(id);
        userDemoBean.setName("错误信息");
        return userDemoBean;
    }



}
