package com.bestnet.hf.interceptor;

import com.bestnet.hf.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 说明：登录拦截器
 *
 * 作者：hzg
 *
 * 时间：2019-06-14
 *
 * */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);
    //验证登陆状态的业务逻辑
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = redisUtil.get(request.getSession().getId());
        if (obj == null){
            response.sendRedirect("");
            return false;
        }else{
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }
    }
}
