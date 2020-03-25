package com.atguigu.springcloud.config;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyRoundRule {

    // 在给定的服务列表中 轮询选择一个服务进行访问
    ServiceInstance getInstance(List<ServiceInstance> instances);
}
