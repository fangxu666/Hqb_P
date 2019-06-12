package com.bestnet.hf.services.imp;

import com.bestnet.hf.bean.UserDemoBean;
import com.bestnet.hf.mapper.DemoMapper;
import com.bestnet.hf.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImp implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public Integer addUserDemo(UserDemoBean userDemoBean) {
        return demoMapper.insert(userDemoBean);
    }
}
