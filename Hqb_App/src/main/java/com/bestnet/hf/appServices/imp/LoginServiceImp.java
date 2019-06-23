package com.bestnet.hf.appServices.imp;

import com.bestnet.hf.appServices.LoginService;
import com.bestnet.hf.bean.HfUserBean;
import com.bestnet.hf.bean.HfUserRegisterBean;
import com.bestnet.hf.services.manager.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    private UserService userService;

    //校验手机号是否已经使用
    public boolean checkMobileNum(String mobileNum) {
        boolean flag = false;
        HfUserBean hfUserBean = new HfUserBean();
        hfUserBean.setUserTel(mobileNum);
        hfUserBean = userService.checkMobileNum(hfUserBean);
        if(hfUserBean != null) flag = true;
        return flag;
    }

    //添加人员
    public int insertHfUser(HfUserBean hfUserBean) {
        return addHfUser(hfUserBean);
    }

    private int addHfUser(HfUserBean hfUserBean){
        return userService.insertUser(hfUserBean);
    }

}
