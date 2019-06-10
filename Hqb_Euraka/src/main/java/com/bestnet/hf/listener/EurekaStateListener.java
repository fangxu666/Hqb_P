package com.bestnet.hf.listener;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 说明：Eureka注册服务上下线监听
 *
 * */
@Component
@ComponentScan
public class EurekaStateListener {

    //下线
    //condition = "#event.replication == false " 避免重复发送消息（Eureka集群）
    @EventListener(condition = "#event.replication == false ")
    public void listen(EurekaInstanceCanceledEvent event){
        String msg="===========>服务名："+event.getAppName()+"  服务ID："+event.getServerId()+"，已下线";
        System.out.println(msg);
    }

    //注册
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        String msg="===========>服务名："+instanceInfo.getAppName()+"  服务地址："+  instanceInfo.getHostName()+":"+ instanceInfo.getPort()+ " 服务ip: " +instanceInfo.getIPAddr() +"，进行注册";
        System.out.println(msg);
    }

    //续约
    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceRenewedEvent event) {
        System.out.println("续约开始时间："+System.currentTimeMillis());
        String msg = "===========>服务名："+event.getAppName()+"  服务ID："+event.getServerId()+"，进行续约";
        System.out.println(msg);
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        String msg = "===========>注册中心启动";
        System.out.println(msg);
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        String msg = "===========>注册中心服务端启动";
        System.out.println(msg);
    }
}
