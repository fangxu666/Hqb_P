package com.bestnet.hf.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "HF_USER_REGISTER")
public class HfUserRegisterBean {
    private Integer id;

    private String reUsername;

    private String reUsersex;

    private String reUsertel;

    private String reLoginPassword;

    private String reIsopeate;

    private Date reCreatetime;
}