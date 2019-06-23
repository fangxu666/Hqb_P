package com.bestnet.hf.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "HF_COMPANY")
public class HfCompanyBean {
    private Integer id;

    private Integer registerId;

    private String companyName;

    private String companyCode;

    private String companyType;

    private Date validStarttime;

    private Date validEndtime;

    private String companyIsValid;

    private String companyIsLogout;

    private BigDecimal moneyDiscount;
}