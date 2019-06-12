package com.bestnet.hf.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("USERDEMO")
@Data
public class UserDemoBean implements Serializable {
    @TableId("ID")
    private int id;

    @TableField("NAME")
    private String name;

    @TableField("AGE")
    private int age;
}
