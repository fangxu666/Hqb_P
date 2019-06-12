package com.bestnet.hf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bestnet.hf.bean.UserDemoBean;

import java.util.List;

/**
 * 说明：测试mapper
 *
 * 作者：hzg
 *
 * 时间：2019-06-11
 *
 * */
public interface  DemoMapper extends BaseMapper<UserDemoBean> {
    List<UserDemoBean> qryUserList();

    List<UserDemoBean> qryUserListByPage(Page<UserDemoBean> page);
}
