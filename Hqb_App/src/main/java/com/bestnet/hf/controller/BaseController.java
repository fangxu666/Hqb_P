package com.bestnet.hf.controller;

import com.alibaba.fastjson.JSONObject;
import com.bestnet.hf.bean.ResponseMessageBean;

/**
 * 说明：Controller抽象父类
 *
 * 作者：hzg
 *
 * 时间：2019-06-22
 *
 * */
public abstract class BaseController {

    //成功响应
    String successResponse(Integer returnCode,String returnMsg){
        return loadResponseMessage(returnCode,returnMsg,null);
    }
    //成功响应
    String successResponse(Integer returnCode,String returnMsg,Object obj){
        return loadResponseMessage(returnCode,returnMsg,obj);
    }

    //失败响应
    String errorResponse(Integer returnCode,String returnMsg){
        return loadResponseMessage(returnCode,returnMsg,null);
    }

    String loadResponseMessage(Integer returnCode,String returnMsg,Object obj){
        ResponseMessageBean responseMessageBean = new ResponseMessageBean();
        responseMessageBean.setReturnCode(returnCode);
        responseMessageBean.setReturnMsg(returnMsg);
        responseMessageBean.setData(obj);
        return JSONObject.toJSONString(responseMessageBean);
    }
}
