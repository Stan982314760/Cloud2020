package com.atguigu.springcloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 动态刷新
public class ConfigController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/nacos/config")
    public String config(){
        return configInfo;
    }
}
