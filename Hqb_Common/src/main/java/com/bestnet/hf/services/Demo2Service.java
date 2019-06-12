package com.bestnet.hf.services;

import com.bestnet.hf.bean.UserDemoBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 说明：传入对象使用POST方法，接收参数必须加@RequestBody注解；传入多个非对象参数，必须使用@RequestParam接收
 *
 * */
@FeignClient(value = "hqbService")
public interface Demo2Service {
    @RequestMapping(value="/hqbService/demo2/addUserDemo",method = RequestMethod.POST)
    public Integer addUserDemo(@RequestBody UserDemoBean userDemoBean);

    @RequestMapping(value="/hqbService/demo2/qryUser",method = RequestMethod.GET)
    public UserDemoBean qryUser(@RequestParam("id")int id);

    @RequestMapping(value="/hqbService/demo2/qryUserList",method = RequestMethod.GET)
    public List<UserDemoBean> qryUserList();

    @RequestMapping(value="/hqbService/demo2/qryUserListByPage",method = RequestMethod.POST)
    public Object qryUserListByPage(@RequestParam("currentPage")int currentPage,@RequestParam("pageSize")int pageSize);

}
