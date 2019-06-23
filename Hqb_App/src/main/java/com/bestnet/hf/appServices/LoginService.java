package com.bestnet.hf.appServices;

import com.bestnet.hf.bean.HfUserBean;

public interface LoginService {

    boolean checkMobileNum(String mobileNum);

    int insertHfUser(HfUserBean hfUserBean);
}
