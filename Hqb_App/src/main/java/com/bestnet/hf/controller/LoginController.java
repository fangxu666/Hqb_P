package com.bestnet.hf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 说明：平台人员登录、注册
 *
 * 作者：hzg
 *
 * 时间：2019-06-17
 *
 * */

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/index")
    public String login(){
        return "/html/manager/login/login";
    }
}
