package com.bestnet.hf.sysenum;

/**
 * 说明：发送短信枚举类
 *
 * 备注：暂时提供 登录 、 找回密码 、注册
 *
 * 作者：hzg
 *
 * 时间：2019-06-23
 *
 * */
public enum MobileCodeTypeEnum {
    LOGIN("LOGIN"),FORGET_PASSWORD("FORGET_PASSWORD"),SIGN_IN("SIGN_IN");

    private String value;

    private MobileCodeTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
