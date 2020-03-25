package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // 引入ribbon负载均衡 不然通过注册中心上的服务名称去调用服务 会报错
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
