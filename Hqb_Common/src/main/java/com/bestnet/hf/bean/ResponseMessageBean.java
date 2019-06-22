package com.bestnet.hf.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 说明：响应实体类，规范响应格式
 *
 * 作者：hzg
 *
 * 时间：2019-06-22
 *
 * */
@Data
public class ResponseMessageBean {
    /* 响应编码 */
    @JSONField(name = "returnCode")
    private Integer returnCode;

    /* 响应信息 */
    @JSONField(name = "returnMsg")
    private String returnMsg;

    /* 响应内容 */
    @JSONField(name = "data")
    private Object data;
}
