package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyGatewayConfig {

    // 当访问路径匹配 /guonei/** 规则时，将请求路由转发到 http://news.baidu.com/guonei/** 处理
    @Bean
    public RouteLocator routeLocator1(RouteLocatorBuilder routeLocatorBuilder){
       return  routeLocatorBuilder.routes()
                .route("news1",predicateSpec -> predicateSpec.path("/guonei/**").uri("http://news.baidu.com"))
               .build();
    }

    @Bean
    public RouteLocator routeLocator2(RouteLocatorBuilder routeLocatorBuilder){
        return  routeLocatorBuilder.routes()
                .route("news2",predicateSpec -> predicateSpec.path("/finance/**").uri("http://news.baidu.com"))
                .build();
    }
}
