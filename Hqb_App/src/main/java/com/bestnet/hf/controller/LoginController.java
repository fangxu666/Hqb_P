package com.bestnet.hf.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.bestnet.hf.util.MobileMessageUtil;
import com.bestnet.hf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
public class LoginController extends BaseController {

    @Autowired
    private RedisUtil redisUtil;

    //登录页面
    @RequestMapping("/index")
    public String index(){
        return "manager/login/login";
    }

    //注册页面
    @RequestMapping("/register")
    public String register(@RequestParam("registerType")String registerType){
        String pageUrl = "manager/register/register";
        switch (registerType){
            case "engineer"://工程师
                pageUrl = "manager/register/registerEngineer";
            break;
            case "enterprise"://业主
                pageUrl = "manager/register/registerEnterprise";
                break;
            case "service"://服务商
                pageUrl = "manager/register/registerService";
                break;
        }
        return pageUrl;
    }

    //忘记密码
    @RequestMapping("/forgetPassword")
    public String forgetPassword(){
        return "manager/login/forgetPassword";
    }

    //登录session失效
    @RequestMapping("/sessionLoss")
    public String sessionLoss(){
        return "manager/login/loginLose";
    }

    /**
     * 说明：获取验证码
     *
     * 参数：
     * @param codeType : 获取验证码类型 0：登录  1：忘记密码
     * @param mobileNum : 手机号
     *
     * */
    @RequestMapping("/getMobileCode")
    public String getMobileCode(@RequestParam("codeType")String codeType,@RequestParam("mobileNum")String mobileNum){
        //验证码
        String mobileCode = "";
        try {
            SendSmsResponse sendSmsResponse = MobileMessageUtil.sendSms(mobileNum,codeType);
            //验证码
            mobileCode = sendSmsResponse.getCode();
            //验证码放入redis缓存中，设置5分钟后失效，key 手机号  value 验证码
            boolean flag = redisUtil.set(mobileNum,codeType);
            if(flag){
                redisUtil.expire(mobileNum,300);
            }
        }catch (Exception e){
            e.printStackTrace();
            return errorResponse(500,e.getMessage());
        }
        return successResponse(200,"获取成功",mobileCode);
    }

    /**
     * 说明：注册
     *
     *
     *
     *
     * */
    @RequestMapping("/signIn")
    public String signIn(){
        return "";
    }

    /**
     * 说明：登录
     *
     * 参数：
     * @param loginType ：登录类型 0：密码  1：验证码
     * @param userName ：用户名
     * @param password ：密码
     * @param mobileCode ：手机验证码
     *
     * */
    @RequestMapping("/login")
    public String login(@RequestParam("loginType") Integer loginType,@RequestParam("userName")String userName,
                        @RequestParam("password")String password,@RequestParam("mobileCode")String mobileCode){
        return "";
    }


}
