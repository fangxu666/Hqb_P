package com.bestnet.hf.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.bestnet.hf.appServices.LoginService;
import com.bestnet.hf.bean.HfUserBean;
import com.bestnet.hf.exception.SystemException;
import com.bestnet.hf.sysenum.MobileCodeTypeEnum;
import com.bestnet.hf.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

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
     * @param codeType : 获取验证码类型 0：登录  1：忘记密码  2:注册
     * @param mobileNum : 手机号
     *
     * */
    @RequestMapping("/getMobileCode")
    public String getMobileCode(@RequestParam("codeType")String codeType,@RequestParam("mobileNum")String mobileNum){
        //验证码
        String mobileCode = "";
        try {
            //生成redis缓存的key值
            String key = "";
            switch (codeType){
                case "0"://登录
                    key = mobileNum+ConstantUtil.SYS_SPLIT_FLAG+MobileCodeTypeEnum.LOGIN.getValue();
                    break;
                case "1"://忘记密码
                    key = mobileNum+ConstantUtil.SYS_SPLIT_FLAG+MobileCodeTypeEnum.FORGET_PASSWORD.getValue();
                    break;
                case "2"://注册
                    key = mobileNum+ConstantUtil.SYS_SPLIT_FLAG+MobileCodeTypeEnum.SIGN_IN.getValue();
                    break;
            }
            String value = (String)redisUtil.get(key);
            if(!StringUtil.isEmpty(value)){//不为空，证明验证码已发送，还未到失效时间，重新发送，但失效时间不重新计时
                SendSmsResponse sendSmsResponse = MobileMessageUtil.sendSms(mobileNum,codeType,value);
            }else{//为空，发送验证码并设置失效时间
                SendSmsResponse sendSmsResponse = MobileMessageUtil.sendSms(mobileNum,codeType);
                //验证码
                mobileCode = sendSmsResponse.getCode();
                //验证码放入redis缓存中，设置5分钟后失效，key 手机号|类型  value 验证码
                boolean flag = redisUtil.set(key,codeType);
                if(flag){
                    redisUtil.expire(key,300);
                }
            }
        }catch (Exception e){
            LOG.error("获取手机验证码失败，失败原因："+e.getMessage());
            e.printStackTrace();
            return errorResponse(500,e.getMessage());
        }
        return successResponse(200,"获取成功",mobileCode);
    }

    /**
     * 说明：校验手机号是否已经使用
     *
     * 参数：
     * @param mobileNum：手机号
     *
     * */
    @RequestMapping("/checkMobileNum")
    public String checkMobileNum(@RequestParam("mobileNum")String mobileNum){
        try {
            if(StringUtil.isEmpty(mobileNum))throw new SystemException(500,"校验手机号是否使用失败，原因：传入手机号为空");
            if(!MobileNumUtil.checkMobileNum(mobileNum))throw new SystemException(500,"手机号["+mobileNum+"]不符合规范");
            boolean flag = loginService.checkMobileNum(mobileNum);
            if(flag)return errorResponse(500,"手机号["+mobileNum+"]已经注册，请前往登录。");
        } catch (SystemException e){
            e.printStackTrace();
            return errorResponse(e.getErrorCode(),e.getMessage());
        } catch (Exception e){
            LOG.error("校验手机号失败，失败原因："+e.getMessage());
            e.printStackTrace();
        }
        return successResponse(200,"手机号未被注册");
    }


    /**
     * 说明：注册
     *
     * 参数：
     * @param signInType：注册类型 0：工程师注册  1：学校  2：服务商
     * @param mobileNum: 手机号
     * @param mobileCode: 手机验证码
     * @param password: 登录密码
     * @param checkPassword: 确认密码
     * @param companyName: 公司名称
     *
     * */
    @RequestMapping("/signIn")
    public String signIn(@RequestParam("signInType")String signInType,@RequestParam("mobileNum")String mobileNum,@RequestParam("mobileCode") String mobileCode,
                         @RequestParam("password")String password,@RequestParam("checkPassword")String checkPassword,@RequestParam("companyName")String companyName){
        HfUserBean hfUserBean = new HfUserBean();
        try {
            if(StringUtil.isEmpty(signInType))throw new SystemException(500,"注册类型为空");
            if(StringUtil.isEmpty(mobileNum))throw new SystemException(500,"注册手机号为空");
            if(StringUtil.isEmpty(mobileCode))throw new SystemException(500,"注册手机验证码为空");
            if(StringUtil.isEmpty(password))throw new SystemException(500,"登录密码为空");
            if(StringUtil.isEmpty(checkPassword))throw new SystemException(500,"登录确认密码为空");
            if(("1".equals(signInType)||"2".equals(signInType))&&StringUtil.isEmpty(companyName))throw new SystemException(500,"注册公司名称为空");
            //校验手机号码是否符合规范
            if(!MobileNumUtil.checkMobileNum(mobileNum))throw new SystemException(500,"手机号["+mobileNum+"]不符合规范");
            //校验手机验证码是否正确
            String key = mobileNum+ConstantUtil.SYS_SPLIT_FLAG+MobileCodeTypeEnum.SIGN_IN.getValue();
            if(mobileCode.equals(redisUtil.get(key)))throw new SystemException(500,"输入的手机验证码有误，请核对");
            //校验密码是否正确
            if(!Md5Util.MD5Encode(password).equals(Md5Util.MD5Encode(checkPassword)))throw new SystemException(500,"两次密码输入的不一致，请重新输入");

            //封装参数
            hfUserBean.setUserTel(mobileNum);
            hfUserBean.setUserLoginPassword(password);
            int count = 0;
            switch (signInType){
                case "0"://工程师
                    count = loginService.insertHfUser(hfUserBean);
                    break;

                case "1"://学校

                    break;

                case "2"://服务商

                    break;
            }
        } catch (SystemException e){
            e.printStackTrace();
            return errorResponse(e.getErrorCode(),e.getMessage());
        } catch (Exception e){
            LOG.error("用户注册失败，失败原因："+e.getMessage());
            e.printStackTrace();
        }
        return successResponse(200,"注册成功",hfUserBean);
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
