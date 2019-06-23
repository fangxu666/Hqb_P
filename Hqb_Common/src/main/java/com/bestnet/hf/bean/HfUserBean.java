package com.bestnet.hf.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "HF_USER")
public class HfUserBean {
    private Integer id;

    private Integer registerId;

    private String companyId;

    private String userName;

    private String userSex;

    private Date userBrithday;

    private String userPhoto;

    private String userTel;

    private String userLoginPassword;

    private String userIsLogout;

    private Date userCreatetime;

    private Integer userCreateuserid;
}