package com.bestnet.hf.util;

/**
 * 说明：系统常量工具类
 *
 * 作者：hzg
 *
 * 时间：2019-06-22
 *
 * */
public class ConstantUtil {

    //分割标识
    public static final String SYS_SPLIT_FLAG = "|";

    /********************  阿里云发送短信常量  *********************/
    //产品名称:云通信短信API产品,开发者无需替换
    public static final String SEND_MOBILEINFO_PRODUCT = "Dysmsapi";

    //产品域名,开发者无需替换
    public static final String SEND_MOBILEINFO_DOMAIN = "dysmsapi.aliyuncs.com";

    // TODO 以下需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    public static final String SEND_MOBILEINFO_ACCESSKEYID = "yourAccessKeyId";
    public static final String SEND_MOBILEINFO_ACCESSKEYSECRET = "yourAccessKeySecret";
    /********************  阿里云发送短信常量  *********************/
}
